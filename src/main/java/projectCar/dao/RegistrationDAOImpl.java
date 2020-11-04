package projectCar.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.IRegistrationDAO;
import projectCar.entity.Parameter;
import projectCar.entity.Registration;

import java.util.List;

@Repository
public class RegistrationDAOImpl implements IRegistrationDAO {

    private static final Logger logger = Logger.getLogger(DocumentDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Registration registration) {
        Session session = sessionFactory.getCurrentSession();
        session.save(registration);
        logger.info("Registration successfully added. Registration: " + registration);
    }

    @Override
    public void update(Registration registration) {
        Session session = sessionFactory.getCurrentSession();
        session.update(registration);
        logger.info("Registration successfully updated. Registration: " + registration);
    }

    @Override
    public Registration read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Registration registration = session.get(Registration.class, id);
        logger.info("Registration successfully read. Registration: " + registration);
        return registration;
    }

    @Override
    public void delete(Registration registration) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(registration);
        logger.info("Registration successfully deleted. Registration: " + registration);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Registration> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Registration> registrationList = session.createQuery("from Registration").list();
        for (Registration registration : registrationList) {
            logger.info("Registration list. Registration: " + registration);
        }
        return registrationList;
    }
}
