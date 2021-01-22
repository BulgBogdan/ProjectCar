package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.service.CarServiceImpl;
import projectCar.service.interfaces.ICarService;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    int amountOfDays(Date startDate, Date endDate) {
        long period = Math.abs(endDate.getTime() - startDate.getTime());
        return (int) TimeUnit.DAYS.convert(period, TimeUnit.MILLISECONDS);
    }

    int amountOfMonths(Date startDate, Date endDate) {
        return (int) ChronoUnit.MONTHS.between(startDate.toLocalDate(), endDate.toLocalDate());
    }

    ModelAndView errorIncorrectEnter(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("Errors", "Некоректный ввод данных");
        return modelAndView;
    }
}
