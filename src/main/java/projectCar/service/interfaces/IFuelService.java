package projectCar.service.interfaces;

import projectCar.entity.Fuel;

import java.util.List;

public interface IFuelService {

    void add(Fuel fuel);

    void update(Fuel fuel);

    Fuel read(int id);

    void delete(Fuel fuel);

    int fuelCount (int id);

    List<Fuel> getFuel (int page, int id);

    List<Fuel> getAll();
}
