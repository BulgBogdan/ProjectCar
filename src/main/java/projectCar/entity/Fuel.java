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

    @Column(name = "literCost")
    private double literCost;

    @Column(name = "literValue")
    private double lietrValue;

    @Column(name = "summ")
    private double summ;

    @ManyToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

    public Fuel() {
    }

    public Fuel(double literCost, double lietrValue, double summ, Car car) {
        this.literCost = literCost;
        this.lietrValue = lietrValue;
        this.summ = summ;
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fuel fuel = (Fuel) o;
        return id == fuel.id &&
                Double.compare(fuel.literCost, literCost) == 0 &&
                Double.compare(fuel.lietrValue, lietrValue) == 0 &&
                Double.compare(fuel.summ, summ) == 0 &&
                Objects.equals(car, fuel.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, literCost, lietrValue, summ, car);
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "id=" + id +
                ", literCost=" + literCost +
                ", lietrValue=" + lietrValue +
                ", summ=" + summ +
                ", car=" + car +
                '}';
    }
}
