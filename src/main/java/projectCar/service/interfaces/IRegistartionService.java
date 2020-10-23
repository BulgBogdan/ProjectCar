package projectCar.service.interfaces;

import projectCar.entity.Registration;

import java.util.List;

public interface IRegistartionService {

    void add(Registration registration);

    void update(Registration registration);

    Registration read(int id);

    void delete(Registration registration);

    List<Registration> getAll();
}
