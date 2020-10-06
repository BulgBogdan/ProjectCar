package entity;

import java.util.Date;
import java.util.Objects;

public class Document {
    private int id;
    private String nameDocument;
    private Date beginDate;
    private Date endDate;
    private int FK_cars;

    public Document() {
    }

    public Document(int id, String nameDocument, Date beginDate, Date endDate, int FK_cars) {
        this.id = id;
        this.nameDocument = nameDocument;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.FK_cars = FK_cars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDocument() {
        return nameDocument;
    }

    public void setNameDocument(String nameDocument) {
        this.nameDocument = nameDocument;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getFK_cars() {
        return FK_cars;
    }

    public void setFK_cars(int FK_cars) {
        this.FK_cars = FK_cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return id == document.id &&
                FK_cars == document.FK_cars &&
                Objects.equals(nameDocument, document.nameDocument) &&
                Objects.equals(beginDate, document.beginDate) &&
                Objects.equals(endDate, document.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameDocument, beginDate, endDate, FK_cars);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", nameDocument='" + nameDocument + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", FK_cars=" + FK_cars +
                '}';
    }
}
