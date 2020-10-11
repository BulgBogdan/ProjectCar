package projectCar.service.interfaces;

import projectCar.entity.Car;

import java.util.List;

public interface ICarService {

    void add(Car car);

    void update(Car car);

    Car read(int id);

    void delete(Car car);

    List<Car> getAll();
}