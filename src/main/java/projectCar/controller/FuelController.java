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
        double distance = (literValue / averageRate) * 100;
        return (int) Math.round(distance);
    }

    @GetMapping("/car/fuel/{id}")
    public ModelAndView pageFuel(@PathVariable("id") int id) {
        car = getCarWithWires(id);
        modelAndView.setViewName("car/fuel");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("fuel", car.getFuels());
        double fuels = 0;
        for (Fuel fuel : car.getFuels()) {
            fuels = fuel.getSumm() + fuels;
        }
        modelAndView.addObject("allFuelsCosts", fuels);
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
                                @ModelAttribute("fuel") Fuel fuel,
                                BindingResult result) {
        if (result.hasErrors()){
            errorIncorrectEnter();
        }
        car = getCarById(id);

        if (fuel.getSumm() == 0) {
            double sumFuel = fuelSumm(fuel.getLiterCost(), fuel.getLiterValue());
            fuel.setSumm(sumFuel);
        }
        if (fuel.getLiterValue() == 0) {
            double valueFuel = fuelValue(fuel.getSumm(), fuel.getLiterCost());
            fuel.setLiterValue(valueFuel);
        }
        double distanceFuel = fuelDistance(fuel.getLiterValue(), car.getParameters().getAverageRate());
        fuel.setFuelDistance(distanceFuel);
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
                                 BindingResult result,
                                 @ModelAttribute("car") Car car) {
        if (result.hasErrors()){
            errorIncorrectEnter();
        }
        Fuel fuel = fuelService.read(id);
        int carId = car.getId();

        fuelEdit.setCar(getCarById(carId));
        if (fuelEdit.getSumm() != fuel.getSumm()) {
            double sumFuel = fuelSumm(fuelEdit.getLiterCost(), fuelEdit.getLiterValue());
            fuelEdit.setSumm(sumFuel);
        }
        if (fuelEdit.getLiterValue() != fuel.getLiterValue()) {
            double valueFuel = fuelValue(fuelEdit.getSumm(), fuelEdit.getLiterCost());
            fuelEdit.setLiterValue(valueFuel);
        }
        double distanceFuel = fuelDistance(fuelEdit.getLiterValue(), getCarById(car.getId()).getParameters().getAverageRate());
        fuelEdit.setFuelDistance(distanceFuel);
        modelAndView.setViewName("redirect:/car/fuel/{id}");
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
}
