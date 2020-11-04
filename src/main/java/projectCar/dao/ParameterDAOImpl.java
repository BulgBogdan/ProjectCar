package projectCar.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.IParameterDAO;
import projectCar.entity.Fuel;
import projectCar.entity.Parameter;

import java.util.List;

@Repository
public class ParameterDAOImpl implements IParameterDAO {

    private static final Logger logger = Logger.getLogger(DocumentDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Parameter parameter) {
        Session session = sessionFactory.getCurrentSession();
        session.save(parameter);
        logger.info("Parameter successfully added. Parameter: " + parameter);
    }

    @Override
    public void update(Parameter parameter) {
        Session session = sessionFactory.getCurrentSession();
        session.update(parameter);
        logger.info("Parameter successfully updated. Parameter: " + parameter);
    }

    @Override
    public Parameter read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Parameter parameter = session.get(Parameter.class, id);
        logger.info("Parameter successfully read. Parameter: " + parameter);
        return parameter;
    }

    @Override
    public void delete(Parameter parameter) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(parameter);
        logger.info("Parameter successfully deleted. Parameter: " + parameter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Parameter> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Parameter> listParameter = session.createQuery("from Parameter").list();
        for (Parameter parameter : listParameter) {
            logger.info("Parameter list. Parameter: " + parameter);
        }
        return listParameter;
    }
}
