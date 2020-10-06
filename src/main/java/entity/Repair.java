package entity;

import java.util.Objects;

public class Repair {
    private int id;
    private String nameRepair;
    private int beginMileage;
    private int endMileage;
    private double costsRepair;
    private int FK_cars;

    public Repair() {
    }

    public Repair(int id, String nameRepair, int beginMileage, int endMileage, double costsRepair, int FK_cars) {
        this.id = id;
        this.nameRepair = nameRepair;
        this.beginMileage = beginMileage;
        this.endMileage = endMileage;
        this.costsRepair = costsRepair;
        this.FK_cars = FK_cars;
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
        Repair repair = (Repair) o;
        return id == repair.id &&
                beginMileage == repair.beginMileage &&
                endMileage == repair.endMileage &&
                Double.compare(repair.costsRepair, costsRepair) == 0 &&
                FK_cars == repair.FK_cars &&
                Objects.equals(nameRepair, repair.nameRepair);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameRepair, beginMileage, endMileage, costsRepair, FK_cars);
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", nameRepair='" + nameRepair + '\'' +
                ", beginMileage=" + beginMileage +
                ", endMileage=" + endMileage +
                ", costsRepair=" + costsRepair +
                ", FK_cars=" + FK_cars +
                '}';
    }
}
