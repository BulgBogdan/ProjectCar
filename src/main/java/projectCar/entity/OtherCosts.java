package projectCar.entity;

import lombok.Data;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Indexed
@Table(name = "other_costs", schema = "projectcar")
public class OtherCosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_other_cost", nullable = false, length = 100)
    @Field
    private String nameOtherCost;

    @Column(name = "cost_date", nullable = false)
    private Date dateCost;

    @Column(name = "cost")
    private double cost;

    @ManyToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

}