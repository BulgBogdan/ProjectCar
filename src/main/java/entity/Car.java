package entity;

import java.util.Objects;

public class Car {
    private int id;
    private String nameCar;
    private int FK_users;

    public Car() {
    }

    public Car(int id, String nameCar, int FK_users) {
        this.id = id;
        this.nameCar = nameCar;
        this.FK_users = FK_users;
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

    public int getFK_users() {
        return FK_users;
    }

    public void setFK_users(int FK_users) {
        this.FK_users = FK_users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                FK_users == car.FK_users &&
                Objects.equals(nameCar, car.nameCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCar, FK_users);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", nameCar='" + nameCar + '\'' +
                ", FK_users=" + FK_users +
                '}';
    }
}
