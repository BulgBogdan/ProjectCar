package projectCar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.IUserDAO;
import projectCar.entity.User;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserDAOImplTest {

    @Autowired
    private IUserDAO userDAO;

    private User user;

    @Test
    void loadUserByUsername() {
        user = userDAO.findByLogin("user");
        assertEquals(user.getLogin(), "user");
    }

    @Test
    void addUserTest() {
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
    void updateUserTest() {
        user = userDAO.findByLogin("user5");
        user.setLogin("user55");
        userDAO.update(user);
        user = userDAO.findByLogin("user55");
        assertNotNull(user);
    }

    @Test
    void readUserTest() {
        user = userDAO.read(1);
        assertEquals(user.getId(), 1);
    }

    @Test
    void getAllUsers() {
        List<User> userList = userDAO.getAll();
        assertNotNull(userList);
    }

    @Test
    void deleteUserTest() {
        User userRead = userDAO.read(4);
        userDAO.delete(userRead);
        User userDelete = userDAO.read(4);
        assertNotEquals(userRead, userDelete);
        assertNull(userDelete);
    }
}