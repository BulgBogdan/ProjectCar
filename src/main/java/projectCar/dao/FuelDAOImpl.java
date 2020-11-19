package projectCar.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.IFuelDAO;
import projectCar.entity.Fuel;

import java.util.List;

@Repository
public class FuelDAOImpl implements IFuelDAO {

    private static final Logger logger = Logger.getLogger(DocumentDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Fuel fuel) {
        Session session = sessionFactory.getCurrentSession();
        session.save(fuel);
        logger.info("Fuel successfully added. Fuel: " + fuel);
    }

    @Override
    public void update(Fuel fuel) {
        Session session = sessionFactory.getCurrentSession();
        session.update(fuel);
        logger.info("Fuel successfully updated. Fuel: " + fuel);
    }

    @Override
    public Fuel read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Fuel fuel = session.get(Fuel.class, id);
        logger.info("Fuel successfully read. Fuel: " + fuel);
        return fuel;
    }

    @Override
    public void delete(Fuel fuel) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(fuel);
        logger.info("Fuel successfully deleted. Fuel: " + fuel);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int fuelCount(int id) {
        Session session = sessionFactory.getCurrentSession();
        int count = session.createQuery("select count(*) from Fuel where car.id = '" + id + "'", Number.class)
                .getSingleResult().intValue();
        logger.info("Fuel returned count. Fuel: " + count);
        return count;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Fuel> getFuel(int page, int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Fuel> fuelList = session.createQuery("from Fuel where car.id = '" + id + "'")
                .setFirstResult(10 * (page - 1)).setMaxResults(10).list();
        for (Fuel fuel : fuelList) {
            logger.info("Fuel list. Fuel: " + fuel.toString());
        }
        return fuelList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Fuel> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Fuel> listFuel = session.createQuery("from Fuel").list();
        for (Fuel fuel : listFuel) {
            logger.info("Fuel list. Fuel: " + fuel);
        }
        return listFuel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Fuel> getAllByCar(int idCar) {
        Session session = sessionFactory.getCurrentSession();
        List<Fuel> listFuel = session.createQuery("from Fuel where car.id = '" + idCar + "'").list();
        for (Fuel fuel : listFuel) {
            logger.info("FuelsByCar list. Fuel: " + fuel);
        }
        return listFuel;
    }
}
