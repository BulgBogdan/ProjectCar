package projectCar.entity;

import lombok.Data;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.sql.Date;

@Data
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

}
