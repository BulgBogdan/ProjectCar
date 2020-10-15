package projectCar.service;

public interface ISecurityService {
    String findLoggedInUsername();
    void autoLogin(String login, String password);
}
