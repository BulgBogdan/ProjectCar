package projectCar.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.IRepairDAO;
import projectCar.entity.Parameter;
import projectCar.entity.Repair;

import java.util.List;

@Repository
public class RepairDAOImpl implements IRepairDAO {

    private static final Logger logger = Logger.getLogger(DocumentDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Repair repair) {
        Session session = sessionFactory.getCurrentSession();
        session.save(repair);
        logger.info("Repair successfully added. Repair: " + repair);
    }

    @Override
    public void update(Repair repair) {
        Session session = sessionFactory.getCurrentSession();
        session.update(repair);
        logger.info("Repair successfully updated. Repair: " + repair);
    }

    @Override
    public Repair read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Repair repair = session.get(Repair.class, id);
        logger.info("Repair successfully read. Repair: " + repair);
        return repair;
    }

    @Override
    public void delete(Repair repair) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(repair);
        logger.info("Repair successfully deleted. Repair: " + repair);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Repair> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Repair> listRepair = session.createQuery("from Repair").list();
        for (Repair repair : listRepair) {
            logger.info("Repair list. Repair: " + repair);
        }
        return listRepair;
    }
}
