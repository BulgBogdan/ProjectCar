package projectCar.service;

import projectCar.entity.Parameter;

import java.util.List;

public interface IParameterService {

    void add(Parameter parameter);

    void update(Parameter parameter);

    Parameter read(int id);

    void delete(Parameter parameter);

    List<Parameter> getAll();
}
