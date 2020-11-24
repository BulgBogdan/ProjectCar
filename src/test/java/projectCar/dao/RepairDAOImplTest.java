package projectCar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.dao.interfaces.IRepairDAO;
import projectCar.entity.Car;
import projectCar.entity.Repair;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RepairDAOImplTest {

    @Autowired
    private ICarDAO carDAO;

    @Autowired
    private IRepairDAO repairDAO;

    private Car car;

    private Repair repair;

    @Test
    void add() {
        Repair repairCreate = new Repair();
        car = carDAO.read(1);
        repairCreate.setNameRepair("repair");
        repairCreate.setServiceLife(1000);
        repairCreate.setCostsRepair(1);
        repairCreate.setBeginMileage(1);
        repairCreate.setCar(car);
        repairDAO.add(repairCreate);
        int repairId = repairCreate.getId();
        repair = repairDAO.read(repairId);
        assertEquals(repair.getNameRepair(), "repair");
        assertEquals(repair.getServiceLife(), 1000);
    }

    @Test
    void update() {
        repair = repairDAO.read(1);
        repair.setNameRepair("repairTest");
        repairDAO.update(repair);
        String testRepair = repairDAO.read(1).getNameRepair();
        assertEquals(testRepair, "repairTest");
    }

    @Test
    void read() {
        repair = repairDAO.read(1);
        assertNotNull(repair);
    }

    @Test
    void delete() {
        repair = repairDAO.read(1);
        repairDAO.delete(repair);
        Repair repairDelete = repairDAO.read(1);
        assertNotEquals(repair, repairDelete);
    }

    @Test
    void repairCount() {
        car = carDAO.read(1);
        int repairsCount = repairDAO.repairCount(car.getId());
        boolean notNullCountRepair = false;
        if (repairsCount != 0) {
            notNullCountRepair = true;
        }
        assertTrue(notNullCountRepair);
    }

    @Test
    void getAll() {
        List<Repair> repairs = repairDAO.getAll();
        assertNotNull(repairs);
    }
}