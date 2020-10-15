package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projectCar.entity.User;
import projectCar.service.ISecurityService;
import projectCar.service.UserServiceImpl;
import projectCar.service.interfaces.IUserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService=new UserServiceImpl();

    @Autowired
    private ISecurityService securityService;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("Errors", "Uncorrect enter");
            return "signup";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            model.addAttribute("passwordError", "Password not complaining");
            return "signup";
        }
        if (!userService.add(user)){
            model.addAttribute("loginError", "User exist");
            return "signup";
        }
        securityService.autoLogin(user.getLogin(),user.getPassword());
        return "redirect:/";
    }



}
