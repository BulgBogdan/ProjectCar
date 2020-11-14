package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Repair;
import projectCar.service.RepairServiceImpl;
import projectCar.service.interfaces.IRepairService;

import java.util.List;

@Controller
public class RepairController extends MethodsCarForControllers {

    @Autowired
    private IRepairService repairService = new RepairServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    private int page;

    private static int endMileageRepairs(int startMileage, int serviceMileage) {
        int mileage = startMileage + serviceMileage;
        return mileage;
    }

    @GetMapping("/car/repairs/{id}")
    public ModelAndView pageRepairs(@PathVariable("id") int id,
                                    @RequestParam(defaultValue = "1") int page) {
        car = getCarWithWires(id);
        List<Repair> repairList = repairService.getRepair(page, id);
        int repairCount = repairService.repairCount(id);
        int pagesCount = (repairCount + 9) / 10;
        modelAndView.setViewName("car/repairs");
        modelAndView.addObject("page",page);
        modelAndView.addObject("repairCount", repairCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("repairs", repairList);
        this.page = page;
        double repairs = 0;
        for (Repair repair : car.getRepairs()) {
            repairs = repair.getCostsRepair() + repairs;
        }
        modelAndView.addObject("allRepairsCosts",repairs);
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
                                  @ModelAttribute("repair") Repair repair,
                                  BindingResult result) {
        if (result.hasErrors()){
            errorIncorrectEnter();
            return modelAndView;
        }

        car = getCarById(id);
        int endMileageRepair = endMileageRepairs(repair.getBeginMileage(),repair.getServiceLife());
        repair.setEndMileage(endMileageRepair);
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
                                BindingResult result,
                                @ModelAttribute("car") Car car){
        if (result.hasErrors()){
            errorIncorrectEnter();
            return modelAndView;
        }

        Car carRepair = getCarById(car.getId());
        repair.setCar(carRepair);
        int endMileageRepair = endMileageRepairs(repair.getBeginMileage(),repair.getServiceLife());
        repair.setEndMileage(endMileageRepair);
        modelAndView.setViewName("redirect:/car/repairs/{id}");
        repairService.update(repair);
        return modelAndView;
    }

    @GetMapping("/car/repairs/delete/{id}")
    public ModelAndView deleteRepair(@PathVariable("id") int id){
        Repair repair = repairService.read(id);
        modelAndView.addObject("carId", repair.getCar().getId());
        modelAndView.setViewName("redirect:/car/repairs/{carId}");
        repairService.delete(repair);
        return modelAndView;
    }
}

