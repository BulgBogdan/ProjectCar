package projectCar.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import projectCar.entity.User;

import java.util.List;

@Repository
public class UserDAOImpl implements IUserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully added. User: " + user);
    }

    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully updated. User: " + user);
    }

    public User read(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User successfully readed. User: " + user);
        return user;
    }

    public void delete(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        if (session!=null) {
            session.delete(user);
        }
        logger.info("User successfully deleted. User: " + user);
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> listUser = session.createQuery("from User").list();
        for (User user : listUser) {
            logger.info("User list: " + user);
        }
        return listUser;
    }
}
