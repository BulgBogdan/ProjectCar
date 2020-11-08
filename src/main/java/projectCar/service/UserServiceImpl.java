package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDAO.findByLogin(login);
        org.springframework.security.core.userdetails.User.UserBuilder builder=null;
        if (user!=null){
            builder=org.springframework.security.core.userdetails.User.withUsername(login);
            builder.disabled(!user.isEnabled());
            builder.password(user.getPassword());
            builder.authorities(user.getAuthorities());
        }else {
            throw new UsernameNotFoundException("User Not Found ");
        }
        return builder.build();
    }

    @Override
    @Transactional
    public boolean add(User user) {
        User userFromDB = userDAO.findByLogin(user.getLogin());
        if (userFromDB != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
