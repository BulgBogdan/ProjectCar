package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import projectCar.service.ISecurityService;
import projectCar.service.interfaces.IUserService;
import projectCar.validator.UserValidator;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISecurityService securityService;

    @Autowired
    private UserValidator userValidator;

}
