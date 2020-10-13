package projectCar.dao.interfaces;

import projectCar.entity.User;

import java.util.List;

public interface IUserDAO{

    boolean add(User user);

    boolean update(User user);

    User read(int id);

    User findByLogin(String login);

    boolean delete(User user);

    List<User> getAll();
}
