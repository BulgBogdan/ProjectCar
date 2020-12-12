package projectCar.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "registration", schema = "projectcar")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "price_car")
    private int priceCar;

    @Column(name = "price_registration")
    private double priceRegistration;

    @OneToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

}
