package projectCar.dao.interfaces;

import projectCar.entity.Car;

import java.util.List;

public interface ICarDAO {

    void add(Car car);

    void update(Car car);

    Car read(int id);

    void delete(Car car);

    int carsCount();

    List<Car> getAll();

    List<Car> getCars(int page);

    List<Car> getLists(int id);
}
