package projectCar.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fuel", schema = "projectcar")
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "liter_cost")
    private double literCost;

    @Column(name = "liter_value")
    private double literValue;

    @Column(name = "summ")
    private double summ;

    @Column(name = "fuel_distance")
    private double fuelDistance;

    @ManyToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

}
