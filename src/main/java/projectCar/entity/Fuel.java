package projectCar.entity;

import javax.persistence.*;
import java.util.Objects;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_cars")
    private Car car;

    public Fuel() {
    }

    public Fuel(double literCost, double literValue, double summ, double fuelDistance, Car car) {
        this.literCost = literCost;
        this.literValue = literValue;
        this.summ = summ;
        this.fuelDistance = fuelDistance;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLiterCost() {
        return literCost;
    }

    public void setLiterCost(double literCost) {
        this.literCost = literCost;
    }

    public double getLiterValue() {
        return literValue;
    }

    public void setLiterValue(double lietrValue) {
        this.literValue = lietrValue;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public double getFuelDistance() {
        return fuelDistance;
    }

    public void setFuelDistance(double fuelDistance) {
        this.fuelDistance = fuelDistance;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fuel fuel = (Fuel) o;
        return id == fuel.id &&
                Double.compare(fuel.literCost, literCost) == 0 &&
                Double.compare(fuel.literValue, literValue) == 0 &&
                Double.compare(fuel.summ, summ) == 0 &&
                Double.compare(fuel.fuelDistance, fuelDistance) == 0 &&
                Objects.equals(car, fuel.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, literCost, literValue, summ, fuelDistance, car);
    }
}
