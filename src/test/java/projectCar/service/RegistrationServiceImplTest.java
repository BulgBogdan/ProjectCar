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
import projectCar.dao.interfaces.IRegistrationDAO;
import projectCar.entity.Car;
import projectCar.entity.Registration;
import projectCar.service.interfaces.IRegistrationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class RegistrationServiceImplTest {

    @Autowired
    private IRegistrationService registrationService;

    @MockBean
    private IRegistrationDAO registrationDAO;

    @Autowired
    private ICarDAO carDAO = new CarDAOImpl();

    private Registration useRegistration() {
        Car car = carDAO.read(1);
        Registration registration = new Registration();
        registration.setPriceCar(1);
        registration.setPriceRegistration(1);
        registration.setCar(car);
        return registration;
    }

    private Registration registrationTest;

    @Test
    void add() {
        registrationTest = useRegistration();
        registrationService.add(registrationTest);
        Mockito.verify(registrationDAO, Mockito.times(1)).add(registrationTest);
    }

    @Test
    void update() {
        registrationTest = useRegistration();
        registrationTest.setPriceCar(100);
        registrationService.update(registrationTest);
        Mockito.verify(registrationDAO, Mockito.times(1)).update(registrationTest);
    }

    @Test
    void read() {
        registrationTest = useRegistration();
        Mockito.when(registrationDAO.read(1)).thenReturn(registrationTest);
        Registration registration = registrationService.read(1);
        assertEquals(1, registration.getPriceCar());
        assertEquals(1, registration.getPriceRegistration());
    }

    @Test
    void delete() {
        registrationTest = useRegistration();
        registrationService.delete(registrationTest);
        Mockito.verify(registrationDAO, Mockito.times(1)).delete(registrationTest);
    }

    @Test
    void getAll() {
        List<Registration> registrationList = new ArrayList<>();
        Mockito.when(registrationDAO.getAll()).thenReturn(registrationList);
        List<Registration> registrations = registrationService.getAll();
        assertEquals(registrations, registrationList);
    }
}