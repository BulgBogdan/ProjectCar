package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import projectCar.entity.Car;
import projectCar.service.CarServiceImpl;
import projectCar.service.interfaces.ICarService;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class MethodsCarForControllers {

    @Autowired
    ICarService carService = new CarServiceImpl();

    Car getCarById(int id){
        Car carById = carService.read(id);
        return carById;
    }

    Car getCarWithWires(int id){
        List<Car> listCars = carService.getLists(id);
        Car car = null;
        for (Car cars : listCars) {
            if (cars.getId() == id){
                car=cars;
            }
        }
        return car;
    }

    int amountOfDays(Date startDate, Date endDate) {
        long period = Math.abs(endDate.getTime() - startDate.getTime());
        int days = (int) TimeUnit.DAYS.convert(period, TimeUnit.MILLISECONDS);
        return days;
    }
}
