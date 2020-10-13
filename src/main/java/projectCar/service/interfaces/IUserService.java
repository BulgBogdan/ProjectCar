package projectCar.service.interfaces;

import projectCar.entity.User;

import java.util.List;

public interface IUserService {

    boolean add(User user);

    boolean update(User user);

    User read(int id);

    User findByLogin(String login);

    boolean delete(User user);

    List<User> getAll();
}
