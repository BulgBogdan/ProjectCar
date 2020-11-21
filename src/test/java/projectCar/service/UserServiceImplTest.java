package projectCar.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.UserDAOImpl;
import projectCar.dao.interfaces.IUserDAO;
import projectCar.entity.User;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private IUserDAO userDAO = new UserDAOImpl();

    private User user;

    @Test
    void loadUserByUsername() {
        user = userDAO.findByLogin("user");
        assertEquals(user.getLogin(), "user");
    }

    @Test
    void add() {
        User userCreate = new User();
        userCreate.setLogin("login1234");
        userCreate.setPassword("password");
        userCreate.setEmail("user@gmail.com");
        userCreate.setFirstName("ivan");
        userCreate.setSecondName("ivanov");
        userCreate.setBirthday(Date.valueOf("2000-02-02"));
        userDAO.add(userCreate);
        user = userDAO.findByLogin("login1234");
        assertNotNull(user);
    }

    @Test
    void update() {
        user = userDAO.findByLogin("user5");
        user.setLogin("user55");
        userDAO.update(user);
        user = userDAO.findByLogin("user55");
        assertNotNull(user);
    }

    @Test
    void read() {
        user = userDAO.read(1);
        assertEquals(user.getId(), 1);
    }

}