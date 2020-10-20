package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.service.CarServiceImpl;
import projectCar.service.ParameterServiceImpl;
import projectCar.service.interfaces.ICarService;
import projectCar.service.interfaces.IParameterService;

@Controller
public class ParameterController {

    @Autowired
    private ICarService carService = new CarServiceImpl();

    @Autowired
    private IParameterService parameterService = new ParameterServiceImpl();

    @GetMapping("car/parameter")
    public ModelAndView parameterCar(){
        return null;
    }
}
