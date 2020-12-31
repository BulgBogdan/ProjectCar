package projectCar.service.interfaces;

import projectCar.entity.OtherCosts;

import java.util.List;

public interface IOtherCostsService {

    void add(OtherCosts otherCosts);

    void update(OtherCosts otherCosts);

    OtherCosts read(int id);

    void delete(OtherCosts otherCosts);

    int otherCostsCount (int id);

    List<OtherCosts> getOtherCosts (int page, int id);

    List<OtherCosts> getAll();

    List<OtherCosts> searchList(String textSearch, int id, int page);
}
