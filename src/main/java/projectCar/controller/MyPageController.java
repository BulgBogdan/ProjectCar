package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.User;
import projectCar.service.CarServiceImpl;
import projectCar.service.UserServiceImpl;
import projectCar.service.interfaces.ICarService;
import projectCar.service.interfaces.IUserService;

import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    private IUserService userService = new UserServiceImpl();

    @Autowired
    private ICarService carService = new CarServiceImpl();

    @GetMapping("/user")
    public ModelAndView allUsers() {
        List<User> users = userService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("userList", users);

        return modelAndView;
    }

    @GetMapping("/personalCabinet")
    public ModelAndView myPage(@AuthenticationPrincipal UserDetails userDetails) {
        String login = userDetails.getUsername();
        User userAuth = userService.findByLogin(login);
        List<Car> carList = userAuth.getCars();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("personalCabinet");
        modelAndView.addObject("user",userAuth);
        modelAndView.addObject("carList", carList);
        return modelAndView;
    }

}
