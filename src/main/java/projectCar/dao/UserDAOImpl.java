package projectCar.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.IUserDAO;
import projectCar.entity.User;

import java.util.List;

@Repository
public class UserDAOImpl implements IUserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean add(User user) {
        Session session = sessionFactory.getCurrentSession();
        if (session!=null){
            session.persist(user);
            logger.info("User successfully added. User: " + user);
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        Session session = sessionFactory.getCurrentSession();
        if (session!=null) {
            session.update(user);
            logger.info("User successfully updated. User: " + user);
        }
        return false;
    }

    @Override
    public User read(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User)session.load(User.class, new Integer(id));
        logger.info("User successfully readed. User: " + user);
        return user;
    }

    @Override
    public User findByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User)session.createQuery("from User where login = '" + login +"'").uniqueResult();
        logger.info("User successfully readed. User: " + user);
        return user;
    }

    @Override
    public boolean delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        if (session!=null) {
            session.delete(user);
            logger.info("User successfully deleted. User: " + user);
        }
        return false;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<User> listUser = session.createQuery("from User").list();
        for (User user : listUser) {
            logger.info("User list: " + user);
        }
        return listUser;
    }
}
