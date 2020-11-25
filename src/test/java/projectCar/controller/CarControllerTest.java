package projectCar.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import projectCar.entity.User;
import projectCar.service.interfaces.ICarService;
import projectCar.service.interfaces.IRegistrationService;
import projectCar.service.interfaces.IUserService;

import java.sql.Date;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService userService;

    @MockBean
    private ICarService carService;

    @MockBean
    private IRegistrationService registrationService;

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

    @Test
    void createCar() {

    }

    @Test
    void addCar() {
    }

    @Test
    void createFirstCost() {
    }

    @Test
    void addFirstCost() {
    }

    @Test
    void viewCar() {
    }

    @Test
    void editPageFirstCost() {
    }

    @Test
    void editFirstCost() {
    }

    @Test
    void editPage() {
    }

    @Test
    void editCar() {
    }

    @Test
    void deletePage() {
    }
}