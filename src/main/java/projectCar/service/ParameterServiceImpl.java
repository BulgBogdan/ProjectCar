package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.IParameterDAO;
import projectCar.entity.Parameter;
import projectCar.service.interfaces.IParameterService;

import java.util.List;

@Service
public class ParameterServiceImpl implements IParameterService {

    private IParameterDAO parameterDAO;

    @Autowired
    public ParameterServiceImpl(IParameterDAO parameterDAO) {
        this.parameterDAO = parameterDAO;
    }

    @Override
    @Transactional
    public void add(Parameter parameter) {
        parameterDAO.add(parameter);
    }

    @Override
    @Transactional
    public void update(Parameter parameter) {
        parameterDAO.update(parameter);
    }

    @Override
    @Transactional
    public Parameter read(int id) {
        return parameterDAO.read(id);
    }

    @Override
    @Transactional
    public void delete(Parameter parameter) {
        parameterDAO.delete(parameter);
    }

    @Override
    @Transactional
    public List<Parameter> getAll() {
        return parameterDAO.getAll();
    }
}
