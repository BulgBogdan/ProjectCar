package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.IUserDAO;
import projectCar.dao.UserDAOImpl;
import projectCar.entity.User;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO = new UserDAOImpl();


    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }


    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public User read(int id) {
        return userDAO.read(id);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDAO.getAll();
    }
}
