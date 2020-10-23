package projectCar.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.IFuelDAO;
import projectCar.entity.Fuel;

import java.util.List;

@Repository
public class FuelDAOImpl implements IFuelDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Fuel fuel) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(fuel);
    }

    @Override
    public void update(Fuel fuel) {
        Session session = sessionFactory.getCurrentSession();
        session.update(fuel);
    }

    @Override
    public Fuel read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Fuel fuel = session.get(Fuel.class, id);
        return fuel;
    }

    @Override
    public void delete(Fuel fuel) {
        Session session = sessionFactory.getCurrentSession();
        if (session!=null){
            session.delete(fuel);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Fuel> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Fuel> listFuel = session.createQuery("from Fuel").list();
        return listFuel;
    }
}
