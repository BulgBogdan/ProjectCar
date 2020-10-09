package projectCar.dao;

import projectCar.entity.Document;

import java.util.List;

public interface IDocumentDAO {

    void add(Document document);

    void update(Document document);

    Document read(int id);

    void delete(Document document);

    List<Document> getAll();
}
