package projectCar.entity;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.Objects;

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

    public Repair() {
    }

    public Repair(String nameRepair, int beginMileage, int endMileage, int serviceLife, double costsRepair, Car car) {
        this.nameRepair = nameRepair;
        this.beginMileage = beginMileage;
        this.endMileage = endMileage;
        this.serviceLife = serviceLife;
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

    public int getServiceLife() {
        return serviceLife;
    }

    public void setServiceLife(int serviceLife) {
        this.serviceLife = serviceLife;
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
                serviceLife == repair.serviceLife &&
                Double.compare(repair.costsRepair, costsRepair) == 0 &&
                Objects.equals(nameRepair, repair.nameRepair) &&
                Objects.equals(car, repair.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameRepair, beginMileage, endMileage, serviceLife, costsRepair, car);
    }
}
