package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.CarDAOImpl;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.entity.Car;
import projectCar.service.interfaces.ICarService;

import java.util.List;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private ICarDAO carDAO = new CarDAOImpl();

    @Override
    @Transactional
    public void add(Car car) {
        carDAO.add(car);
    }

    @Override
    @Transactional
    public void update(Car car) {
        carDAO.update(car);
    }

    @Override
    @Transactional
    public Car read(int id) {
        return carDAO.read(id);
    }

    @Override
    @Transactional
    public void delete(Car car) {
        carDAO.delete(car);
    }

    @Override
    @Transactional
    public List<Car> getAll() {
        return carDAO.getAll();
    }
}
