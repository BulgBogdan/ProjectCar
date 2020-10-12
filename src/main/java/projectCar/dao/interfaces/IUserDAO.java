package projectCar.dao.interfaces;

import projectCar.entity.User;

import java.util.List;

public interface IUserDAO{

    void add(User user);

    void update(User user);

    User read(int id);

    User findByLogin(String login);

    void delete(User user);

    List<User> getAll();
}
