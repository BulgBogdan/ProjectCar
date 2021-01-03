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
import projectCar.dao.interfaces.IDocumentDAO;
import projectCar.entity.Document;

import java.util.List;

@Repository
public class DocumentDAOImpl implements IDocumentDAO {

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
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.save(document);
        logger.info("Document successfully added. Document: " + document);
    }

    @Override
    public void update(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.update(document);
        logger.info("Document successfully updated. Document: " + document);

    }

    @Override
    public Document read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Document document = session.get(Document.class, id);
        logger.info("Document successfully read. Document: " + document);
        return document;
    }

    @Override
    public void delete(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(document);
        logger.info("Document successfully deleted. Document: " + document);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int docsCount(int id) {
        Session session = sessionFactory.getCurrentSession();
        int count = session.createQuery("select count(*) from Document where car.id = '" + id + "'", Number.class)
                .getSingleResult().intValue();
        logger.info("Document returned count. Document: " + count);
        return count;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Document> getDocuments(int page, int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Document> documentList = session.createQuery("from Document where car.id = '" + id + "'")
                .setFirstResult(10 * (page - 1)).setMaxResults(10).list();
        for (Document document : documentList) {
            logger.info("Document list. Document: " + document);
        }
        return documentList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Document> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Document> listDocument = session.createQuery("from Document").list();
        for (Document document : listDocument) {
            logger.info("Document list. Document: " + document);
        }
        return listDocument;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Document> searchList(String textSearch, int id, int page) {

        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = getFullTextSession(session);
        List<Integer> idDocs = session
                .createQuery("select id from Document where car.user.id = '" + id + "'", Integer.class)
                .getResultList();

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder()
                .forEntity(Document.class).get();
        Query query = queryBuilder.keyword()
                .onFields("nameDocument")
                .matching(textSearch).createQuery();// matching @field and request text
        BooleanJunction boolJunction = queryBuilder.bool();
        for (Integer ids : idDocs) {
            // match id from field with id from request text
            boolJunction.should(queryBuilder.keyword().onField("id").matching(ids).createQuery());
        }
        Query idQuery = boolJunction.createQuery();// method boolean AND (must().must()), which equals output
        Query combinedQuery = queryBuilder.bool().must(query).must(idQuery).createQuery();

        org.hibernate.search.jpa.FullTextQuery hibQuery = fullTextSession
                .createFullTextQuery(combinedQuery, Document.class)
                .setFirstResult(10 * (page - 1)).setMaxResults(10);

        List<Document> docs = hibQuery.getResultList();
        for (Document doc : docs) {
            logger.info("Documents list. Document: " + doc);
        }
        return docs;
    }

}
