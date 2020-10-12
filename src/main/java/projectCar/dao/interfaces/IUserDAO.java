package projectCar.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import projectCar.entity.User;

import java.util.List;

public interface IUserDAO extends JpaRepository<User, Integer> {

    void add(User user);

    void update(User user);

    User read(int id);

    User findByLogin(String login);

    void delete(User user);

    List<User> getAll();
}
