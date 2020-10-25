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

    @ManyToOne
    @JoinColumn(name = "FK_users")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "car")
    private Registration registration;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "car")
    private Parameter parameters;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<Document> documents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<Fuel> fuels;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<OtherCosts> otherCosts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car")
    private List<Repair> repairs;

    public Car() {
    }

    public Car(String nameCar, User user, Registration registration, Parameter parameters, List<Document> documents,
               List<Fuel> fuels, List<OtherCosts> otherCosts, List<Repair> repairs) {
        this.nameCar = nameCar;
        this.user = user;
        this.registration = registration;
        this.parameters = parameters;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Parameter getParameters() {
        return parameters;
    }

    public void setParameters(Parameter parameters) {
        this.parameters = parameters;
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
                Objects.equals(nameCar, car.nameCar) &&
                Objects.equals(user, car.user) &&
                Objects.equals(registration, car.registration) &&
                Objects.equals(parameters, car.parameters) &&
                Objects.equals(documents, car.documents) &&
                Objects.equals(fuels, car.fuels) &&
                Objects.equals(otherCosts, car.otherCosts) &&
                Objects.equals(repairs, car.repairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCar, user, registration, parameters, documents, fuels, otherCosts, repairs);
    }

}
