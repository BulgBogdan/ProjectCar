package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import projectCar.service.interfaces.IUserService;

@Controller
public class SignUpController {

    @Autowired
    private IUserService userService;

//    @RequestMapping(value = "/signup", method = RequestMethod.GET)
//    public ModelAndView pageSignUpUser(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("signup");
//        modelAndView.addObject("user", new User());
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/signupProcess", method = RequestMethod.POST)
//    public ModelAndView addUser(@ModelAttribute("user") User user){
//        ModelAndView modelAndView = new ModelAndView();
//        userService.add(user);
//        modelAndView.setViewName("redirect:/");
//        return modelAndView;
//    }

}
