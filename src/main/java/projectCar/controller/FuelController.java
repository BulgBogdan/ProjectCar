package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import projectCar.service.CarServiceImpl;
import projectCar.service.FuelServiceImpl;
import projectCar.service.interfaces.ICarService;
import projectCar.service.interfaces.IFuelService;

@Controller
public class FuelController {

    @Autowired
    private ICarService carService = new CarServiceImpl();

    @Autowired
    private IFuelService fuelService = new FuelServiceImpl();


}
