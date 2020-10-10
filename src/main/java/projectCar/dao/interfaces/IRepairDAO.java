package projectCar.dao.interfaces;

import projectCar.entity.Repair;

import java.util.List;

public interface IRepairDAO {

    void add(Repair repair);

    void update(Repair repair);

    Repair read(int id);

    void delete(Repair repair);

    List<Repair> getAll();
}
