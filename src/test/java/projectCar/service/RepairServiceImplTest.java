package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.IRepairDAO;
import projectCar.entity.Car;
import projectCar.entity.Currency;
import projectCar.entity.Repair;
import projectCar.entity.User;
import projectCar.service.interfaces.IRepairService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class RepairServiceImplTest {

    @Autowired
    private IRepairService repairService;

    @MockBean
    private IRepairDAO repairDAO;

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

    private Car useCar() {
        Car carCreate = new Car();
        User user = useUser();
        carCreate.setNameCar("CarTest");
        carCreate.setMileage(1);
        carCreate.setUser(user);
        return carCreate;
    }

    private Currency useCurrency() {
        Currency currency = new Currency();
        currency.setTitle("EUR");
        return currency;
    }

    private Repair useRepair() {
        Repair repair = new Repair();
        Car car = useCar();
        repair.setNameRepair("repair");
        repair.setBeginMileage(100);
        repair.setCostsRepair(1);
        repair.setServiceLife(100);
        repair.setCar(car);
        repair.setCurrency(useCurrency());
        return repair;
    }

    private Repair repairTest;

    @Test
    void add() {
        repairTest = useRepair();
        repairService.add(repairTest);
        Mockito.verify(repairDAO, Mockito.times(1)).add(repairTest);
    }

    @Test
    void update() {
        repairTest = useRepair();
        repairTest.setNameRepair("repairTest");
        repairService.update(repairTest);
        Mockito.verify(repairDAO, Mockito.times(1)).update(repairTest);
    }

    @Test
    void read() {
        repairTest = useRepair();
        Mockito.when(repairDAO.read(1)).thenReturn(repairTest);
        Repair foundRepair = repairService.read(1);
        assertEquals("repair", foundRepair.getNameRepair());
        assertEquals(100, foundRepair.getServiceLife());
    }

    @Test
    void repairCount() {
        Car car = useCar();
        int repairCount = 0;
        Mockito.when(repairService.repairCount(car.getId())).thenReturn(repairCount);
        assertEquals(repairCount, repairDAO.repairCount(car.getId()));
    }

    @Test
    void delete() {
        repairTest = useRepair();
        repairService.delete(repairTest);
        Mockito.verify(repairDAO, Mockito.times(1)).delete(repairTest);
    }

    @Test
    void getAll() {
        List<Repair> repairList = new ArrayList<>();
        Mockito.when(repairDAO.getAll()).thenReturn(repairList);
        List<Repair> repairs = repairService.getAll();
        assertEquals(repairs, repairList);
    }
}