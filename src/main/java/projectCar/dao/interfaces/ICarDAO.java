package projectCar.dao.interfaces;

import projectCar.entity.Car;

import java.util.List;

public interface ICarDAO {

    void add(Car car);

    void update(Car car);

    Car read(int id);

    void delete(Car car);

    int carsCount(int id);

    List<Car> getAll();

    List<Car> getCars(int page,int id);

    List<Car> getLists(int id);

    List<Car> searchList(String textSearch, int id, int page);
}
