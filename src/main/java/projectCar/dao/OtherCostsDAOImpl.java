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
import projectCar.dao.interfaces.IOtherCostsDAO;
import projectCar.entity.OtherCosts;

import java.util.List;

@Repository
public class OtherCostsDAOImpl implements IOtherCostsDAO {

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
    public void add(OtherCosts otherCosts) {
        Session session = sessionFactory.getCurrentSession();
        session.save(otherCosts);
        logger.info("OtherCosts successfully added. OtherCosts: " + otherCosts);
    }

    @Override
    public void update(OtherCosts otherCosts) {
        Session session = sessionFactory.getCurrentSession();
        session.update(otherCosts);
        logger.info("OtherCosts successfully updated. OtherCosts: " + otherCosts);
    }

    @Override
    public OtherCosts read(int id) {
        Session session = sessionFactory.getCurrentSession();
        OtherCosts otherCosts = session.get(OtherCosts.class, id);
        logger.info("OtherCosts successfully read. OtherCosts: " + otherCosts);
        return otherCosts;
    }

    @Override
    public void delete(OtherCosts otherCosts) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(otherCosts);
        logger.info("OtherCosts successfully deleted. OtherCosts: " + otherCosts);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int otherCostsCount(int id) {
        Session session = sessionFactory.getCurrentSession();
        int count = session.createQuery("select count(*) from OtherCosts where car.id = '" + id + "'", Number.class)
                .getSingleResult().intValue();
        logger.info("OtherCosts returned count. OtherCosts: " + count);
        return count;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OtherCosts> getOtherCosts(int page, int id) {
        Session session = sessionFactory.getCurrentSession();
        List<OtherCosts> otherCostsList = session.createQuery("from OtherCosts where car.id = '" + id + "'")
                .setFirstResult(10 * (page - 1)).setMaxResults(10).list();
        for (OtherCosts costs : otherCostsList) {
            logger.info("OtherCosts list. OtherCosts: " + costs);
        }
        return otherCostsList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OtherCosts> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<OtherCosts> listOtherCosts = session.createQuery("from OtherCosts").list();
        for (OtherCosts otherCosts : listOtherCosts) {
            logger.info("OtherCosts list. OtherCosts: " + otherCosts);
        }
        return listOtherCosts;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OtherCosts> searchList(String textSearch, int id, int page) {

        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = getFullTextSession(session);
        List<Integer> idCosts = session
                .createQuery("select id from OtherCosts where car.user.id = '" + id + "'", Integer.class)
                .getResultList();

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder()
                .forEntity(OtherCosts.class).get();
        Query query = queryBuilder.keyword()
                .onFields("nameOtherCost")
                .matching(textSearch).createQuery();// matching @field and request text
        BooleanJunction boolJunction = queryBuilder.bool();
        for (Integer ids : idCosts) {
            // match id from field with id from request text
            boolJunction.should(queryBuilder.keyword().onField("id").matching(ids).createQuery());
        }
        Query idQuery = boolJunction.createQuery();// method boolean AND (must().must()), which equals output
        Query combinedQuery = queryBuilder.bool().must(query).must(idQuery).createQuery();

        org.hibernate.search.jpa.FullTextQuery hibQuery = fullTextSession
                .createFullTextQuery(combinedQuery, OtherCosts.class)
                .setFirstResult(10 * (page - 1)).setMaxResults(10);

        List<OtherCosts> costs = hibQuery.getResultList();
        for (OtherCosts cost : costs) {
            logger.info("OtherCosts list. Costs: " + cost);
        }
        return costs;
    }

}
