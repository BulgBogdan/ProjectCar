package projectCar.dao;

import org.apache.log4j.Logger;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.IRepairDAO;
import projectCar.entity.Repair;

import java.util.List;

@Repository
public class RepairDAOImpl implements IRepairDAO {

    private static final Logger logger = Logger.getLogger(DocumentDAOImpl.class);

    private SessionFactory sessionFactory;

    private FullTextSession getFullTextSession(Session session) {
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        try {
            fullTextSession.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            logger.error("FullTextSession exception", e);
            e.printStackTrace();
        }
        return fullTextSession;
    }

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
    public int repairCount(int id) {
        Session session = sessionFactory.getCurrentSession();
        int count = session.createQuery("select count(*) from Repair where car.id = '" + id + "'", Number.class)
                .getSingleResult().intValue();
        logger.info("Repair returned count. Repair: " + count);
        return count;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Repair> getRepair(int page, int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Repair> repairList = session.createQuery("from Repair where car.id = '" + id + "'")
                .setFirstResult(10 * (page - 1)).setMaxResults(10).list();
        for (Repair repair : repairList) {
            logger.info("Repair list. Repair: " + repair);
        }
        return repairList;
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

    @Override
    @SuppressWarnings("unchecked")
    public List<Repair> searchList(String textSearch, int id, int page) {

        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = getFullTextSession(session);
        List<Integer> idRepairs = session
                .createQuery("select id from Repair where car.user.id = '" + id + "'", Integer.class)
                .getResultList();

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder()
                .forEntity(Repair.class).get();
        Query query = queryBuilder.keyword()
                .onFields("nameRepair")
                .matching(textSearch).createQuery();// matching @field and request text
        BooleanJunction boolJunction = queryBuilder.bool();
        for (Integer ids : idRepairs) {
            // match id from field with id from request text
            boolJunction.should(queryBuilder.keyword().onField("id").matching(ids).createQuery());
        }
        Query idQuery = boolJunction.createQuery();// method boolean AND (must().must()), which equals output
        Query combinedQuery = queryBuilder.bool().must(query).must(idQuery).createQuery();

        org.hibernate.search.jpa.FullTextQuery hibQuery = fullTextSession
                .createFullTextQuery(combinedQuery, Repair.class)
                .setFirstResult(10 * (page - 1)).setMaxResults(10);

        List<Repair> repairs = hibQuery.getResultList();
        for (Repair repair : repairs) {
            logger.info("Repairs list. Repair: " + repair);
        }
        return repairs;
    }
}