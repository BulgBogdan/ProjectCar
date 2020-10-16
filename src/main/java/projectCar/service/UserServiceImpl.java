package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.UserDAOImpl;
import projectCar.dao.interfaces.IUserDAO;
import projectCar.entity.User;
import projectCar.service.interfaces.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, IUserService {
    @Autowired
    private IUserDAO userDAO = new UserDAOImpl();

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails loadedUser;
        User client = userDAO.findByLogin(s);
        loadedUser=new org.springframework.security.core.userdetails.User(client.getLogin(),client.getPassword(),client.getAuthorities());
        if (loadedUser==null){
            throw new UsernameNotFoundException("User Not Found ");
        }
        return loadedUser;
    }

    @Override
    @Transactional
    public boolean add(User user) {
        User userFromDB = userDAO.findByLogin(user.getLogin());
        if (userFromDB != null) {
            return false;
        }
        userDAO.add(user);
        return true;
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
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
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
