package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import projectCar.entity.Car;
import projectCar.service.CarServiceImpl;
import projectCar.service.interfaces.ICarService;

import java.util.List;

@Controller
public class MethodsCarForControllers {

    @Autowired
    ICarService carService = new CarServiceImpl();

    Car getCarById(int id){
        return carService.read(id);
    }

    Car getCarWithWires(int id){
        List<Car> listCars = carService.getListsForCostsByID(id);
        Car car = null;
        for (Car cars : listCars) {
            if (cars.getId() == id){
                car=cars;
            }
        }
        return car;
    }
}