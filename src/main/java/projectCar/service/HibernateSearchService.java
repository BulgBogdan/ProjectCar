package projectCar.service;

import org.apache.lucene.search.Query;
import org.apache.lucene.util.QueryBuilder;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.entity.Car;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class HibernateSearchService {

    @Autowired
    private final EntityManager entityManager;

    @Autowired
    public HibernateSearchService(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    public void initializeHibernateSearch(){
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    @Transactional
    public List<Car> fuzzySearch(String searchText){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Car.class).get();
        Query luceneQuery = ((org.hibernate.search.query.dsl.QueryBuilder) queryBuilder).keyword().fuzzy()
                .withEditDistanceUpTo(1).withPrefixLength(1).onField("nameCar").matching(searchText).createQuery();
        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery,Car.class);
        List<Car> carList = null;
        try{
            carList = jpaQuery.getResultList();
        }catch (NoResultException ex){
            ;
        }
        return carList;
    }
}
