package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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

    private ModelAndView modelAndView = new ModelAndView();

    @GetMapping("car/create")
    public ModelAndView createCar(){
        modelAndView.setViewName("car/create");
        modelAndView.addObject("newCar", new Car());
        return modelAndView;
    }

    @PostMapping("car/create")
    public ModelAndView addCar(@ModelAttribute("newCar")Car car, @AuthenticationPrincipal UserDetails userDetails){
        String userName = userDetails.getUsername();
        User user = userService.findByLogin(userName);
        car.setUser(user);
        int id = car.getId();
        modelAndView.setViewName("car/parameters");
        carService.add(car);
        return modelAndView;
    }

    @GetMapping("car/view/{id}")
    public ModelAndView viewCar(@PathVariable("id") int id){
        Car car = carService.read(id);
        modelAndView.setViewName("car/view");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameter", car.getParameters());
        return modelAndView;
    }

    @GetMapping("car/edit/{id}")
    public ModelAndView editPage(@PathVariable("id")int id){
        Car car = carService.read(id);
        modelAndView.setViewName("car/edit");
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @PostMapping("car/edit/{id}")
    public ModelAndView editCar(@ModelAttribute("car") Car car,
                                @AuthenticationPrincipal UserDetails userDetails,
                                @PathVariable("id")int id){
        String userName = userDetails.getUsername();
        User user = userService.findByLogin(userName);
        car.setUser(user);
        modelAndView.setViewName("redirect:/car/view/{id}");
        carService.update(car);
        return modelAndView;
    }

    @GetMapping("car/delete/{id}")
    public ModelAndView deletePage(@PathVariable("id")int id){
        Car car = carService.read(id);
        modelAndView.setViewName("redirect:/");
        carService.delete(car);
        return modelAndView;
    }


}
