package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.CarDAOImpl;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.dao.interfaces.IOtherCostsDAO;
import projectCar.entity.Car;
import projectCar.entity.OtherCosts;
import projectCar.service.interfaces.IOtherCostsService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OtherCostsServiceImplTest {

    @Autowired
    private IOtherCostsService otherCostsService;

    @MockBean
    private IOtherCostsDAO costsDAO;

    @Autowired
    private ICarDAO carDAO = new CarDAOImpl();

    private OtherCosts useOtherCosts() {
        OtherCosts otherCosts = new OtherCosts();
        Car car = carDAO.read(1);
        otherCosts.setNameOtherCost("cost");
        otherCosts.setCost(1);
        otherCosts.setDateCost(Date.valueOf("2000-01-01"));
        otherCosts.setCar(car);
        return otherCosts;
    }

    private OtherCosts otherCostsTest;

    @Test
    void add() {
        otherCostsTest = useOtherCosts();
        otherCostsService.add(otherCostsTest);
        Mockito.verify(costsDAO, Mockito.times(1)).add(otherCostsTest);
    }

    @Test
    void update() {
        otherCostsTest = useOtherCosts();
        otherCostsTest.setNameOtherCost("testCost");
        otherCostsService.update(otherCostsTest);
        Mockito.verify(costsDAO, Mockito.times(1)).update(otherCostsTest);
    }

    @Test
    void read() {
        otherCostsTest = useOtherCosts();
        Mockito.when(costsDAO.read(1)).thenReturn(otherCostsTest);
        OtherCosts foundCost = otherCostsService.read(1);
        assertEquals("cost", foundCost.getNameOtherCost());
    }

    @Test
    void delete() {
        otherCostsTest = useOtherCosts();
        otherCostsService.delete(otherCostsTest);
        Mockito.verify(costsDAO, Mockito.times(1)).delete(otherCostsTest);
    }

    @Test
    void costsCount() {
        Car car = carDAO.read(1);
        int costsCount = 0;
        Mockito.when(otherCostsService.otherCostsCount(car.getId())).thenReturn(costsCount);
        assertEquals(costsCount, costsDAO.otherCostsCount(car.getId()));
    }

    @Test
    void getAllDocs() {
        List<OtherCosts> costsList = new ArrayList<>();
        Mockito.when(costsDAO.getAll()).thenReturn(costsList);
        List<OtherCosts> costs = otherCostsService.getAll();
        assertEquals(costs, costsList);
    }
}