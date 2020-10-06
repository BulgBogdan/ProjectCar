package entity;

import java.util.Date;
import java.util.Objects;

public class OtherCosts {
    private int id;
    private String nameOtherCost;
    private Date dateCost;
    private double cost;
    private int FK_cars;

    public OtherCosts() {
    }

    public OtherCosts(int id, String nameOtherCost, Date dateCost, double cost, int FK_cars) {
        this.id = id;
        this.nameOtherCost = nameOtherCost;
        this.dateCost = dateCost;
        this.cost = cost;
        this.FK_cars = FK_cars;
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
        OtherCosts that = (OtherCosts) o;
        return id == that.id &&
                Double.compare(that.cost, cost) == 0 &&
                FK_cars == that.FK_cars &&
                Objects.equals(nameOtherCost, that.nameOtherCost) &&
                Objects.equals(dateCost, that.dateCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOtherCost, dateCost, cost, FK_cars);
    }

    @Override
    public String toString() {
        return "OtherCosts{" +
                "id=" + id +
                ", nameOtherCost='" + nameOtherCost + '\'' +
                ", dateCost=" + dateCost +
                ", cost=" + cost +
                ", FK_cars=" + FK_cars +
                '}';
    }
}
