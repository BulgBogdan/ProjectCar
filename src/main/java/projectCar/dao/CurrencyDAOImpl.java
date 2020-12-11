package projectCar.dao;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.ICurrencyDAO;
import projectCar.entity.Currency;

@Repository
public class CurrencyDAOImpl implements ICurrencyDAO {

    private static final Logger logger = Logger.getLogger(CurrencyDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Currency read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Currency currency = session.get(Currency.class, id);
        logger.info("Currency successfully updated. Currency: " + currency);
        return currency;
    }
}
