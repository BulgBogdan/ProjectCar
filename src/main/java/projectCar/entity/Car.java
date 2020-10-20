package projectCar.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cars", schema = "projectcar")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nameCar", nullable = false, length = 100)
    private String nameCar;

    @Column(name = "priceCar")
    private double priceCar;

    @Column(name = "costRegistration")
    private double costRegistration;

    @ManyToOne
    @JoinColumn(name = "FK_users")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<Document> documents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<Fuel> fuels;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<OtherCosts> otherCosts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<Repair> repairs;

    public Car() {
    }

    public Car(String nameCar, double priceCar, double costRegistration, User user, List<Document> documents,
               List<Fuel> fuels, List<OtherCosts> otherCosts, List<Repair> repairs) {
        this.nameCar = nameCar;
        this.priceCar = priceCar;
        this.costRegistration = costRegistration;
        this.user = user;
        this.documents = documents;
        this.fuels = fuels;
        this.otherCosts = otherCosts;
        this.repairs = repairs;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public double getPriceCar() {
        return priceCar;
    }

    public void setPriceCar(double priceCar) {
        this.priceCar = priceCar;
    }

    public double getCostRegistration() {
        return costRegistration;
    }

    public void setCostRegistration(double costRegistration) {
        this.costRegistration = costRegistration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Fuel> getFuels() {
        return fuels;
    }

    public void setFuels(List<Fuel> fuels) {
        this.fuels = fuels;
    }

    public List<OtherCosts> getOtherCosts() {
        return otherCosts;
    }

    public void setOtherCosts(List<OtherCosts> otherCosts) {
        this.otherCosts = otherCosts;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Double.compare(car.priceCar, priceCar) == 0 &&
                Double.compare(car.costRegistration, costRegistration) == 0 &&
                Objects.equals(nameCar, car.nameCar) &&
                Objects.equals(user, car.user) &&
                Objects.equals(documents, car.documents) &&
                Objects.equals(fuels, car.fuels) &&
                Objects.equals(otherCosts, car.otherCosts) &&
                Objects.equals(repairs, car.repairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCar, priceCar, costRegistration, user, documents, fuels, otherCosts, repairs);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", nameCar='" + nameCar + '\'' +
                ", priceCar=" + priceCar +
                ", costRegistration=" + costRegistration +
                ", user=" + user +
                ", documents=" + documents +
                ", fuels=" + fuels +
                ", otherCosts=" + otherCosts +
                ", repairs=" + repairs +
                '}';
    }
}
