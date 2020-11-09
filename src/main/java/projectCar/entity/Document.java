package projectCar.entity;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Indexed
@Table(name = "documents", schema = "projectcar")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_document", nullable = false, length = 100)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String nameDocument;

    @Column(name = "cost_document")
    private double documentCost;

    @Column(name = "start_date", nullable = false)
    private Date beginDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "number_of_days", nullable = false)
    private int numberOfDays;

    @Column(name = "number_of_month", nullable = false)
    private int numberOfMonth;

    @ManyToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

    public Document() {
    }

    public Document(String nameDocument, double documentCost, Date beginDate, Date endDate,
                    int numberOfDays, int numberOfMonth, Car car) {
        this.nameDocument = nameDocument;
        this.documentCost = documentCost;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.numberOfDays = numberOfDays;
        this.numberOfMonth = numberOfMonth;
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

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public int getNumberOfMonth() {
        return numberOfMonth;
    }

    public void setNumberOfMonth(int numberOfMonth) {
        this.numberOfMonth = numberOfMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return id == document.id &&
                Double.compare(document.documentCost, documentCost) == 0 &&
                numberOfDays == document.numberOfDays &&
                numberOfMonth == document.numberOfMonth &&
                Objects.equals(nameDocument, document.nameDocument) &&
                Objects.equals(beginDate, document.beginDate) &&
                Objects.equals(endDate, document.endDate) &&
                Objects.equals(car, document.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameDocument, documentCost, beginDate, endDate, numberOfDays, numberOfMonth, car);
    }
}
