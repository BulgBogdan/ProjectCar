package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Currency;
import projectCar.entity.User;
import projectCar.service.interfaces.ICurrencyService;
import projectCar.service.interfaces.IUserService;

import javax.validation.Valid;

@Controller
public class SignUpController {

    private IUserService userService;

    private ICurrencyService currencyService;

    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public SignUpController(IUserService userService, ICurrencyService currencyService) {
        this.userService = userService;
        this.currencyService = currencyService;
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        modelAndView.setViewName("signup");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute("user") @Valid User user,
                                BindingResult bindingResult) {
        Currency currency = currencyService.read(1);
        user.setCurrency(currency);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("Errors", "Некорректный ввод данных");
            return modelAndView;
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            modelAndView.addObject("passwordError", "Пароли не совпадают");
            return modelAndView;
        }
        if (!userService.add(user)) {
            modelAndView.addObject("loginError", "Пользователь с таким логином существует," +
                    " введите другой");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}