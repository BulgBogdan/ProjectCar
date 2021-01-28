package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Parameter;
import projectCar.methods.ServiceSolution;
import projectCar.service.interfaces.IParameterService;

@Controller
public class ParameterController {

    private ServiceSolution carService;

    private IParameterService parameterService;

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    @Autowired
    public ParameterController(ServiceSolution carService, IParameterService parameterService) {
        this.carService = carService;
        this.parameterService = parameterService;
    }

    @GetMapping("car/parameters/{id}")
    public ModelAndView parameterCar(@PathVariable("id") int id) {
        car = carService.getCarById(id);
        modelAndView.setViewName("car/parameters");
        modelAndView.addObject("parameter", new Parameter());
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @PostMapping("car/parameters/{id}")
    public ModelAndView parameterCar(@ModelAttribute("parameter") Parameter parameter,
                                     BindingResult result,
                                     @PathVariable("id") int id) {
        if (result.hasErrors()){
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            return modelAndView;
        }

        car = carService.getCarById(id);
        modelAndView.setViewName("redirect:/car/view/{id}");
        parameter.setCar(car);
        parameterService.add(parameter);
        return modelAndView;
    }

    @GetMapping("car/parameters/edit/{id}")
    public ModelAndView editParameter(@PathVariable("id") int id) {
        car = carService.getCarById(id);
        Parameter parameter = car.getParameters();
        modelAndView.setViewName("car/parameters/edit");
        modelAndView.addObject("parameter", parameter);
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @PostMapping("car/parameters/edit/{id}")
    public ModelAndView editParameter(@ModelAttribute("parameter") Parameter parameter,
                                      BindingResult result,
                                      @PathVariable("id") int id) {
        if (result.hasErrors()){
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            return modelAndView;
        }

        parameter.setCar(carService.getCarById(id));
        modelAndView.setViewName("redirect:/car/view/{id}");
        parameterService.update(parameter);
        return modelAndView;
    }
}