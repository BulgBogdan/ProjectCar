package projectCar.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.entity.Car;

import java.util.List;

@Repository
public class CarDAOImpl implements ICarDAO {

    private static final Logger logger = Logger.getLogger(CarDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.save(car);
        logger.info("Car successfully added. Car: " + car);
    }

    @Override
    public void update(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.update(car);
        logger.info("Car successfully updated. Car: " + car);

    }

    @Override
    public Car read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Car car = session.get(Car.class, id);
        logger.info("Car successfully read. Car: " + car);
        return car;
    }

    @Override
    public void delete(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(car);
        logger.info("Car successfully deleted. Car: " + car);

    }

    @Override
    public int carsCount(int idUser) {
        Session session = sessionFactory.getCurrentSession();
        int count = session.createQuery("select count(*) from Car where user.id = '" + idUser + "'", Number.class)
                .getSingleResult().intValue();
        logger.info("Car returned count. Car: " + count);
        return count;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Car> listCar = session.createQuery("from Car").list();
        for (Car car : listCar) {
            logger.info("Car list. Car: " + car);
        }
        return listCar;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getCars(int page, int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Car> listCar = session.createQuery("from Car where user.id = '" + id + "'")
                .setFirstResult(10 * (page - 1)).setMaxResults(10).list();
        for (Car car : listCar) {
            logger.info("Car list. Car: " + car.toString());
        }
        return listCar;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getListsForCostsByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Car> cars = null;

        cars = session.createQuery(
                "select distinct car from Car car left join fetch car.documents where car.id = '" + id + "'",
                Car.class).setHint(QueryHints.PASS_DISTINCT_THROUGH, false).getResultList();
        cars = session.createQuery(
                "select distinct car from Car car left join fetch car.repairs where car.id = '" + id + "'",
                Car.class).setHint(QueryHints.PASS_DISTINCT_THROUGH, false).getResultList();
        cars = session.createQuery(
                "select distinct car from Car car left join fetch car.fuels where car.id = '" + id + "'",
                Car.class).setHint(QueryHints.PASS_DISTINCT_THROUGH, false).getResultList();
        cars = session.createQuery(
                "select distinct car from Car car left join fetch car.otherCosts where car.id = '" + id + "'",
                Car.class).setHint(QueryHints.PASS_DISTINCT_THROUGH, false).getResultList();
        for (Car car : cars) {
            logger.info("Car list. Car: " + car);
        }
        return cars;
    }

}