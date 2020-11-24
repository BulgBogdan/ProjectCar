package projectCar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.dao.interfaces.IRegistrationDAO;
import projectCar.entity.Car;
import projectCar.entity.Registration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RegistrationDAOImplTest {

    @Autowired
    private ICarDAO carDAO;

    @Autowired
    private IRegistrationDAO registrationDAO;

    private Car car;

    private Registration registration;

    @Test
    void add() {
        car = carDAO.read(1);
        Registration registr = new Registration();
        registr.setPriceCar(1);
        registr.setPriceRegistration(1);
        registr.setCar(car);
        registrationDAO.add(registr);
        int registrId = registr.getId();
        registration = registrationDAO.read(registrId);
        assertEquals(registration.getPriceCar(), 1);
        assertEquals(registration.getPriceRegistration(), 1);
    }

    @Test
    void update() {
        registration = registrationDAO.read(1);
        registration.setPriceCar(1000);
        int testRegistration = registrationDAO.read(1).getPriceCar();
        assertEquals(testRegistration, 1000);
    }

    @Test
    void read() {
        registration = registrationDAO.read(1);
        assertNotNull(registration);
    }

    @Test
    void delete() {
        registration = registrationDAO.read(1);
        registrationDAO.delete(registration);
        Registration registrationDelete = registrationDAO.read(1);
        assertNotEquals(registration, registrationDelete);
    }

    @Test
    void getAll() {
        List<Registration> registrations = registrationDAO.getAll();
        assertNotNull(registrations);
    }
}