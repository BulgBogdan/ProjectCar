package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.CarDAOImpl;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.dao.interfaces.IDocumentDAO;
import projectCar.entity.Car;
import projectCar.entity.Document;
import projectCar.service.interfaces.IDocumentService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class DocumentServiceImplTest {

    @Autowired
    private IDocumentService documentService;

    @MockBean
    private IDocumentDAO documentDAO;

    @Autowired
    private ICarDAO carDAO = new CarDAOImpl();

    private Document useDocument() {
        Document document = new Document();
        Car car = carDAO.read(1);
        document.setBeginDate(Date.valueOf("2020-11-01"));
        document.setEndDate(Date.valueOf("2021-11-01"));
        document.setNameDocument("document");
        document.setDocumentCost(1);
        document.setCar(car);
        return document;
    }

    private Document documentTest;

    @Test
    void add() {
        documentTest = useDocument();
        documentService.add(documentTest);
        Mockito.verify(documentDAO, Mockito.times(1)).add(documentTest);
    }

    @Test
    void update() {
        documentTest = useDocument();
        documentTest.setNameDocument("documentTest");
        documentService.update(documentTest);
        Mockito.verify(documentDAO, Mockito.times(1)).update(documentTest);
    }

    @Test
    void read() {
        documentTest = useDocument();
        Mockito.when(documentDAO.read(1)).thenReturn(documentTest);
        Document foundDocument = documentService.read(1);
        assertEquals("document", foundDocument.getNameDocument());
    }

    @Test
    void delete() {
        documentTest = useDocument();
        documentService.delete(documentTest);
        Mockito.verify(documentDAO, Mockito.times(1)).delete(documentTest);
    }

    @Test
    void docsCount() {
        Car car = carDAO.read(1);
        int docsCount = 0;
        Mockito.when(documentService.docsCount(car.getId())).thenReturn(docsCount);
        assertEquals(docsCount, documentDAO.docsCount(car.getId()));
    }

    @Test
    void getAllDocs() {
        List<Document> documentList = new ArrayList<>();
        Mockito.when(documentDAO.getAll()).thenReturn(documentList);
        List<Document> docs = documentService.getAll();
        assertEquals(docs, documentList);
    }
}