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
import projectCar.service.*;
import projectCar.service.interfaces.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MyPageController<T> {

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
    private ICurrencyService currencyService = new CurrencyServiceImpl();

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private ModelAndView modelAndView = new ModelAndView();

    private User findUserByLogin(String login) {
        User foundedUser = userService.findByLogin(login);
        return foundedUser;
    }

    private Currency findCurrencyById(int id) {
        Currency foundedCurrency = currencyService.read(id);
        return foundedCurrency;
    }

    private int page;

    private String getPrevPage(String prevPage) {
        String backPage = prevPage.substring(21, prevPage.length());
        return backPage;
    }

    private String prevPage = "";

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("searchText") String searchText) {
        modelAndView.addObject("searchText", searchText);
        modelAndView.setViewName("redirect:/search/{searchText}");
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView myPage(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam(defaultValue = "1") int page) {
        String login = userDetails.getUsername();
        User userAuth = findUserByLogin(login);
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
        User userAuth = findUserByLogin(loginUser);
        int idUser = userAuth.getId();

        List<Document> docs = documentService.searchList(searchText, idUser, page);
        List<Repair> repairs = repairService.searchList(searchText, idUser, page);
        List<OtherCosts> costs = costsService.searchList(searchText, idUser, page);

        modelAndView.addObject("docs", docs);
        modelAndView.addObject("repairs", repairs);
        modelAndView.addObject("costs", costs);

        int countPageDocs = (docs.size() + 10 * page) / 10;
        int countPageRepair = (repairs.size() + 10 * page) / 10;
        int countPageOtherCosts = (repairs.size() + 10 * page) / 10;

        modelAndView.addObject("countPageDocs", countPageDocs);
        modelAndView.addObject("countPageRepair", countPageRepair);
        modelAndView.addObject("countPageOtherCosts", countPageOtherCosts);

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
    public ModelAndView editUserPage(@AuthenticationPrincipal UserDetails userDetails) {
        String login = userDetails.getUsername();
        User userAuth = findUserByLogin(login);
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
            modelAndView.addObject("error", "Некорректный ввод данных");
            return modelAndView;
        }
        user.setCurrency(currency);
        userService.update(user);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/currency")
    public ModelAndView editCurrency(@AuthenticationPrincipal UserDetails userDetails,
                                     HttpServletRequest request) {
        String login = userDetails.getUsername();
        User userAuth = findUserByLogin(login);
        String backPage = request.getHeader("referer");
        prevPage = getPrevPage(backPage);
        List<Currency> currencyList = currencyService.getAll();
        int currencyID = userAuth.getCurrency().getId();
        double currencyValueUSD = currencyService.read(2).getCurrencyValue();
        modelAndView.addObject("user", userAuth);
        modelAndView.addObject("currencyValueUSD", currencyValueUSD);
        modelAndView.addObject("currencies", currencyList);
        modelAndView.addObject("currencyID", currencyID);
        modelAndView.setViewName("currency");
        return modelAndView;
    }

    @PostMapping("/currency")
    public ModelAndView editCurrency(@ModelAttribute("user") User user,
                                     @ModelAttribute("currencyValueUSD") double currencyValue,
                                     @ModelAttribute("currencyID") int currencyID,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("error", "Некорректный ввод данных");
            return modelAndView;
        }
        Currency currency = findCurrencyById(currencyID);
        Currency currencyCorrectValue = currencyService.read(2);
        currencyCorrectValue.setCurrencyValue(currencyValue);
        currencyService.update(currencyCorrectValue);
        user.setCurrency(currency);
        modelAndView.setViewName("redirect:" + prevPage);
        userService.update(user);
        return modelAndView;
    }

    @GetMapping("/editPassword")
    public ModelAndView editPasswordPage(@AuthenticationPrincipal UserDetails userDetails) {
        String login = userDetails.getUsername();
        User userAuth = findUserByLogin(login);
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
        user.setCurrency(currency);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        modelAndView.setViewName("redirect:/editUser");
        userService.update(user);
        return modelAndView;
    }


}