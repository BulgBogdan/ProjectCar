package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projectCar.entity.User;
import projectCar.service.UserServiceImpl;
import projectCar.service.interfaces.IUserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService=new UserServiceImpl();

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "signup";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            model.addAttribute("passwordError", "Password not complaining");
            return "signup";
        }
        userService.add(user);
        if (userService == null){
            model.addAttribute("loginError", "User exist");
            return "signup";
        }
        return "redirect:/";
    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model){
//        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()){
//            return "signup";
//        }
//        userService.add(user);
//        securityService.autoLogin(user.getLogin(),user.getPassword());
//        return "redirect:/";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model, String error, String logout){
//        if (error!=null){
//            model.addAttribute("error","Username or Password is incorrect.");
//        }
//        if (logout!=null){
//            model.addAttribute("message", "Logout out successfully.");
//        }
//        return "login";
//    }


}
