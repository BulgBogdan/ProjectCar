package projectCar.methods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projectCar.entity.Car;
import projectCar.entity.Currency;
import projectCar.service.CarServiceImpl;
import projectCar.service.CurrencyServiceImpl;
import projectCar.service.interfaces.ICarService;
import projectCar.service.interfaces.ICurrencyService;

import java.util.List;

@Component
public final class ServiceSolution {

    @Autowired
    ICarService carService = new CarServiceImpl();

    @Autowired
    private ICurrencyService currencyService = new CurrencyServiceImpl();

    public double getCurrencyValueUSD() {
        return currencyService.read(2).getCurrencyValue();
    }

    public Currency getCurrencyById(int id) {
        return currencyService.read(id);
    }

    public double getValueByUSD(double valueByBYN) {
        return getCurrencyValueUSD() * valueByBYN;
    }

    public List<Currency> getAllCurrency() {
        return currencyService.getAll();
    }

    public Car getCarById(int id) {
        return carService.read(id);
    }

    public Currency getCurrencyFromCarById(int id) {
        Car car = carService.read(id);
        return car.getUser().getCurrency();
    }

    public Car getCarWithWires(int id) {
        List<Car> listCars = carService.getListsForCostsByID(id);
        Car car = null;
        for (Car cars : listCars) {
            if (cars.getId() == id) {
                car = cars;
            }
        }
        return car;
    }

    public List<Car> getUserCars(int page, int userId) {
        return carService.getCars(page, userId);
    }

    public int getCountCarsByUser(int userId) {
        return carService.carsCount(userId);
    }

    public void createCar(Car car) {
        carService.add(car);
    }

    public List<Car> getListAllCostsByCar(int id) {
        return carService.getListsForCostsByID(id);
    }

    public void updateCar(Car car) {
        carService.update(car);
    }

    public void deleteCar(Car car) {
        carService.delete(car);
    }

}