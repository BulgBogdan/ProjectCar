package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Currency;
import projectCar.entity.Fuel;
import projectCar.methods.Calculations;
import projectCar.methods.ServiceSolution;
import projectCar.service.FuelServiceImpl;
import projectCar.service.interfaces.IFuelService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class FuelController {

    @Autowired
    private ServiceSolution solutions;

    @Autowired
    private IFuelService fuelService = new FuelServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    private int page;

    @GetMapping("/car/fuel/{id}")
    public ModelAndView pageFuel(@PathVariable("id") int id,
                                 @RequestParam(defaultValue = "1") int page) {
        car = solutions.getCarWithWires(id);
        Currency currency = solutions.getCurrencyFromCarById(id);
        List<Fuel> fuelList = fuelService.getFuel(page, id);
        int fuelCount = fuelService.fuelCount(id);
        int pagesCount = (fuelCount + 9) / 10;
        modelAndView.setViewName("car/fuel");
        modelAndView.addObject("car", car);
        modelAndView.addObject("page",page);
        modelAndView.addObject("fuelCount", fuelCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("fuel", fuelList);
        this.page = page;
        double fuels = 0;
        for (Fuel fuel : car.getFuels()) {
            fuels = fuel.getSumm() + fuels;
        }
        if (currency.getTitle().equals("USD")) {
            double valueUSD = solutions.getCurrencyValueUSD();
            fuels = fuels / valueUSD;
            modelAndView.addObject("allFuelsCosts", fuels);
        } else {
            modelAndView.addObject("allFuelsCosts", fuels);
        }
        return modelAndView;
    }

    @GetMapping("/car/fuel/create/{id}")
    public ModelAndView pageCreateFuel(@PathVariable("id") int id) {
        car = solutions.getCarById(id);
        modelAndView.setViewName("car/fuel/create");
        modelAndView.addObject("car", car);
        modelAndView.addObject("fuel", new Fuel());
        return modelAndView;
    }

    @PostMapping("/car/fuel/create/{id}")
    public ModelAndView addFuel(@PathVariable("id") int id,
                                @ModelAttribute("fuel") Fuel fuel,
                                BindingResult result) {

        if (fuel.getSumm() == 0) {
            double sumFuel = Calculations.fuelSum(fuel.getLiterCost(), fuel.getLiterValue());
            fuel.setSumm(sumFuel);
        }

        if (fuel.getLiterValue() == 0) {
            double valueFuel = Calculations.fuelValue(fuel.getSumm(), fuel.getLiterCost());
            fuel.setLiterValue(valueFuel);
        }

        if ((fuel.getSumm() == 0) && (fuel.getLiterValue() == 0)) {
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            modelAndView.setViewName("redirect:/car/fuel/create/{id}");
            return modelAndView;
        }
        Date todayFuel = Date.valueOf(LocalDate.now());
        double distanceFuel = Calculations
                .fuelDistance(fuel.getLiterValue(), car.getParameters().getAverageRate());
        fuel.setDateFuel(todayFuel);
        fuel.setFuelDistance(distanceFuel);
        fuel.setCar(solutions.getCarById(id));
        modelAndView.setViewName("redirect:/car/fuel/{id}");
        fuelService.add(fuel);
        return modelAndView;
    }

    @GetMapping("/car/fuel/edit/{id}")
    public ModelAndView pageEditFuel(@PathVariable("id") int id) {
        Fuel fuel = fuelService.read(id);
        modelAndView.setViewName("car/fuel/edit");
        modelAndView.addObject("car", fuel.getCar());
        modelAndView.addObject("fuel", fuel);
        return modelAndView;
    }

    @PostMapping("/car/fuel/edit/{id}")
    public ModelAndView editFuel(@ModelAttribute("fuel") Fuel fuelEdit,
                                 BindingResult result,
                                 @PathVariable("id") int id) {
        if (result.hasErrors()) {
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            return modelAndView;
        }

        Fuel fuel = fuelService.read(id);
        int carId = fuel.getCar().getId();
        fuelEdit.setCar(solutions.getCarById(carId));
        boolean editSum = fuelEdit.getSumm() == fuel.getSumm();
        boolean editValue = fuelEdit.getLiterValue() == fuel.getLiterValue();

        if (!editSum) {
            double valueFuel = Calculations.fuelValue(fuelEdit.getSumm(), fuelEdit.getLiterCost());
            fuelEdit.setLiterValue(valueFuel);
        }

        if (!editValue) {
            double sumFuel = Calculations.fuelSum(fuelEdit.getLiterCost(), fuelEdit.getLiterValue());
            fuelEdit.setSumm(sumFuel);
        }

        if ((fuelEdit.getSumm() == 0) && (fuelEdit.getLiterValue() == 0)) {
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            modelAndView.setViewName("redirect:/car/fuel/edit/{id}");
            return modelAndView;
        }

        double distanceFuel = Calculations
                .fuelDistance(fuelEdit.getLiterValue(), fuel.getCar().getParameters().getAverageRate());
        fuelEdit.setFuelDistance(distanceFuel);
        modelAndView.addObject("carId", carId);
        modelAndView.setViewName("redirect:/car/fuel/{carId}");
        fuelService.update(fuelEdit);
        return modelAndView;
    }

    @GetMapping("/car/fuel/delete/{id}")
    public ModelAndView deleteFuel(@PathVariable("id") int id) {
        Fuel fuel = fuelService.read(id);
        modelAndView.addObject("idCar", fuel.getCar().getId());
        modelAndView.setViewName("redirect:/car/fuel/{idCar}");
        fuelService.delete(fuel);
        return modelAndView;
    }

    @GetMapping("/car/fuel/diagram/{id}")
    public ModelAndView diagramCostsFuel(@PathVariable("id") int id) {
        List<Fuel> fuelList = fuelService.getAllByCar(id);
        Car car = solutions.getCarById(id);
        modelAndView.addObject("fuelList", fuelList);
        modelAndView.addObject("car", car);
        modelAndView.setViewName("car/fuel/diagram");
        return modelAndView;
    }
}