package projectCar.service;

import projectCar.entity.OtherCosts;

import java.util.List;

public interface IOtherCostsService {

    void add(OtherCosts otherCosts);

    void update(OtherCosts otherCosts);

    OtherCosts read(int id);

    void delete(OtherCosts otherCosts);

    List<OtherCosts> getAll();
}
