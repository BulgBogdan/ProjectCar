package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.IRepairDAO;
import projectCar.dao.RepairDAOImpl;
import projectCar.entity.Repair;
import projectCar.service.interfaces.IRepairService;

import java.util.List;

@Service
public class RepairServiceImpl implements IRepairService {

    @Autowired
    private IRepairDAO repairDAO = new RepairDAOImpl();

    @Override
    @Transactional
    public void add(Repair repair) {
        repairDAO.add(repair);
    }

    @Override
    @Transactional
    public void update(Repair repair) {
        repairDAO.update(repair);
    }

    @Override
    @Transactional
    public Repair read(int id) {
        return repairDAO.read(id);
    }

    @Override
    @Transactional
    public void delete(Repair repair) {
        repairDAO.delete(repair);
    }

    @Override
    @Transactional
    public int repairCount() {
        return repairDAO.repairCount();
    }

    @Override
    @Transactional
    public List<Repair> getRepair(int page, int id) {
        return repairDAO.getRepair(page, id);
    }

    @Override
    @Transactional
    public List<Repair> getAll() {
        return repairDAO.getAll();
    }
}
