package projectCar.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "documents", schema = "projectcar")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nameDocument", nullable = false, length = 100)
    private String nameDocument;

    @Column(name = "documentCost")
    private double documentCost;

    @Column(name = "beginDate", nullable = false)
    private Date beginDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @Column(name = "numberOfDays", nullable = false)
    private int numberOf;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_cars")
    private Car car;

    public Document() {
    }

    public Document(String nameDocument, double documentCost, Date beginDate, Date endDate, int numberOf, Car car) {
        this.nameDocument = nameDocument;
        this.documentCost = documentCost;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.numberOf = numberOf;
        this.car = car;
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

    public double getDocumentCost() {
        return documentCost;
    }

    public void setDocumentCost(double documentCost) {
        this.documentCost = documentCost;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(int numberOfDays) {
        this.numberOf = numberOfDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return id == document.id &&
                Double.compare(document.documentCost, documentCost) == 0 &&
                numberOf == document.numberOf &&
                Objects.equals(nameDocument, document.nameDocument) &&
                Objects.equals(beginDate, document.beginDate) &&
                Objects.equals(endDate, document.endDate) &&
                Objects.equals(car, document.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameDocument, documentCost, beginDate, endDate, numberOf, car);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", nameDocument='" + nameDocument + '\'' +
                ", documentCost=" + documentCost +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", numberOfDays=" + numberOf +
                ", car=" + car +
                '}';
    }
}
