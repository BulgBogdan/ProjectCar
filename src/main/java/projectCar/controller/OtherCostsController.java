package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.OtherCosts;
import projectCar.service.OtherCostsServiceImpl;
import projectCar.service.interfaces.IOtherCostsService;

import java.util.List;

@Controller
public class OtherCostsController extends MethodsCarForControllers {

    @Autowired
    private IOtherCostsService costsService = new OtherCostsServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    private int page;

    @GetMapping("/car/other/costs/{id}")
    public ModelAndView pageRepairs(@PathVariable("id") int id,
                                    @RequestParam(defaultValue = "1") int page) {
        car = getCarWithWires(id);
        List<OtherCosts> costsList = costsService.getOtherCosts(page, id);
        int costsCount = costsService.otherCostsCount(id);
        int pagesCount = (costsCount + 9) / 10;
        modelAndView.setViewName("car/other/costs");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("otherCosts", costsList);
        modelAndView.addObject("page",page);
        modelAndView.addObject("costsCount", costsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        this.page = page;
        double costs = 0;
        for (OtherCosts listCosts : car.getOtherCosts()) {
            costs = costs + listCosts.getCost();
        }
        modelAndView.addObject("sumAllCosts", costs);
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
                                  @ModelAttribute("otherCosts") OtherCosts costs,
                                  BindingResult result) {
        if (result.hasErrors()){
            errorIncorrectEnter();
            return modelAndView;
        }
        costs.setCar(getCarById(id));
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
                                BindingResult result){
        if (result.hasErrors()){
            errorIncorrectEnter();
            return modelAndView;
        }
        otherCosts.setCar(costsService.read(id).getCar());
        modelAndView.setViewName("redirect:/car/other/costs/{id}");
        costsService.update(otherCosts);
        return modelAndView;
    }

    @GetMapping("/car/other/costs/delete/{id}")
    public ModelAndView deleteOtherCost(@PathVariable("id") int id){
        OtherCosts cost = costsService.read(id);
        modelAndView.addObject("carId", cost.getCar().getId());
        modelAndView.setViewName("redirect:/car/other/costs/{carId}");
        costsService.delete(cost);
        return modelAndView;
    }
}
