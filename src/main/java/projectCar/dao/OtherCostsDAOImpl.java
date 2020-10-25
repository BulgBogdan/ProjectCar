package projectCar.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.IOtherCostsDAO;
import projectCar.entity.OtherCosts;

import java.util.List;

@Repository
public class OtherCostsDAOImpl implements IOtherCostsDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(OtherCosts otherCosts) {
        Session session = sessionFactory.getCurrentSession();
        session.save(otherCosts);
    }

    @Override
    public void update(OtherCosts otherCosts) {
        Session session = sessionFactory.getCurrentSession();
        session.update(otherCosts);
    }

    @Override
    public OtherCosts read(int id) {
        Session session = sessionFactory.getCurrentSession();
        OtherCosts otherCosts = session.get(OtherCosts.class, id);
        return otherCosts;
    }

    @Override
    public void delete(OtherCosts otherCosts) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(otherCosts);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OtherCosts> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<OtherCosts> listOtherCosts = session.createQuery("from OtherCosts").list();
        return listOtherCosts;
    }
}
