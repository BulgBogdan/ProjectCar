package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.IFuelDAO;
import projectCar.entity.Fuel;
import projectCar.service.interfaces.IFuelService;

import java.util.List;

@Service
public class FuelServiceImpl implements IFuelService {

    private IFuelDAO fuelDAO;

    @Autowired
    public FuelServiceImpl(IFuelDAO fuelDAO) {
        this.fuelDAO = fuelDAO;
    }

    @Override
    @Transactional
    public void add(Fuel fuel) {
        fuelDAO.add(fuel);
    }

    @Override
    @Transactional
    public void update(Fuel fuel) {
        fuelDAO.update(fuel);
    }

    @Override
    @Transactional
    public Fuel read(int id) {
        return fuelDAO.read(id);
    }

    @Override
    @Transactional
    public void delete(Fuel fuel) {
        fuelDAO.delete(fuel);
    }

    @Override
    @Transactional
    public int fuelCount(int id) {
        return fuelDAO.fuelCount(id);
    }

    @Override
    @Transactional
    public List<Fuel> getFuel(int page, int id) {
        return fuelDAO.getFuel(page,id);
    }

    @Override
    @Transactional
    public List<Fuel> getAll() {
        return fuelDAO.getAll();
    }

    @Override
    @Transactional
    public List<Fuel> getAllByCar(int idCar) {
        return fuelDAO.getAllByCar(idCar);
    }
}
