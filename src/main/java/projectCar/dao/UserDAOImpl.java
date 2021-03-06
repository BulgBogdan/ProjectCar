package projectCar.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.IUserDAO;
import projectCar.entity.User;

import java.util.List;

@Repository
public class UserDAOImpl implements IUserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully added. User: " + user);
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully updated. User: " + user);
    }

    @Override
    public User read(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        logger.info("User successfully read. User: " + user);
        return user;
    }

    @Override
    public User findByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User where login = '" + login + "'").uniqueResult();
        logger.info("User successfully foundByLogin. User: " + user);
        return user;
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
        logger.info("User successfully deleted. User: " + user);
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
