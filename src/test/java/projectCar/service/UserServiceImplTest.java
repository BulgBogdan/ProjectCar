package projectCar.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import projectCar.dao.UserDAOImpl;
import projectCar.dao.interfaces.IUserDAO;
import projectCar.service.interfaces.IUserService;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private IUserService userService = new UserServiceImpl();

    @MockBean
    private IUserDAO userDAO = new UserDAOImpl();

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void loadUserByUsername() {
    }

    @Test
    void add() {

    }

    @Test
    void update() {
    }

    @Test
    void read() {
    }

    @Test
    void findByLogin() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}