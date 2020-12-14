package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.*;
import projectCar.service.CarServiceImpl;
import projectCar.service.CurrencyServiceImpl;
import projectCar.service.UserServiceImpl;
import projectCar.service.interfaces.ICarService;
import projectCar.service.interfaces.ICurrencyService;
import projectCar.service.interfaces.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MyPageController {

    @Autowired
    private IUserService userService = new UserServiceImpl();

    @Autowired
    private ICarService carService = new CarServiceImpl();

    @Autowired
    private ICurrencyService currencyService = new CurrencyServiceImpl();

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private ModelAndView modelAndView = new ModelAndView();

    private int page;

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("searchText") String searchText) {
        modelAndView.addObject("searchText", searchText);
        modelAndView.setViewName("redirect:/search/{searchText}");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView allUsers() {
        List<User> users = userService.getAll();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("userList", users);
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView myPage(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam(defaultValue = "1") int page) {
        String login = userDetails.getUsername();
        User userAuth = userService.findByLogin(login);
        int idUser = userAuth.getId();
        List<Car> carList = carService.getCars(page, idUser);
        int carsCount = carService.carsCount(idUser);
        int pagesCount = (carsCount + 9) / 10;
        modelAndView.setViewName("home");
        modelAndView.addObject("page", page);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("user", userAuth);
        modelAndView.addObject("carList", carList);
        modelAndView.addObject("carsCount", carsCount);
        modelAndView.addObject("cars", new Car());
        this.page = page;
        return modelAndView;
    }

    @GetMapping("/search/{searchText}")
    public ModelAndView searchPage(@PathVariable("searchText") String searchText,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   @RequestParam(defaultValue = "1") int page) {
        String loginUser = userDetails.getUsername();
        User userAuth = userService.findByLogin(loginUser);
        int idUser = userAuth.getId();
        List<Car> carsList = carService.searchList(searchText, idUser, page);
        int countPageCars;
        if (page <= 1) {
            countPageCars = (carsList.size() + 10) / 10;
        } else {
            countPageCars = (carsList.size() + (10 * page)) / 10;
        }
        for (Car cars : carsList) {
            List<Document> docs = cars.getDocuments().stream()
                    .filter(document -> document.getNameDocument().equals(searchText)).collect(Collectors.toList());
            int countPageDocs = (docs.size() + 10) / 10;

            List<Repair> repairs = cars.getRepairs().stream()
                    .filter(repair -> repair.getNameRepair().equals(searchText)).collect(Collectors.toList());
            int countPageRepair = (repairs.size() + 10) / 10;

            List<OtherCosts> costs = cars.getOtherCosts().stream()
                    .filter(othCosts -> othCosts.getNameOtherCost().equals(searchText)).collect(Collectors.toList());
            int countPageOtherCosts = (repairs.size() + 10) / 10;

            modelAndView.addObject("docs", docs);
            modelAndView.addObject("countPageDocs", countPageDocs);
            modelAndView.addObject("repairs", repairs);
            modelAndView.addObject("countPageRepair", countPageRepair);
            modelAndView.addObject("costs", costs);
            modelAndView.addObject("countPageOtherCosts", countPageOtherCosts);
        }
        modelAndView.addObject("page", page);
        modelAndView.setViewName("search");
        modelAndView.addObject("searchText", searchText);
        modelAndView.addObject("carsList", carsList);
        modelAndView.addObject("countPageCars", countPageCars);
        this.page = page;
        return modelAndView;
    }

    @GetMapping("/editUser")
    public ModelAndView editUserPage(@AuthenticationPrincipal UserDetails userDetails) {
        String login = userDetails.getUsername();
        User userAuth = userService.findByLogin(login);
        List<Currency> currencyList = currencyService.getAll();
        modelAndView.addObject("user", userAuth);
        modelAndView.addObject("currencies", currencyList);
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    @PostMapping("/editUser")
    public ModelAndView editUser(@ModelAttribute("user") User user,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("error", "Некорректный ввод данных");
            return modelAndView;
        }
        Currency currency = currencyService.read(1);
        user.setCurrency(currency);
        modelAndView.setViewName("redirect:/");
        userService.update(user);
        return modelAndView;
    }

    //Problem with bCryptPasswordEncoder
    @GetMapping("/editPassword")
    public ModelAndView editPasswordPage(@AuthenticationPrincipal UserDetails userDetails) {
        String login = userDetails.getUsername();
        User userAuth = userService.findByLogin(login);
        String newPassword = "";
        modelAndView.addObject("user", userAuth);
        modelAndView.addObject("newPassword", newPassword);
        modelAndView.setViewName("editPassword");
        return modelAndView;
    }

    @PostMapping("/editPassword")
    public ModelAndView editPasswordUser(@ModelAttribute("user") User user,
                                         BindingResult bindingResult,
                                         @ModelAttribute("newPassword") String newPassword) {
        User userAuth = userService.read(user.getId());
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("error", "Некорректный ввод данных");
            return modelAndView;
        }
        if (!bCryptPasswordEncoder.matches(user.getPassword(), userAuth.getPassword())) {
            modelAndView.addObject("passwordError", "Неверный пароль");
            return modelAndView;
        }
        user.setPassword(newPassword);
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            modelAndView.addObject("passwordConfirmError",
                    "Подтвержденный пароль не совпадает с новым");
            return modelAndView;
        }
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        modelAndView.setViewName("redirect:/editUser");
        userService.update(user);
        return modelAndView;
    }


}