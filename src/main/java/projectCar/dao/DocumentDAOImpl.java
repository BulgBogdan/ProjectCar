package projectCar.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import projectCar.entity.Document;

import java.util.List;

public class DocumentDAOImpl implements IDocumentDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(document);

    }

    @Override
    public void update(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.update(document);
    }

    @Override
    public Document read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Document document = session.get(Document.class,id);
        return document;
    }

    @Override
    public void delete(Document document) {
        Session session = sessionFactory.getCurrentSession();
        if (session!=null){
            session.delete(document);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Document> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Document> listDocument = session.createQuery("from Document").list();
        return listDocument;
    }
}
