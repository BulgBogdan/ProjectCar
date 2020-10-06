package entity;

import java.util.Objects;

public class Fuel {
    private int id;
    private double literCost;
    private double lietrValue;
    private double summ;
    private int FK_cars;

    public Fuel() {
    }

    public Fuel(int id, double literCost, double lietrValue, double summ, int FK_cars) {
        this.id = id;
        this.literCost = literCost;
        this.lietrValue = lietrValue;
        this.summ = summ;
        this.FK_cars = FK_cars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLiterCost() {
        return literCost;
    }

    public void setLiterCost(double literCost) {
        this.literCost = literCost;
    }

    public double getLietrValue() {
        return lietrValue;
    }

    public void setLietrValue(double lietrValue) {
        this.lietrValue = lietrValue;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
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
        Fuel fuel = (Fuel) o;
        return id == fuel.id &&
                Double.compare(fuel.literCost, literCost) == 0 &&
                Double.compare(fuel.lietrValue, lietrValue) == 0 &&
                Double.compare(fuel.summ, summ) == 0 &&
                FK_cars == fuel.FK_cars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, literCost, lietrValue, summ, FK_cars);
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "id=" + id +
                ", literCost=" + literCost +
                ", lietrValue=" + lietrValue +
                ", summ=" + summ +
                ", FK_cars=" + FK_cars +
                '}';
    }
}
