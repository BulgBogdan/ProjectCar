package projectCar.service.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.RegistrationDAOImpl;
import projectCar.dao.interfaces.IRegistrationDAO;
import projectCar.entity.Registration;

import java.util.List;

@Service
public class RegistrationServiceImpl implements IRegistartionService {

    @Autowired
    IRegistrationDAO registrationDAO = new RegistrationDAOImpl();

    @Override
    @Transactional
    public void add(Registration registration) {

    }

    @Override
    @Transactional
    public void update(Registration registration) {

    }

    @Override
    @Transactional
    public Registration read(int id) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Registration registration) {

    }

    @Override
    @Transactional
    public List<Registration> getAll() {
        return null;
    }
}
