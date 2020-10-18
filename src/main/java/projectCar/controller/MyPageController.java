package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.User;
import projectCar.service.UserServiceImpl;
import projectCar.service.interfaces.IUserService;

import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    private IUserService userService = new UserServiceImpl();

    @GetMapping("/user")
    public ModelAndView allUsers() {
        List<User> users = userService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("userList", users);

        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView myPage(@AuthenticationPrincipal UserDetails user) {
        String login = user.getUsername();
        User userAuth = userService.findByLogin(login);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user",userAuth);
        return modelAndView;
    }


}
