package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.entity.Car;
import projectCar.entity.User;
import projectCar.service.interfaces.ICarService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class CarServiceImplTest {

    @Autowired
    private ICarService carService;

    @MockBean
    private ICarDAO carDAO;

    private Car useCar() {
        Car carCreate = new Car();
        User user = useUser();
        carCreate.setNameCar("CarTest");
        carCreate.setMileage(1);
        carCreate.setUser(user);
        return carCreate;
    }

    private User useUser() {
        User userCreate = new User();
        userCreate.setLogin("login1234");
        userCreate.setPassword("password");
        userCreate.setEmail("user@gmail.com");
        userCreate.setFirstName("ivan");
        userCreate.setSecondName("ivanov");
        userCreate.setBirthday(Date.valueOf("2000-02-02"));
        return userCreate;
    }

    private Car car;

    @Test
    void addCar() {
        car = useCar();
        carService.add(car);
        Mockito.verify(carDAO, Mockito.times(1)).add(car);
    }

    @Test
    void updateCar() {
        car = useCar();
        car.setNameCar("TestCar");
        carService.update(car);
        Mockito.verify(carDAO, Mockito.times(1)).update(car);
    }

    @Test
    void readCar() {
        car = useCar();
        Mockito.when(carDAO.read(1)).thenReturn(car);
        Car foundCar = carService.read(1);
        assertEquals("CarTest", foundCar.getNameCar());
    }

    @Test
    void deleteCar() {
        car = useCar();
        carService.delete(car);
        Mockito.verify(carDAO, Mockito.times(1)).delete(car);
    }

    @Test
    void carsCountCar() {
        User user = useUser();
        int carsCount = 0;
        Mockito.when(carService.carsCount(user.getId())).thenReturn(carsCount);
        assertEquals(carsCount, carDAO.carsCount(user.getId()));
    }

    @Test
    void getListsCar() {
        List<Car> carList = new ArrayList<>();
        int idCar = useCar().getId();
        Mockito.when(carDAO.getLists(idCar)).thenReturn(carList);
        List<Car> cars = carService.getLists(idCar);
        assertEquals(cars, carList);
    }

    @Test
    void getAllCars() {
        List<Car> carList = new ArrayList<>();
        Mockito.when(carDAO.getAll()).thenReturn(carList);
        List<Car> cars = carService.getAll();
        assertEquals(cars, carList);
    }

}