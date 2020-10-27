package projectCar.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "parameters", schema = "projectcar")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "mark", nullable = false, length = 100)
    private String mark;

    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Column(name = "year", nullable = false)
    private Date year;

    @Column(name = "first_mileage")
    private int firstMileage;

    @Column(name = "mass")
    private int mass;

    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @Column(name = "average_rate")
    private double averageRate;

    @Column(name = "vin", nullable = false, length = 45)
    private String vin;

    @Column(name = "registration_number", nullable = false, length = 45)
    private String registrationNumber;

    @OneToOne
    @JoinColumn(name = "FK_cars")
    private Car car;

    public Parameter() {
    }

    public Parameter(String mark, String model, Date year, int firstMileage, int mass, String color, double averageRate,
                     String vin, String registrationNumber, Car car) {
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.firstMileage = firstMileage;
        this.mass = mass;
        this.color = color;
        this.averageRate = averageRate;
        this.vin = vin;
        this.registrationNumber = registrationNumber;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getFirstMileage() {
        return firstMileage;
    }

    public void setFirstMileage(int mileage) {
        this.firstMileage = mileage;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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
        Parameter parameter = (Parameter) o;
        return id == parameter.id &&
                firstMileage == parameter.firstMileage &&
                mass == parameter.mass &&
                Double.compare(parameter.averageRate, averageRate) == 0 &&
                Objects.equals(mark, parameter.mark) &&
                Objects.equals(model, parameter.model) &&
                Objects.equals(year, parameter.year) &&
                Objects.equals(color, parameter.color) &&
                Objects.equals(vin, parameter.vin) &&
                Objects.equals(registrationNumber, parameter.registrationNumber) &&
                Objects.equals(car, parameter.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, model, year, firstMileage, mass, color, averageRate, vin, registrationNumber, car);
    }
}
