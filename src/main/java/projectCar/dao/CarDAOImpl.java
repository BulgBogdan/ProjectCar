package projectCar.dao;

import org.apache.log4j.Logger;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.QueryHints;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
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
        logger.info("Car successfully added. Car: " + car.toString());
    }

    @Override
    public void update(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.update(car);
        logger.info("Car successfully updated. Car: " + car.toString());

    }

    @Override
    public Car read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Car car = session.get(Car.class, id);
        logger.info("Car successfully read. Car: " + car.toString());
        return car;
    }

    @Override
    public void delete(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(car);
        logger.info("Car successfully deleted. Car: " + car.toString());

    }

    @Override
    public int carsCount(int id) {
        Session session = sessionFactory.getCurrentSession();
        int count = session.createQuery("select count(*) from Car where user.id = '" + id + "'", Number.class)
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
            logger.info("Car list. Car: " + car.toString());
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
    public List<Car> getLists(int id) {
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

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> searchList(String textSearch, int id) {
        Session session = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession  = Search.getFullTextSession(session);
        try {
            fullTextSession.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            logger.error("FullTextSession exception", e);
            e.printStackTrace();
        }
        List<Integer> idsForCar = session
                .createQuery("select id from Car where user.id = '" + id + "'", Integer.class).getResultList();

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Car.class).get();
        Query query = queryBuilder.keyword()
                .onField("nameCar")
                .andField("documents.nameDocument")
                .andField("otherCosts.nameOtherCost")
                .andField("repairs.nameRepair")
                .matching(textSearch).createQuery();
        // matching @field and request text
        BooleanJunction idJunction = queryBuilder.bool();
        for (Integer ids : idsForCar) {
            idJunction.should(queryBuilder.keyword().onField("id").matching(ids).createQuery());
            // match id from field with id from request text
        }
        Query idQuery = idJunction.createQuery();
        Query combinedQuery = queryBuilder.bool().must(query)
//                .must(queryBuilder.keyword().onField("nameCar").matching(textSearch).createQuery())
//                .must(queryBuilder.keyword().onField("documents.nameDocument").matching(textSearch).createQuery())
//                .must(queryBuilder.keyword().onField("otherCosts.nameOtherCost").matching(textSearch).createQuery())
//                .must(queryBuilder.keyword().onField("repairs.nameRepair").matching(textSearch).createQuery())
                .must(idQuery).createQuery();
        // method boolean AND (must().must()), which equals output
        org.hibernate.search.jpa.FullTextQuery hibQuery = fullTextSession.createFullTextQuery(combinedQuery, Car.class);
        List<Car> cars = hibQuery.getResultList();
        for (Car car: cars){
            logger.info("Car list. Car: " + car);
        }
        return cars;
    }
}
