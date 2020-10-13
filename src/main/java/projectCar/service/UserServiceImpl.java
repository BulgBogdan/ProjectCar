package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.UserDAOImpl;
import projectCar.dao.interfaces.IUserDAO;
import projectCar.entity.User;
import projectCar.service.interfaces.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO = new UserDAOImpl();

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public boolean add(User user) {
        User userFromDB = userDAO.findByLogin(user.getLogin());
        if (userFromDB == null){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDAO.add(user);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean update(User user) {
        if (userDAO.update(user)){
            return true;
        }
            return false;
    }

    @Override
    @Transactional
    public User read(int id) {
        return userDAO.read(id);
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    @Transactional
    public boolean delete(User user) {
        if (userDAO.delete(user)){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDAO.getAll();
    }
}
