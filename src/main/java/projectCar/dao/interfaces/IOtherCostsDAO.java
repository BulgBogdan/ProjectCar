package projectCar.dao.interfaces;

import projectCar.entity.OtherCosts;

import java.util.List;

public interface IOtherCostsDAO {

    void add(OtherCosts otherCosts);

    void update(OtherCosts otherCosts);

    OtherCosts read(int id);

    void delete(OtherCosts otherCosts);

    int otherCostsCount ();

    List<OtherCosts> getOtherCosts (int page, int id);

    List<OtherCosts> getAll();
}
