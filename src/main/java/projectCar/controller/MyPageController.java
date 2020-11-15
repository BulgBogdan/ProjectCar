package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.*;
import projectCar.service.*;
import projectCar.service.interfaces.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MyPageController {

    @Autowired
    private IUserService userService = new UserServiceImpl();

    @Autowired
    private ICarService carService = new CarServiceImpl();

    @Autowired
    private IDocumentService documentService = new DocumentServiceImpl();

    @Autowired
    private IOtherCostsService costsService = new OtherCostsServiceImpl();

    @Autowired
    private IRepairService repairService = new RepairServiceImpl();

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private ModelAndView modelAndView = new ModelAndView();

    private int page;

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

    @GetMapping("/search")
    public ModelAndView searchPage(@RequestParam("searchText") String searchText,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        String loginUser = userDetails.getUsername();
        User userAuth = userService.findByLogin(loginUser);
        int idUser = userAuth.getId();
        List<Car> carsList = carService.searchList(searchText, idUser);
        for (Car cars : carsList) {

            List<Document> docs = cars.getDocuments().stream()
                    .filter(document -> document.getNameDocument().equals(searchText)).collect(Collectors.toList());
            List<Repair> repairs = cars.getRepairs().stream()
                    .filter(repair -> repair.getNameRepair().equals(searchText)).collect(Collectors.toList());
            List<OtherCosts> costs = cars.getOtherCosts().stream()
                    .filter(othCosts -> othCosts.getNameOtherCost().equals(searchText)).collect(Collectors.toList());

            modelAndView.addObject("docs", docs);
            modelAndView.addObject("repairs", repairs);
            modelAndView.addObject("costs", costs);
        }

        carsList = carsList.stream().filter(car -> car.getNameCar().equals(searchText)).collect(Collectors.toList());
        modelAndView.addObject("carsList", carsList);
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @GetMapping("/editUser")
    public ModelAndView editUserPage(@AuthenticationPrincipal UserDetails userDetails) {
        String login = userDetails.getUsername();
        User userAuth = userService.findByLogin(login);
        modelAndView.addObject("user", userAuth);
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