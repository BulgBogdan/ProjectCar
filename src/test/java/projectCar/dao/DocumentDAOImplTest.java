package projectCar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.dao.interfaces.IDocumentDAO;
import projectCar.entity.Car;
import projectCar.entity.Document;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DocumentDAOImplTest {

    @Autowired
    private ICarDAO carDAO;

    @Autowired
    private IDocumentDAO documentDAO;

    private Car car;

    private Document document;

    @Test
    void add() {
        Document doc = new Document();
        car = carDAO.read(1);
        doc.setNameDocument("doc");
        doc.setBeginDate(Date.valueOf("2020-11-01"));
        doc.setDocumentCost(1);
        doc.setCar(car);
        documentDAO.add(doc);
        int docId = doc.getId();
        document = documentDAO.read(docId);
        assertEquals(document.getNameDocument(), "doc");
        assertEquals(document.getDocumentCost(), 1);
    }

    @Test
    void update() {
        document = documentDAO.read(1);
        document.setNameDocument("testDoc");
        documentDAO.update(document);
        String testDoc = documentDAO.read(1).getNameDocument();
        assertEquals(testDoc, "testDoc");
    }

    @Test
    void read() {
        document = documentDAO.read(1);
        assertNotNull(document);
    }

    @Test
    void delete() {
        document = documentDAO.read(1);
        documentDAO.delete(document);
        Document documentDelete = documentDAO.read(1);
        assertNotEquals(documentDelete, document);
    }

    @Test
    void docsCount() {
        car = carDAO.read(1);
        int docsCount = documentDAO.docsCount(car.getId());
        boolean notNullCountDoc = false;
        if (docsCount != 0) {
            notNullCountDoc = true;
        }
        assertTrue(notNullCountDoc);
    }

    @Test
    void getAll() {
        List<Document> docs = documentDAO.getAll();
        assertNotNull(docs);
    }
}