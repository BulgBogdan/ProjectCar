package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Parameter;
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

    @GetMapping("car/parameter/{id}")
    public ModelAndView parameterCar(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("car/parameter");
        modelAndView.addObject("parameter", new Parameter());
        return modelAndView;
    }

    @PostMapping("car/parameter/{id}")
    public ModelAndView parameterCar(@ModelAttribute("parameter") Parameter parameter,@PathVariable("id") int id){
        Car car = carService.read(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("personalCabinet");
        parameter.setCar(car);
        parameterService.add(parameter);
        return modelAndView;
    }

    @GetMapping("car/editParameter/{id}")
    public ModelAndView editParameter(@PathVariable("id") int id){
        Car car = carService.read(id);
        Parameter parameter = car.getParameters();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("car/editParameter");
        modelAndView.addObject("parameter", parameter);
        return modelAndView;
    }

    @PostMapping("car/editParameter/{id}")
    public ModelAndView editParameter(@ModelAttribute("parameter") Parameter parameter, @PathVariable("id") int id){
        Car car = carService.read(id);
        parameter.setCar(car);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("car/view");
        parameterService.update(parameter);
        return modelAndView;
    }
}
