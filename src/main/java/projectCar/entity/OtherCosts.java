package projectCar.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "other_costs", schema = "projectcar")
public class OtherCosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nameOtherCost", nullable = false, length = 100)
    private String nameOtherCost;

    @Column(name = "dateCost", nullable = false)
    private Date dateCost;

    @Column(name = "cost")
    private double cost;

    @ManyToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

    public OtherCosts() {
    }

    public OtherCosts(String nameOtherCost, Date dateCost, double cost, Car car) {
        this.nameOtherCost = nameOtherCost;
        this.dateCost = dateCost;
        this.cost = cost;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOtherCost() {
        return nameOtherCost;
    }

    public void setNameOtherCost(String nameOtherCost) {
        this.nameOtherCost = nameOtherCost;
    }

    public Date getDateCost() {
        return dateCost;
    }

    public void setDateCost(Date dateCost) {
        this.dateCost = dateCost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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
        OtherCosts that = (OtherCosts) o;
        return id == that.id &&
                Double.compare(that.cost, cost) == 0 &&
                Objects.equals(nameOtherCost, that.nameOtherCost) &&
                Objects.equals(dateCost, that.dateCost) &&
                Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOtherCost, dateCost, cost, car);
    }

    @Override
    public String toString() {
        return "OtherCosts{" +
                "id=" + id +
                ", nameOtherCost='" + nameOtherCost + '\'' +
                ", dateCost=" + dateCost +
                ", cost=" + cost +
                ", car=" + car +
                '}';
    }
}
