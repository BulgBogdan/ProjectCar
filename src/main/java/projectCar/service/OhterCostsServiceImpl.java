package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.IOtherCostsDAO;
import projectCar.dao.OtherCostsDAOImpl;
import projectCar.entity.OtherCosts;
import projectCar.service.interfaces.IOtherCostsService;

import java.util.List;

@Service
public class OhterCostsServiceImpl implements IOtherCostsService {

    @Autowired
    IOtherCostsDAO otherCostsDAO = new OtherCostsDAOImpl();

    @Override
    @Transactional
    public void add(OtherCosts otherCosts) {
        otherCostsDAO.add(otherCosts);
    }

    @Override
    @Transactional
    public void update(OtherCosts otherCosts) {
        otherCostsDAO.update(otherCosts);
    }

    @Override
    @Transactional
    public OtherCosts read(int id) {
        return otherCostsDAO.read(id);
    }

    @Override
    @Transactional
    public void delete(OtherCosts otherCosts) {
        otherCostsDAO.delete(otherCosts);
    }

    @Override
    @Transactional
    public List<OtherCosts> getAll() {
        return otherCostsDAO.getAll();
    }
}
