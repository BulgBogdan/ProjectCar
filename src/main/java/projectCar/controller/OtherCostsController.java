package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.service.OhterCostsServiceImpl;
import projectCar.service.interfaces.IOtherCostsService;

@Controller
public class OtherCostsController extends MethodsCarForControllers {

    @Autowired
    private IOtherCostsService costsService = new OhterCostsServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

}
