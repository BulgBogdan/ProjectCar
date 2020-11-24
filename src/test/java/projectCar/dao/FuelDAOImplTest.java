package projectCar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.dao.interfaces.IFuelDAO;
import projectCar.entity.Car;
import projectCar.entity.Fuel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FuelDAOImplTest {

    @Autowired
    private ICarDAO carDAO;

    @Autowired
    private IFuelDAO fuelDAO;

    private Car car;

    private Fuel fuel;

    @Test
    void add() {
        Fuel fuelTest = new Fuel();
        car = carDAO.read(1);
        fuelTest.setLiterCost(1);
        fuelTest.setLiterValue(1);
        fuelTest.setCar(car);
        fuelDAO.add(fuelTest);
        int fuelId = fuelTest.getId();
        fuel = fuelDAO.read(fuelId);
        assertEquals(fuel.getLiterValue(), 1);
    }

    @Test
    void update() {
        fuel = fuelDAO.read(1);
        fuel.setLiterCost(2);
        fuelDAO.update(fuel);
        double testFuel = fuelDAO.read(1).getLiterCost();
        assertEquals(testFuel, 2);
    }

    @Test
    void read() {
        fuel = fuelDAO.read(1);
        assertNotNull(fuel);
    }

    @Test
    void delete() {
        fuel = fuelDAO.read(1);
        fuelDAO.delete(fuel);
        Fuel fuelDelete = fuelDAO.read(1);
        assertNotEquals(fuelDelete, fuel);
    }

    @Test
    void fuelCount() {
        car = carDAO.read(1);
        int fuelCount = fuelDAO.fuelCount(car.getId());
        boolean notNullCountFuel = false;
        if (fuelCount != 0) {
            notNullCountFuel = true;
        }
        assertTrue(notNullCountFuel);
    }

    @Test
    void getAll() {
        List<Fuel> fuelList = fuelDAO.getAll();
        assertNotNull(fuelList);
    }

    @Test
    void getAllByCar() {
        car = carDAO.read(1);
        int carId = car.getId();
        List<Fuel> fuelList = fuelDAO.getAllByCar(carId);
        assertNotNull(fuelList);
    }
}