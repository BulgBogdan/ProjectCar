package projectCar.service.interfaces;

import projectCar.entity.Car;

import java.util.List;

public interface ICarService {

    void add(Car car);

    void update(Car car);

    Car read(int id);

    void delete(Car car);

    int carsCount(int idUser);

    List<Car> getAll();

    List<Car> getCars(int page, int id);

    List<Car> getLists(int id);

    List<Car> searchList(String textSearch, int id, int page);

}
