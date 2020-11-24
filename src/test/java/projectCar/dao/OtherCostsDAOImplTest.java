package projectCar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.dao.interfaces.IOtherCostsDAO;
import projectCar.entity.Car;
import projectCar.entity.OtherCosts;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OtherCostsDAOImplTest {

    @Autowired
    private ICarDAO carDAO;

    @Autowired
    private IOtherCostsDAO costsDAO;

    private Car car;

    private OtherCosts otherCosts;

    @Test
    void add() {
        car = carDAO.read(1);
        OtherCosts cost = new OtherCosts();
        cost.setNameOtherCost("cost");
        cost.setDateCost(Date.valueOf("2000-01-01"));
        cost.setCost(1);
        cost.setCar(car);
        costsDAO.add(cost);
        int costId = cost.getId();
        otherCosts = costsDAO.read(costId);
        assertEquals(otherCosts.getNameOtherCost(), "cost");
    }

    @Test
    void update() {
        otherCosts = costsDAO.read(1);
        otherCosts.setNameOtherCost("testCost");
        costsDAO.update(otherCosts);
        String testCost = costsDAO.read(1).getNameOtherCost();
        assertEquals(testCost, "testCost");
    }

    @Test
    void read() {
        otherCosts = costsDAO.read(1);
        assertNotNull(otherCosts);
    }

    @Test
    void delete() {
        OtherCosts cost = costsDAO.read(1);
        costsDAO.delete(cost);
        OtherCosts costDelete = costsDAO.read(1);
        assertNotEquals(costDelete, cost);
    }

    @Test
    void otherCostsCount() {
        car = carDAO.read(1);
        int costsCount = costsDAO.otherCostsCount(car.getId());
        boolean notNullCostsCount = false;
        if (costsCount != 0) {
            notNullCostsCount = true;
        }
        assertTrue(notNullCostsCount);
    }

    @Test
    void getAll() {
        List<OtherCosts> costsList = costsDAO.getAll();
        assertNotNull(costsList);
    }
}