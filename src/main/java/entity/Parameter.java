package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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

    @Column(name = "year", nullable = false)
    private Date year;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "mass")
    private int mass;

    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @Column(name = "averageRate")
    private double averageRate;

    @Column(name = "vin", nullable = false, length = 45)
    private String vin;

    @Column(name = "registrationNumber", nullable = false, length = 45)
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

    public Parameter() {
    }

    public Parameter(String mark, String model, Date year, int mileage, int mass, String color, double averageRate, String vin, String registrationNumber, Car car) {
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.mass = mass;
        this.color = color;
        this.averageRate = averageRate;
        this.vin = vin;
        this.registrationNumber = registrationNumber;
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return id == parameter.id &&
                mileage == parameter.mileage &&
                mass == parameter.mass &&
                Double.compare(parameter.averageRate, averageRate) == 0 &&
                Objects.equals(mark, parameter.mark) &&
                Objects.equals(model, parameter.model) &&
                Objects.equals(year, parameter.year) &&
                Objects.equals(color, parameter.color) &&
                Objects.equals(vin, parameter.vin) &&
                Objects.equals(registrationNumber, parameter.registrationNumber) &&
                Objects.equals(car, parameter.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, model, year, mileage, mass, color, averageRate, vin, registrationNumber, car);
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", mass=" + mass +
                ", color='" + color + '\'' +
                ", averageRate=" + averageRate +
                ", vin='" + vin + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", car=" + car +
                '}';
    }
}
