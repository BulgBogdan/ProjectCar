package projectCar.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "repairs", schema = "projectcar")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nameRepair", nullable = false, length = 100)
    private String nameRepair;

    @Column(name = "beginMileage")
    private int beginMileage;

    @Column(name = "endMileage")
    private int endMileage;

    @Column(name = "costsRepair")
    private double costsRepair;

    @ManyToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

    public Repair() {
    }

    public Repair(String nameRepair, int beginMileage, int endMileage, double costsRepair, Car car) {
        this.nameRepair = nameRepair;
        this.beginMileage = beginMileage;
        this.endMileage = endMileage;
        this.costsRepair = costsRepair;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRepair() {
        return nameRepair;
    }

    public void setNameRepair(String nameRepair) {
        this.nameRepair = nameRepair;
    }

    public int getBeginMileage() {
        return beginMileage;
    }

    public void setBeginMileage(int beginMileage) {
        this.beginMileage = beginMileage;
    }

    public int getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(int endMileage) {
        this.endMileage = endMileage;
    }

    public double getCostsRepair() {
        return costsRepair;
    }

    public void setCostsRepair(double costsRepair) {
        this.costsRepair = costsRepair;
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
        Repair repair = (Repair) o;
        return id == repair.id &&
                beginMileage == repair.beginMileage &&
                endMileage == repair.endMileage &&
                Double.compare(repair.costsRepair, costsRepair) == 0 &&
                Objects.equals(nameRepair, repair.nameRepair) &&
                Objects.equals(car, repair.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameRepair, beginMileage, endMileage, costsRepair, car);
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", nameRepair='" + nameRepair + '\'' +
                ", beginMileage=" + beginMileage +
                ", endMileage=" + endMileage +
                ", costsRepair=" + costsRepair +
                ", car=" + car +
                '}';
    }
}
