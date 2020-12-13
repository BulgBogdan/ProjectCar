package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.IFuelDAO;
import projectCar.entity.Car;
import projectCar.entity.Currency;
import projectCar.entity.Fuel;
import projectCar.entity.User;
import projectCar.service.interfaces.IFuelService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class FuelServiceImplTest {

    @Autowired
    private IFuelService fuelService;

    @MockBean
    private IFuelDAO fuelDAO;

    private Currency useCurrency() {
        Currency currency = new Currency();
        currency.setTitle("EUR");
        return currency;
    }

    private User useUser() {
        User userCreate = new User();
        userCreate.setLogin("login1234");
        userCreate.setPassword("password");
        userCreate.setEmail("user@gmail.com");
        userCreate.setFirstName("ivan");
        userCreate.setSecondName("ivanov");
        userCreate.setBirthday(Date.valueOf("2000-02-02"));
        userCreate.setCurrency(useCurrency());
        return userCreate;
    }

    private Car useCar() {
        Car carCreate = new Car();
        User user = useUser();
        carCreate.setNameCar("CarTest");
        carCreate.setMileage(1);
        carCreate.setUser(user);
        return carCreate;
    }

    private Fuel useFuel() {
        Fuel fuel = new Fuel();
        Car car = useCar();
        fuel.setLiterCost(1);
        fuel.setLiterValue(1);
        fuel.setSumm(1);
        fuel.setCar(car);
        return fuel;
    }

    private Fuel fuelTest;

    @Test
    void add() {
        fuelTest = useFuel();
        fuelService.add(fuelTest);
        Mockito.verify(fuelDAO, Mockito.times(1)).add(fuelTest);
    }

    @Test
    void update() {
        fuelTest = useFuel();
        fuelTest.setLiterCost(2);
        fuelService.update(fuelTest);
        Mockito.verify(fuelDAO, Mockito.times(1)).update(fuelTest);
    }

    @Test
    void read() {
        fuelTest = useFuel();
        Mockito.when(fuelDAO.read(1)).thenReturn(fuelTest);
        Fuel foundFuel = fuelService.read(1);
        assertEquals(1, foundFuel.getLiterValue());
        assertEquals(1, foundFuel.getLiterCost());
    }

    @Test
    void delete() {
        fuelTest = useFuel();
        fuelService.delete(fuelTest);
        Mockito.verify(fuelDAO, Mockito.times(1)).delete(fuelTest);
    }

    @Test
    void fuelCount() {
        Car car = useCar();
        int fuelsCount = 0;
        Mockito.when(fuelService.fuelCount(car.getId())).thenReturn(fuelsCount);
        assertEquals(fuelsCount, fuelDAO.fuelCount(car.getId()));
    }

    @Test
    void getAllByCar() {
        List<Fuel> fuelList = new ArrayList<>();
        Mockito.when(fuelDAO.getAll()).thenReturn(fuelList);
        List<Fuel> fuels = fuelService.getAll();
        assertEquals(fuelList, fuels);
    }
}