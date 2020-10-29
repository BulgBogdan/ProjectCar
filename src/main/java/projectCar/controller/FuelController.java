package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Fuel;
import projectCar.service.FuelServiceImpl;
import projectCar.service.interfaces.IFuelService;

@Controller
public class FuelController extends MethodsCarForControllers {

    @Autowired
    private IFuelService fuelService = new FuelServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    private static double fuelSumm(double liter, double value) {
        double summ = liter * value;
        return summ;
    }

    private static double fuelValue(double sum, double liter) {
        double value = sum / liter;
        return value;
    }

    private static int fuelDistance(double literValue, double averageRate) {
        double distance = (literValue/averageRate)*100;
        return (int) Math.round(distance);
    }

    @GetMapping("/car/fuel/{id}")
    public ModelAndView pageFuel(@PathVariable("id") int id) {
        car = getCarWithWires(id);
        modelAndView.setViewName("car/fuel");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("fuel", car.getFuels());
        return modelAndView;
    }

    @GetMapping("/car/fuel/create/{id}")
    public ModelAndView pageCreateFuel(@PathVariable("id") int id) {
        car = getCarById(id);
        modelAndView.setViewName("car/fuel/create");
        modelAndView.addObject("car", car);
        modelAndView.addObject("fuel", new Fuel());
        return modelAndView;
    }

    @PostMapping("/car/fuel/create/{id}")
    public ModelAndView addFuel(@PathVariable("id") int id,
                                @ModelAttribute("fuel") Fuel fuel) {
        car = getCarById(id);
        fuel.setCar(car);
        if (fuel.getSumm() == 0) {
            fuel.setSumm(
                    fuelSumm(fuel.getLiterCost(), fuel.getLiterValue()));
        }
        if (fuel.getLiterValue() == 0) {
            fuel.setLiterValue(
                    fuelValue(fuel.getSumm(), fuel.getLiterCost()));
        }
        fuel.setFuelDistance(
                fuelDistance(fuel.getLiterValue(), car.getParameters().getAverageRate()));
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
    public ModelAndView editFuel(@PathVariable("id") int id,
                                 @ModelAttribute("fuel") Fuel fuelEdit,
                                 @ModelAttribute("car") Car car) {
        Fuel fuel = fuelService.read(id);
        fuelEdit.setCar(
                getCarById(car.getId()));
        if (fuelEdit.getSumm() != fuel.getSumm()) {
            fuelEdit.setSumm(
                    fuelSumm(fuelEdit.getLiterCost(), fuelEdit.getLiterValue()));
        }
        if (fuelEdit.getLiterValue() != fuel.getLiterValue()) {
            fuelEdit.setLiterValue(
                    fuelValue(fuelEdit.getSumm(), fuelEdit.getLiterCost()));
        }
        fuelEdit.setFuelDistance(
                fuelDistance(fuelEdit.getLiterValue(), getCarById(car.getId()).getParameters().getAverageRate()));
        modelAndView.setViewName("redirect:/car/fuel/{id}");
        fuelService.update(fuelEdit);
        return modelAndView;
    }
}
