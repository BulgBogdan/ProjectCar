package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Currency;
import projectCar.entity.OtherCosts;
import projectCar.service.CurrencyServiceImpl;
import projectCar.service.OtherCostsServiceImpl;
import projectCar.service.interfaces.ICurrencyService;
import projectCar.service.interfaces.IOtherCostsService;

import java.util.List;

@Controller
public class OtherCostsController extends MethodsCarForControllers {

    @Autowired
    private ICurrencyService currencyService = new CurrencyServiceImpl();

    @Autowired
    private IOtherCostsService costsService = new OtherCostsServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    private int page;

    @GetMapping("/car/other/costs/{id}")
    public ModelAndView pageCosts(@PathVariable("id") int id,
                                  @RequestParam(defaultValue = "1") int page) {
        car = getCarWithWires(id);
        List<OtherCosts> costsList = costsService.getOtherCosts(page, id);
        int costsCount = costsService.otherCostsCount(id);
        int pagesCount = (costsCount + 9) / 10;
        double valueUSD = getCurrencyValueUSD();
        modelAndView.setViewName("car/other/costs");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("otherCosts", costsList);
        modelAndView.addObject("page",page);
        modelAndView.addObject("costsCount", costsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("valueUSD", valueUSD);
        this.page = page;
        double costs = getCostsByOtherCost(car.getOtherCosts());
        //currency = BYN
        if (car.getUser().getCurrency().getTitle().equals("BYN")) {
            modelAndView.addObject("sumAllCosts", costs);
        }
        //currency = USD
        else {
            double costValueByUSD = costs / valueUSD;
            modelAndView.addObject("sumAllCosts", costValueByUSD);
        }
        return modelAndView;
    }

    @GetMapping("/car/other/costs/create/{id}")
    public ModelAndView pageAddCost(@PathVariable("id") int id) {
        car = getCarById(id);
        Currency currency = getCurrencyFromCarById(id);
        modelAndView.setViewName("car/other/costs/create");
        modelAndView.addObject("otherCosts", new OtherCosts());
        modelAndView.addObject("car", car);
        modelAndView.addObject("currency", currency);
        return modelAndView;
    }

    @PostMapping("/car/other/costs/create/{id}")
    public ModelAndView addCost(@PathVariable("id") int id,
                                @ModelAttribute("otherCosts") OtherCosts costs,
                                BindingResult result) {
        if (result.hasErrors()){
            errorIncorrectEnter();
            return modelAndView;
        }

        Car car = getCarById(id);
        costs.setCar(car);
        modelAndView.setViewName("redirect:/car/other/costs/{id}");

        Currency currency = getCurrencyFromCarById(id);
        if (currency.getTitle().equals("USD")) {
            double priceOtherCost = getValueByUSD(costs.getCost());
            costs.setCost(priceOtherCost);
            costsService.add(costs);
        } else {
            costsService.add(costs);
        }
        return modelAndView;
    }

    @GetMapping("/car/other/costs/edit/{id}")
    public ModelAndView pageEditCost(@PathVariable("id") int id) {
        OtherCosts otherCosts = costsService.read(id);
        modelAndView.setViewName("car/other/costs/edit");
        double valueUSD = getCurrencyValueUSD();
        if (otherCosts.getCar().getUser().getCurrency().getTitle().equals("USD")) {
            otherCosts.setCost(otherCosts.getCost() / valueUSD);
            modelAndView.addObject("costs", otherCosts);
        } else {
            modelAndView.addObject("costs", otherCosts);
        }
        modelAndView.addObject("car", otherCosts.getCar());
        return modelAndView;
    }

    @PostMapping("/car/other/costs/edit/{id}")
    public ModelAndView addEditCost(@PathVariable("id") int id,
                                    @ModelAttribute("costs") OtherCosts otherCosts,
                                    BindingResult result){
        if (result.hasErrors()){
            errorIncorrectEnter();
            return modelAndView;
        }

        OtherCosts cost = costsService.read(id);
        int currencyID = cost.getCar().getUser().getCurrency().getId();
        otherCosts.setCar(costsService.read(id).getCar());
        modelAndView.setViewName("redirect:/car/other/costs/{id}");
        Currency currency = currencyService.read(currencyID);
        if (currency.getTitle().equals("BYN")) {
            costsService.update(otherCosts);
        } else {
            double priceOtherCost = getValueByUSD(otherCosts.getCost());
            otherCosts.setCost(priceOtherCost);
            costsService.update(otherCosts);
        }
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

    private double getCurrencyValueUSD() {
        return currencyService.read(2).getCurrencyValue();
    }

    private double getCostsByOtherCost(List<OtherCosts> otherCostsList) {
        double costs = 0;
        for (OtherCosts listCosts : otherCostsList) {
            costs = costs + listCosts.getCost();
        }
        return costs;
    }

    private Currency getCurrencyFromCarById(int id) {
        Car car = carService.read(id);
        return car.getUser().getCurrency();
    }

    private double getValueByUSD(double valueByBYN) {
        return getCurrencyValueUSD() * valueByBYN;
    }

}