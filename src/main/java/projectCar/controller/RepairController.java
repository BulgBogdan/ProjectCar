package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Currency;
import projectCar.entity.Repair;
import projectCar.service.CurrencyServiceImpl;
import projectCar.service.RepairServiceImpl;
import projectCar.service.interfaces.ICurrencyService;
import projectCar.service.interfaces.IRepairService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RepairController extends MethodsCarForControllers {

    @Autowired
    private ICurrencyService currencyService = new CurrencyServiceImpl();

    @Autowired
    private IRepairService repairService = new RepairServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    private int page;

    private Currency getCurrencyByID(int id) {
        Currency currencyByID = currencyService.read(id);
        return currencyByID;
    }

    private static int endMileageRepairs(int startMileage, int serviceMileage) {
        int mileage = startMileage + serviceMileage;
        return mileage;
    }

    @GetMapping("/car/repairs/{id}")
    public ModelAndView pageRepairs(@PathVariable("id") int id,
                                    @RequestParam(defaultValue = "1") int page) {
        car = getCarWithWires(id);
        List<Repair> repairList = repairService.getRepair(page, id);
        double repairs = 0;
        List<Repair> greenMileage = new ArrayList<>();
        List<Repair> yellowMileage = new ArrayList<>();
        List<Repair> redMileage = new ArrayList<>();
        List<Repair> endService = new ArrayList<>();

        for (Repair repair : repairList) {
            int colorMileage = car.getMileage() - repair.getBeginMileage();

            if (colorMileage < (repair.getServiceLife() / 2)) {
                greenMileage.add(repair);
            }
            if ((colorMileage >= (repair.getServiceLife() / 2)) && (colorMileage < ((repair.getServiceLife() * 3) / 4))) {
                yellowMileage.add(repair);
            }
            if ((colorMileage >= (repair.getServiceLife() * 3) / 4) && (colorMileage < repair.getServiceLife())) {
                redMileage.add(repair);
            } else if (colorMileage >= repair.getServiceLife()) {
                endService.add(repair);
            }
            repairs = repair.getCostsRepair() + repairs;
        }

        modelAndView.addObject("greenMileage", greenMileage);
        modelAndView.addObject("yellowMileage", yellowMileage);
        modelAndView.addObject("redMileage", redMileage);
        modelAndView.addObject("endService", endService);

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

        //currency = BYN
        if (car.getUser().getCurrency().getTitle().equals("BYN")) {
            modelAndView.addObject("allRepairsCosts", repairs);
        }
        //currency = USD
        else {
            double repairValueByUSD = repairs / 2.6;
            String repairCostsByUSD = String.format("%.2f", repairValueByUSD);
            modelAndView.addObject("allRepairsCosts", repairCostsByUSD);
        }
        return modelAndView;
    }

    @GetMapping("/car/repairs/create/{id}")
    public ModelAndView pageAddRepair(@PathVariable("id") int id) {
        car = getCarById(id);
        int currencyID = car.getUser().getCurrency().getId();
        Currency currency = getCurrencyByID(currencyID);
        modelAndView.setViewName("car/repairs/create");
        modelAndView.addObject("repair", new Repair());
        modelAndView.addObject("car", car);
        modelAndView.addObject("currency", currency);
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

