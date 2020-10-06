package entity;

import java.util.Date;
import java.util.Objects;

public class Parameter {
    private int id;
    private String mark;
    private String model;
    private Date year;
    private int mileage;
    private int mass;
    private String color;
    private double averageRate;
    private String vin;
    private String registrationNumber;
    private int FK_cars;

    public Parameter() {
    }

    public Parameter(int id, String mark, String model, Date year, int mileage, int mass, String color, double averageRate, String vin, String registrationNumber, int FK_cars) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.mass = mass;
        this.color = color;
        this.averageRate = averageRate;
        this.vin = vin;
        this.registrationNumber = registrationNumber;
        this.FK_cars = FK_cars;
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

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
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

    public int getFK_cars() {
        return FK_cars;
    }

    public void setFK_cars(int FK_cars) {
        this.FK_cars = FK_cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return id == parameter.id &&
                mileage == parameter.mileage &&
                mass == parameter.mass &&
                Double.compare(parameter.averageRate, averageRate) == 0 &&
                FK_cars == parameter.FK_cars &&
                Objects.equals(mark, parameter.mark) &&
                Objects.equals(model, parameter.model) &&
                Objects.equals(year, parameter.year) &&
                Objects.equals(color, parameter.color) &&
                Objects.equals(vin, parameter.vin) &&
                Objects.equals(registrationNumber, parameter.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, model, year, mileage, mass, color, averageRate, vin, registrationNumber, FK_cars);
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", mass=" + mass +
                ", color='" + color + '\'' +
                ", averageRate=" + averageRate +
                ", vin='" + vin + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", FK_cars=" + FK_cars +
                '}';
    }
}
