package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.User;
import projectCar.service.CarServiceImpl;
import projectCar.service.UserServiceImpl;
import projectCar.service.interfaces.ICarService;
import projectCar.service.interfaces.IUserService;

@Controller
public class CarController {

    @Autowired
    private IUserService userService = new UserServiceImpl();

    @Autowired
    private ICarService carService = new CarServiceImpl();

    @GetMapping("/createCar")
    public String createCar(Model model){
        model.addAttribute("newCar", new Car());
        return "createcar";
    }

    @PostMapping("/createCar")
    public String addCar(@ModelAttribute("newCar")Car car, @AuthenticationPrincipal UserDetails userDetails, Model model){
        String userName = userDetails.getUsername();
        User user = userService.findByLogin(userName);
        car.setUser(user);
        carService.add(car);
        return "redirect:/home";
    }

    @GetMapping("/carView/{id}")
    public ModelAndView viewCar(@PathVariable("id") int id){
        Car car = carService.read(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("carview");
        modelAndView.addObject("car", car);
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
