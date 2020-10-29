package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Repair;
import projectCar.service.RepairServiceImpl;
import projectCar.service.interfaces.IRepairService;

@Controller
public class RepairController extends MethodsCarForControllers {

    @Autowired
    private IRepairService repairService = new RepairServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    private static int endMileageRepairs(int startMileage, int serviceMileage) {
        int mileage = startMileage + serviceMileage;
        return mileage;
    }

    @GetMapping("/car/repairs/{id}")
    public ModelAndView pageRepairs(@PathVariable("id") int id) {
        car = getCarWithWires(id);
        modelAndView.setViewName("car/repairs");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("repairs", car.getRepairs());
        return modelAndView;
    }

    @GetMapping("/car/repairs/create/{id}")
    public ModelAndView pageAddRepair(@PathVariable("id") int id) {
        car = getCarById(id);
        modelAndView.setViewName("car/repairs/create");
        modelAndView.addObject("repair", new Repair());
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @PostMapping("/car/repairs/create/{id}")
    public ModelAndView addRepair(@PathVariable("id") int id,
                                  @ModelAttribute("repair") Repair repair) {
        car = getCarById(id);
        repair.setEndMileage(
                endMileageRepairs(repair.getBeginMileage(),repair.getServiceLife()));
        repair.setCar(car);
        modelAndView.setViewName("redirect:/car/repairs/{id}");
        repairService.add(repair);
        return modelAndView;
    }

    @GetMapping("/car/repairs/edit/{id}")
    public ModelAndView pageEdit(@PathVariable("id")int id){
        Repair repair = repairService.read(id);
        modelAndView.setViewName("car/repairs/edit");
        modelAndView.addObject("repair", repair);
        modelAndView.addObject("car", repair.getCar());
        return modelAndView;
    }

    @PostMapping("/car/repairs/edit/{id}")
    public ModelAndView addEdit(@PathVariable("id")int id,
                                @ModelAttribute("repair") Repair repair,
                                @ModelAttribute("car") Car car){
        repair.setCar(
                getCarById(car.getId()));
        repair.setEndMileage(
                endMileageRepairs(repair.getBeginMileage(),repair.getServiceLife()));
        modelAndView.setViewName("redirect:/car/repairs/{id}");
        repairService.update(repair);
        return modelAndView;
    }
}

