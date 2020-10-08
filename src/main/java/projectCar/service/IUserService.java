package projectCar.service;

import projectCar.entity.User;

import java.util.List;

public interface IUserService {

    void add(User user);

    void update(User user);

    User read(int id);

    void delete(User user);

    List<User> getAll();
}
