package projectCar.dao.interfaces;

import projectCar.entity.Fuel;

import java.util.List;

public interface IFuelDAO {

    void add(Fuel fuel);

    void update(Fuel fuel);

    Fuel read(int id);

    void delete(Fuel fuel);

    int fuelCount (int id);

    List<Fuel> getFuel (int page, int id);

    List<Fuel> getAll();

    List<Fuel> getAllByCar(int idCar);
}
