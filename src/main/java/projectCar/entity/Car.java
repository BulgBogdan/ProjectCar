package projectCar.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "cars", schema = "projectcar")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_car", nullable = false, length = 100)
    private String nameCar;

    @Column(name = "mileage")
    private int mileage;

    @ManyToOne
    @JoinColumn(name = "FK_users")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "car")
    private Registration registration;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "car")
    private Parameter parameters;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    @IndexedEmbedded(includePaths = "nameDocument")
    private List<Document> documents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<Fuel> fuels;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    @IndexedEmbedded(includePaths = "nameOtherCost")
    private List<OtherCosts> otherCosts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    @IndexedEmbedded(includePaths = "nameRepair")
    private List<Repair> repairs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                mileage == car.mileage &&
                Objects.equals(nameCar, car.nameCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCar, mileage);
    }
}
