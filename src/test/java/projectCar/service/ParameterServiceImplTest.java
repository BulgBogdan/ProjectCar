package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.CarDAOImpl;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.dao.interfaces.IParameterDAO;
import projectCar.entity.Car;
import projectCar.entity.Parameter;
import projectCar.service.interfaces.IParameterService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ParameterServiceImplTest {

    @Autowired
    private IParameterService parameterService;

    @MockBean
    private IParameterDAO parameterDAO;

    @Autowired
    private ICarDAO carDAO = new CarDAOImpl();

    private Parameter useParameter() {
        Parameter parameter = new Parameter();
        Car car = carDAO.read(1);
        parameter.setAverageRate(1);
        parameter.setColor("black");
        parameter.setFirstMileage(1);
        parameter.setMark("mark");
        parameter.setMass(1);
        parameter.setModel("model");
        parameter.setRegistrationNumber("1");
        parameter.setVin("1");
        parameter.setYear(2000);
        parameter.setCar(car);
        return parameter;
    }

    @Test
    void add() {
        Parameter parameter = useParameter();
        parameterService.add(parameter);
        Mockito.verify(parameterDAO, Mockito.times(1)).add(parameter);
    }

    @Test
    void update() {
        Parameter parameter = useParameter();
        parameter.setModel("modelParameter");
        parameterService.update(parameter);
        Mockito.verify(parameterDAO, Mockito.times(1)).update(parameter);
    }

    @Test
    void read() {
        Parameter parameter = useParameter();
        Mockito.when(parameterDAO.read(1)).thenReturn(parameter);
        Parameter foundParameter = parameterService.read(1);
        assertEquals("model", foundParameter.getModel());
        assertEquals(2000, foundParameter.getYear());
        assertEquals("black", foundParameter.getColor());
    }

    @Test
    void delete() {
        Parameter parameter = useParameter();
        parameterService.delete(parameter);
        Mockito.verify(parameterDAO, Mockito.times(1)).delete(parameter);
    }

    @Test
    void getAll() {
        List<Parameter> parameterList = new ArrayList<>();
        Mockito.when(parameterDAO.getAll()).thenReturn(parameterList);
        List<Parameter> parameters = parameterService.getAll();
        assertEquals(parameters, parameterList);
    }
}