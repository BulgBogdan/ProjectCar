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

    private Parameter parameterTest;

    @Test
    void add() {
        parameterTest = useParameter();
        parameterService.add(parameterTest);
        Mockito.verify(parameterDAO, Mockito.times(1)).add(parameterTest);
    }

    @Test
    void update() {
        parameterTest = useParameter();
        parameterTest.setModel("modelParameter");
        parameterService.update(parameterTest);
        Mockito.verify(parameterDAO, Mockito.times(1)).update(parameterTest);
    }

    @Test
    void read() {
        parameterTest = useParameter();
        Mockito.when(parameterDAO.read(1)).thenReturn(parameterTest);
        Parameter foundParameter = parameterService.read(1);
        assertEquals("model", foundParameter.getModel());
        assertEquals(2000, foundParameter.getYear());
        assertEquals("black", foundParameter.getColor());
    }

    @Test
    void delete() {
        parameterTest = useParameter();
        parameterService.delete(parameterTest);
        Mockito.verify(parameterDAO, Mockito.times(1)).delete(parameterTest);
    }

    @Test
    void getAll() {
        List<Parameter> parameterList = new ArrayList<>();
        Mockito.when(parameterDAO.getAll()).thenReturn(parameterList);
        List<Parameter> parameters = parameterService.getAll();
        assertEquals(parameters, parameterList);
    }
}