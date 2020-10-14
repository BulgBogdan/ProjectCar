package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.User;
import projectCar.service.interfaces.IUserService;
import projectCar.service.UserServiceImpl;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private IUserService userService = new UserServiceImpl();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView allUsers(){
        List<User> users = userService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("userList", users);

        return modelAndView;
    }
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public ModelAndView editPage(@PathVariable("id") int id){
//        User user = userService.read(id);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("editpage");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }
//    @RequestMapping(value = "/edit",method = RequestMethod.POST)
//    public ModelAndView editUser(@ModelAttribute("user") User user){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        userService.update(user);
//        return modelAndView;
//    }
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public ModelAndView addPage(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("editpage");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    public ModelAndView addUser(@ModelAttribute("user") User user) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        userService.add(user);
//        return modelAndView;
//    }
}
