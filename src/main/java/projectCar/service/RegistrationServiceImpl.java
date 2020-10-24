package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.RegistrationDAOImpl;
import projectCar.dao.interfaces.IRegistrationDAO;
import projectCar.entity.Registration;
import projectCar.service.interfaces.IRegistrationService;

import java.util.List;

@Service
public class RegistrationServiceImpl implements IRegistrationService {

    @Autowired
    private IRegistrationDAO registrationDAO = new RegistrationDAOImpl();

    @Override
    @Transactional
    public void add(Registration registration) {
        registrationDAO.add(registration);
    }

    @Override
    @Transactional
    public void update(Registration registration) {
        registrationDAO.update(registration);
    }

    @Override
    @Transactional
    public Registration read(int id) {
        return registrationDAO.read(id);
    }

    @Override
    @Transactional
    public void delete(Registration registration) {
        registrationDAO.delete(registration);
    }

    @Override
    @Transactional
    public List<Registration> getAll() {
        return registrationDAO.getAll();
    }
}
