package projectCar.validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.Errors;
import projectCar.config.TestConfig;
import projectCar.entity.User;
import projectCar.service.interfaces.IUserService;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class UserValidatorTest {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private IUserService userService;

    private static final String userLogin = "login";

    private static final User user = mock(User.class);

    @BeforeAll
    public static void setup() {
        when(user.getLogin()).thenReturn(userLogin);
    }

    @Test
    public void validateShouldAcceptUserWithNewLogin() {
        when(userService.findByLogin(userLogin)).thenReturn(null);
        Errors errors = mock(Errors.class);
        userValidator.validate(user, errors);
        verify(errors, times(1)).rejectValue(eq("login"), any(), any());
    }
}