package projectCar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.IUserDAO;
import projectCar.entity.User;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private IUserDAO userDAO;

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void add(User user) {
        this.userDAO.add(user);
    }

    @Transactional
    public void update(User user) {
        this.userDAO.update(user);
    }

    @Transactional
    public User read(int id) {
        return this.userDAO.read(id);
    }

    @Transactional
    public void delete(User user) {
        this.userDAO.delete(user);
    }

    @Transactional
    public List<User> getAll() {
        return this.userDAO.getAll();
    }
}
