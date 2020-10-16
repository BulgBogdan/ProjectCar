package projectCar.controller;

import org.springframework.stereotype.Controller;

@Controller
public class CarController {


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
