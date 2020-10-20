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

    @GetMapping("car/createName")
    public String createCar(Model model){
        model.addAttribute("newCar", new Car());
        return "car/createName";
    }

    @PostMapping("car/createName")
    public String addCar(@ModelAttribute("newCar")Car car, @AuthenticationPrincipal UserDetails userDetails, Model model){
        String userName = userDetails.getUsername();
        User user = userService.findByLogin(userName);
        car.setUser(user);
        carService.add(car);
        return "redirect:car/parameter";
    }

    @GetMapping("car/view/{id}")
    public ModelAndView viewCar(@PathVariable("id") int id){
        Car car = carService.read(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("car/view");
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @GetMapping("car/edit/{id}")
    public ModelAndView editPage(@PathVariable("id")int id){
        Car car = carService.read(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("car/edit");
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @PostMapping("car/edit")
    public ModelAndView editCar(@ModelAttribute("car") Car car){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:car/View");
        carService.update(car);
        return modelAndView;
    }

}
