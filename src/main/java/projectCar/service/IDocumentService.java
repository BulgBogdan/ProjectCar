package projectCar.service;

import projectCar.entity.Document;

import java.util.List;

public interface IDocumentService {

    void add(Document document);

    void update(Document document);

    Document read(int id);

    void delete(Document document);

    List<Document> getAll();
}
