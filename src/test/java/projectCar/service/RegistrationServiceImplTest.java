package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    private Registration useRegistrayion() {
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
    }

    @Test
    void update() {
    }

    @Test
    void read() {
    }
}