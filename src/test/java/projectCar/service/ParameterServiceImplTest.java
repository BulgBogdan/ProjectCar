package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
    IParameterService parameterService;

    @MockBean
    IParameterDAO parameterDAO;

    @Autowired
    ICarDAO carDAO = new CarDAOImpl();

    @InjectMocks
    Parameter parameter = new Parameter();

    @Test
    void add() {

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
        Mockito.verify(parameterDAO, Mockito.times(1)).add(parameter);
//        Mockito.when(parameterService.add(parameter)).thenAnswer()
    }

    @Test
    void update() {
    }

    @Test
    void read() {

    }

    @Test
    void add1() {
    }

    @Test
    void update1() {
    }

    @Test
    void read1() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
        List<Parameter> parameterList = new ArrayList<>();
        Mockito.when(parameterDAO.getAll()).thenReturn(parameterList);
        List<Parameter> parameters = parameterService.getAll();
        assertEquals(parameters, parameterList);
    }
}