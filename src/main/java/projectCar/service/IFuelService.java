package projectCar.service;

import projectCar.entity.Fuel;

import java.util.List;

public interface IFuelService {

    void add(Fuel fuel);

    void update(Fuel fuel);

    Fuel read(int id);

    void delete(Fuel fuel);

    List<Fuel> getAll();
}
