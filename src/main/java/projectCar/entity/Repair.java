package projectCar.entity;

import lombok.Data;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.*;

import javax.persistence.*;

@Data
@Entity
@Indexed
@Table(name = "repairs", schema = "projectcar")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_repair", nullable = false, length = 100)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String nameRepair;

    @Column(name = "start_mileage")
    private int beginMileage;

    @Column(name = "end_mileage")
    private int endMileage;

    @Column(name = "service_life")
    private int serviceLife;

    @Column(name = "cost_repair")
    private double costsRepair;

    @ManyToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "FK_currency")
    private Currency currency;

}
