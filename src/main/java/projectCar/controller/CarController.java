package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.User;
import projectCar.service.IUserService;
import projectCar.service.UserServiceImpl;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private IUserService userService = new UserServiceImpl();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(){
        List<User> users = userService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("userList", users);

        return modelAndView;
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }
}
