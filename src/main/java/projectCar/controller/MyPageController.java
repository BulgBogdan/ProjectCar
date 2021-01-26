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
import projectCar.methods.Calculations;
import projectCar.methods.ServiceSolution;
import projectCar.service.DocumentServiceImpl;
import projectCar.service.OtherCostsServiceImpl;
import projectCar.service.RepairServiceImpl;
import projectCar.service.UserServiceImpl;
import projectCar.service.interfaces.IDocumentService;
import projectCar.service.interfaces.IOtherCostsService;
import projectCar.service.interfaces.IRepairService;
import projectCar.service.interfaces.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    private IUserService userService = new UserServiceImpl();

    @Autowired
    private IDocumentService documentService = new DocumentServiceImpl();

    @Autowired
    private IOtherCostsService costsService = new OtherCostsServiceImpl();

    @Autowired
    private IRepairService repairService = new RepairServiceImpl();

    @Autowired
    private ServiceSolution solutions;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private ModelAndView modelAndView = new ModelAndView();

    private int page;

    private String prevPage = "";

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("searchText") String searchText) {
        modelAndView.addObject("searchText", searchText);
        modelAndView.setViewName("redirect:/search/{searchText}");
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView myPage(@AuthenticationPrincipal UserDetails authUser,
                               @RequestParam(defaultValue = "1") int page) {
        User userAuth = findUserByLogin(authUser.getUsername());
        int idUser = userAuth.getId();
        List<Car> carList = solutions.getUserCars(page, idUser);
        int carsCount = solutions.getCountCarsByUser(idUser);
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
                                   @AuthenticationPrincipal UserDetails authUser,
                                   @RequestParam(defaultValue = "1") int page) {
        User userAuth = findUserByLogin(authUser.getUsername());
        int idUser = userAuth.getId();

        List<Document> docs = documentService.searchList(searchText, idUser, page);
        List<Repair> repairs = repairService.searchList(searchText, idUser, page);
        List<OtherCosts> costs = costsService.searchList(searchText, idUser, page);

        modelAndView.addObject("docs", docs);
        modelAndView.addObject("repairs", repairs);
        modelAndView.addObject("costs", costs);

        modelAndView.addObject("countPageDocs", Calculations.getCountPage(page, docs.size()));
        modelAndView.addObject("countPageRepair", Calculations.getCountPage(page, repairs.size()));
        modelAndView.addObject("countPageOtherCosts", Calculations.getCountPage(page, costs.size()));

        modelAndView.addObject("page", page);
        modelAndView.addObject("searchText", searchText);
        modelAndView.addObject("user", userAuth);

        if (docs.isEmpty() && repairs.isEmpty() && costs.isEmpty()) {
            modelAndView.setViewName("searchNull");
        } else {
            modelAndView.setViewName("search");
        }

        this.page = page;
        return modelAndView;
    }

    @GetMapping("/editUser")
    public ModelAndView editUserPage(@AuthenticationPrincipal UserDetails authUser) {
        User userAuth = findUserByLogin(authUser.getUsername());
        Currency currency = userAuth.getCurrency();
        modelAndView.addObject("user", userAuth);
        modelAndView.addObject("currency", currency);
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    @PostMapping("/editUser")
    public ModelAndView editUser(@ModelAttribute("user") User user,
                                 @ModelAttribute("currency") Currency currency,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            return modelAndView;
        }
        user.setCurrency(currency);
        userService.update(user);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/currency")
    public ModelAndView editCurrency(@AuthenticationPrincipal UserDetails authUser,
                                     HttpServletRequest request) {
        User userAuth = findUserByLogin(authUser.getUsername());
        String backPage = request.getHeader("referer");
        prevPage = getPrevPage(backPage);

        List<Currency> currencyList = solutions.getAllCurrency();
        int currencyID = userAuth.getCurrency().getId();

        double currencyTodayUSD = solutions.getCurrencyValueUSD();

        modelAndView.addObject("user", userAuth);
        modelAndView.addObject("currencyValueUSD", currencyTodayUSD);
        modelAndView.addObject("currencies", currencyList);
        modelAndView.addObject("currencyID", currencyID);

        modelAndView.setViewName("currency");
        return modelAndView;
    }

    @PostMapping("/currency")
    public ModelAndView editCurrency(@ModelAttribute("user") User user,
                                     @ModelAttribute("currencyID") int currencyId,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            return modelAndView;
        }
        Currency currency = solutions.getCurrencyById(currencyId);
        user.setCurrency(currency);
        modelAndView.setViewName("redirect:" + prevPage);
        userService.update(user);
        return modelAndView;
    }

    @GetMapping("/editPassword")
    public ModelAndView editPasswordPage(@AuthenticationPrincipal UserDetails authUser) {
        User userAuth = findUserByLogin(authUser.getUsername());
        Currency currency = userAuth.getCurrency();
        String newPassword = "";
        modelAndView.addObject("user", userAuth);
        modelAndView.addObject("currency", currency);
        modelAndView.addObject("newPassword", newPassword);
        modelAndView.setViewName("editPassword");
        return modelAndView;
    }

    @PostMapping("/editPassword")
    public ModelAndView editPasswordUser(@ModelAttribute("user") User user,
                                         BindingResult bindingResult,
                                         @ModelAttribute("currency") Currency currency,
                                         @ModelAttribute("newPassword") String newPassword) {
        User userAuth = userService.read(user.getId());
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("Errors", "Некоректный ввод данных");
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
        user.setCurrency(currency);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        modelAndView.setViewName("redirect:/editUser");
        userService.update(user);
        return modelAndView;
    }

    private User findUserByLogin(String login) {
        return userService.findByLogin(login);
    }

    private String getPrevPage(String prevPage) {
        return prevPage.substring(21, prevPage.length());
    }
}