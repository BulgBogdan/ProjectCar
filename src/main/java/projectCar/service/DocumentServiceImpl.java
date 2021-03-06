package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.IDocumentDAO;
import projectCar.entity.Document;
import projectCar.service.interfaces.IDocumentService;

import java.util.List;

@Service
public class DocumentServiceImpl implements IDocumentService {

    private IDocumentDAO documentDAO;

    @Autowired
    public DocumentServiceImpl(IDocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Override
    @Transactional
    public void add(Document document) {
        documentDAO.add(document);
    }

    @Override
    @Transactional
    public void update(Document document) {
        documentDAO.update(document);
    }

    @Override
    @Transactional
    public Document read(int id) {
        return documentDAO.read(id);
    }

    @Override
    @Transactional
    public void delete(Document document) {
        documentDAO.delete(document);
    }

    @Override
    @Transactional
    public int docsCount(int id) {
        return documentDAO.docsCount(id);
    }

    @Override
    @Transactional
    public List<Document> getDocuments(int page, int id) {
        return documentDAO.getDocuments(page, id);
    }

    @Override
    @Transactional
    public List<Document> getAll() {
        return documentDAO.getAll();
    }

    @Override
    @Transactional
    public List<Document> searchList(String textSearch, int id, int page) {
        return documentDAO.searchList(textSearch, id, page);
    }
}
