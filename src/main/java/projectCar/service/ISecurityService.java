package projectCar.service;

public interface ISecurityService {

    String findLoggedInLogin();

    void autoLogin (String login, String password);
}
