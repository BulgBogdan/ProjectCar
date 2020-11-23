package projectCar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.dao.interfaces.IUserDAO;
import projectCar.entity.Car;
import projectCar.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CarDAOImplTest {

    @Autowired
    private ICarDAO carDAO;

    @Autowired
    private IUserDAO userDAO;

    private Car car;

    private User user;

    @Test
    void addCar() {
        Car carCreate = new Car();
        user = userDAO.read(1);
        carCreate.setNameCar("CarTest");
        carCreate.setMileage(1);
        carCreate.setUser(user);
        carDAO.add(carCreate);
        int carId = carCreate.getId();
        car = carDAO.read(carId);
        assertEquals(car.getNameCar(), "CarTest");
        assertEquals(car.getMileage(), 1);
    }

    @Test
    void updateCar() {
        car = carDAO.read(1);
        car.setNameCar("testCar");
        carDAO.update(car);
        String testCar = carDAO.read(1).getNameCar();
        assertEquals(testCar, "testCar");
    }

    @Test
    void readCar() {
        car = carDAO.read(1);
        assertNotNull(car);
    }

    @Test
    void deleteCar() {
        Car carRead = carDAO.read(1);
        carDAO.delete(carRead);
        Car carDelete = carDAO.read(1);
        assertNotEquals(carRead, carDelete);
    }

    @Test
    void carsCountCar() {
        user = userDAO.read(1);
        int carsCount = carDAO.carsCount(user.getId());
        boolean notNullCountCar = false;
        if (carsCount != 0) {
            notNullCountCar = true;
        }
        assertTrue(notNullCountCar);
    }

    @Test
    void getListsCar() {
        car = carDAO.read(1);
        List<Car> carList = carDAO.getLists(car.getId());
        assertNotNull(carList);
    }

    @Test
    void getAllCars() {
        List<Car> carList = carDAO.getAll();
        assertNotNull(carList);
    }
}