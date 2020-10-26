package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.OtherCosts;
import projectCar.service.OhterCostsServiceImpl;
import projectCar.service.interfaces.IOtherCostsService;

@Controller
public class OtherCostsController extends MethodsCarForControllers {

    @Autowired
    private IOtherCostsService costsService = new OhterCostsServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    @GetMapping("/car/other/costs/{id}")
    public ModelAndView pageRepairs(@PathVariable("id") int id) {
        car = getCarWithWires(id);
        modelAndView.setViewName("car/other/costs");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("otherCosts", car.getOtherCosts());
        return modelAndView;
    }

    @GetMapping("/car/other/costs/create/{id}")
    public ModelAndView pageAddRepair(@PathVariable("id") int id) {
        car = getCarById(id);
        modelAndView.setViewName("car/other/costs/create");
        modelAndView.addObject("otherCosts", new OtherCosts());
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @PostMapping("/car/other/costs/create/{id}")
    public ModelAndView addRepair(@PathVariable("id") int id,
                                  @ModelAttribute("otherCosts") OtherCosts costs) {
        costs.setCar(
                getCarById(id));
        modelAndView.setViewName("redirect:/car/other/costs/{id}");
        costsService.add(costs);
        return modelAndView;
    }

    @GetMapping("/car/other/costs/edit/{id}")
    public ModelAndView pageEdit(@PathVariable("id")int id){
        OtherCosts otherCosts = costsService.read(id);
        modelAndView.setViewName("car/other/costs/edit");
        modelAndView.addObject("costs", otherCosts);
        modelAndView.addObject("car", otherCosts.getCar());
        return modelAndView;
    }

    @PostMapping("/car/other/costs/edit/{id}")
    public ModelAndView addEdit(@PathVariable("id")int id,
                                @ModelAttribute("costs") OtherCosts otherCosts,
                                @ModelAttribute("car") Car car){
        otherCosts.setCar(
                getCarById(car.getId()));
        modelAndView.setViewName("redirect:/car/other/costs/{id}");
        costsService.update(otherCosts);
        return modelAndView;
    }
}
