package projectCar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import projectCar.dao.UserDAOImpl;
import projectCar.service.interfaces.IUserService;
import projectCar.validator.UserValidator;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public UserValidator userValidator() {
        return new UserValidator();

    }

    @Bean
    public IUserService userService() {
        return mock(IUserService.class);
    }

    @Bean
    public UserDAOImpl userDAO() {
        return new UserDAOImpl();
    }

}
