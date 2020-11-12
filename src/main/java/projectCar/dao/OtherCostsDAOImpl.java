package projectCar.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectCar.dao.interfaces.IOtherCostsDAO;
import projectCar.entity.OtherCosts;

import java.util.List;

@Repository
public class OtherCostsDAOImpl implements IOtherCostsDAO {

    private static final Logger logger = Logger.getLogger(DocumentDAOImpl.class);

    private SessionFactory sessionFactory;

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
            logger.info("OtherCosts list. OtherCosts: " + costs.toString());
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
    public List<OtherCosts> searchList(String searchText, int id) {
//        Session session = sessionFactory.getCurrentSession();
//        FullTextSession fullTextSession  = Search.getFullTextSession(session);
//        try {
//            fullTextSession.createIndexer().startAndWait();
//        } catch (InterruptedException e) {
//            logger.error("FullTextSession OtherCosts exception", e);
//            e.printStackTrace();
//        }
//        List<Integer> idsForOtherCosts = session
//                .createQuery("select id from OtherCosts where car.user.id = '" + id + "'", Integer.class)
//                .getResultList();
//        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
//                .buildQueryBuilder().forEntity(OtherCosts.class).get();
//        Query query = queryBuilder.keyword().onField("nameOtherCost").matching(searchText).createQuery();
//        BooleanJunction idJunction = queryBuilder.bool();
//        for (Integer idforCosts : idsForOtherCosts) {
//            idJunction.should(queryBuilder.keyword().onField("id").matching(idforCosts).createQuery());
//        }
//        Query idQuery = idJunction.createQuery();
//        Query combinedQuery = queryBuilder.bool().must(query).must(idQuery).createQuery();
//
//        org.hibernate.search.jpa.FullTextQuery hibQuery = fullTextSession
//                .createFullTextQuery(combinedQuery, OtherCosts.class);
//        List<OtherCosts> otherCosts = hibQuery.getResultList();
//        for (OtherCosts otherCost: otherCosts){
//            logger.info("OtherCosts list. OtherCosts: " + otherCost);
//        }
        return null;
    }
}
