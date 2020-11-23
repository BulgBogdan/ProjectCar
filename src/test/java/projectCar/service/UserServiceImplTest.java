package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.IUserDAO;
import projectCar.entity.User;
import projectCar.service.interfaces.IUserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @MockBean
    private IUserDAO userDAO;

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
    void loadUserByUsername() {
        User userCreate = useUser();
        Mockito.when(userDAO.findByLogin("login1234")).thenReturn(userCreate);
        User user = userService.findByLogin("login1234");
        assertEquals("login1234", user.getLogin());
    }

    @Test
    void addUserTest() {
        User userCreate = useUser();
        userService.add(userCreate);
        Mockito.verify(userDAO, Mockito.times(1)).add(userCreate);
    }

    @Test
    void updateUserTest() {
        User userCreate = useUser();
        userCreate.setLogin("login4321");
        userService.update(userCreate);
        Mockito.verify(userDAO, Mockito.times(1)).update(userCreate);
    }

    @Test
    void readUserByIdTest() {
        User userCreate = useUser();
        Mockito.when(userDAO.read(1)).thenReturn(userCreate);
        User user = userService.read(1);
        assertEquals("login1234", user.getLogin());
    }

    @Test
    void getAllUsers() {
        List<User> userList = new ArrayList<>();
        Mockito.when(userDAO.getAll()).thenReturn(userList);
        List<User> users = userService.getAll();
        assertEquals(users, userList);
    }

    @Test
    void deleteUserTest() {
        User userCreate = useUser();
        userService.delete(userCreate);
        Mockito.verify(userDAO, Mockito.times(1)).delete(userCreate);
    }
}