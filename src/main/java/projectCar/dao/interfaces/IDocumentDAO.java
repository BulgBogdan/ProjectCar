package projectCar.dao.interfaces;

import projectCar.entity.Document;

import java.util.List;

public interface IDocumentDAO {

    void add(Document document);

    void update(Document document);

    Document read(int id);

    void delete(Document document);

    int docsCount(int id);

    List<Document> getDocuments(int page, int id);

    List<Document> getAll();

    List<Document> searchList(String textSearch, int id, int page);
}
