package projectCar.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "parameters", schema = "projectcar")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "mark", nullable = false, length = 100)
    private String mark;

    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "first_mileage")
    private int firstMileage;

    @Column(name = "mass")
    private int mass;

    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @Column(name = "average_rate")
    private double averageRate;

    @Column(name = "vin", nullable = false, length = 45)
    private String vin;

    @Column(name = "registration_number", nullable = false, length = 45)
    private String registrationNumber;

    @OneToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

}
