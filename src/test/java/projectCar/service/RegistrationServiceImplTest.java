package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.IRegistrationDAO;
import projectCar.entity.Car;
import projectCar.entity.Registration;
import projectCar.entity.User;
import projectCar.service.interfaces.IRegistrationService;

import java.sql.Date;
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

    private Registration useRegistration() {
        Car car = useCar();
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