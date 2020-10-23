package projectCar.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "registration", schema = "projectcar")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "priceCar", nullable = false)
    private int priceCar;

    @Column(name = "priceRegistration", nullable = false)
    private double priceRegistration;

    @OneToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

    public Registration() {
    }

    public Registration(int priceCar, double priceRegistration, Car car) {
        this.priceCar = priceCar;
        this.priceRegistration = priceRegistration;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriceCar() {
        return priceCar;
    }

    public void setPriceCar(int priceCar) {
        this.priceCar = priceCar;
    }

    public double getPriceRegistration() {
        return priceRegistration;
    }

    public void setPriceRegistration(double priceRegistration) {
        this.priceRegistration = priceRegistration;
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
        Registration that = (Registration) o;
        return id == that.id &&
                priceCar == that.priceCar &&
                Double.compare(that.priceRegistration, priceRegistration) == 0 &&
                Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priceCar, priceRegistration, car);
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", priceCar=" + priceCar +
                ", priceRegistration=" + priceRegistration +
                ", car=" + car +
                '}';
    }
}
