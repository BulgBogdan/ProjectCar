package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private ModelAndView modelAndView = new ModelAndView();

    private int page;

    @GetMapping("/user")
    public ModelAndView allUsers() {
        List<User> users = userService.getAll();
        modelAndView.setViewName("user");
        modelAndView.addObject("userList", users);
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView myPage(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam(defaultValue = "1") int page) {
        String login = userDetails.getUsername();
        User userAuth = userService.findByLogin(login);
        List<Car> carList = carService.getCars(page);
        int carsCount = carService.carsCount();
        int pagesCount = (carsCount + 9)/10;
        modelAndView.setViewName("home");
        modelAndView.addObject("page", page);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("user",userAuth);
        modelAndView.addObject("carList", carList);
        modelAndView.addObject("carsCount", carsCount);
        modelAndView.addObject("cars", new Car());
        this.page = page;
        return modelAndView;
    }

}