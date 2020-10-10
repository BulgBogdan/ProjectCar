package projectCar.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import projectCar.dao.interfaces.IRepairDAO;
import projectCar.entity.Repair;

import java.util.List;

public class RepairDAOImpl implements IRepairDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Repair repair) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(repair);
    }

    @Override
    public void update(Repair repair) {
        Session session = sessionFactory.getCurrentSession();
        session.update(repair);
    }

    @Override
    public Repair read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Repair repair = session.get(Repair.class, id);
        return repair;
    }

    @Override
    public void delete(Repair repair) {
        Session session = sessionFactory.getCurrentSession();
        if (session != null){
            session.delete(repair);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Repair> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Repair> listRepair = session.createQuery("from Repair").list();
        return listRepair;
    }
}
