package projectCar.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.entity.Car;

import java.util.List;

@Repository
public class CarDAOImpl implements ICarDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.save(car);
    }

    @Override
    public void update(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.update(car);
    }

    @Override
    public Car read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Car car = session.get(Car.class, id);
        return car;
    }

    @Override
    public void delete(Car car) {
        Session session = sessionFactory.getCurrentSession();
        if (session!=null) {
            session.delete(car);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Car> listCar = session.createQuery("from Car").list();
        return listCar;
    }
}
