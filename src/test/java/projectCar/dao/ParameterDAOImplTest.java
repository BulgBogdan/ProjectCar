package projectCar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.ICarDAO;
import projectCar.dao.interfaces.IParameterDAO;
import projectCar.entity.Car;
import projectCar.entity.Parameter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ParameterDAOImplTest {

    @Autowired
    IParameterDAO parameterDAO;

    @Autowired
    ICarDAO carDAO;

    @Test
    void add() {
        Car car = carDAO.read(1);
        Parameter createParameter = new Parameter();
        createParameter.setAverageRate(1);
        createParameter.setColor("black");
        createParameter.setFirstMileage(1);
        createParameter.setMark("mark");
        createParameter.setMass(1);
        createParameter.setModel("model");
        createParameter.setRegistrationNumber("1");
        createParameter.setVin("1");
        createParameter.setYear(2000);
        createParameter.setCar(car);
        parameterDAO.add(createParameter);
        Parameter createdParameter = parameterDAO.read(createParameter.getId());
        assertNotNull(createdParameter);
    }

    @Test
    void update() {
        Parameter updateParameter = parameterDAO.read(1);
        updateParameter.setModel("MODEL");
        parameterDAO.update(updateParameter);
        String testEqualModel = parameterDAO.read(1).getModel();
        assertEquals("MODEL", testEqualModel);
    }

    @Test
    void read() {
        Parameter parameter = parameterDAO.read(1);
        assertNotNull(parameter);
    }

    @Test
    void delete() {
        Parameter createParameter = parameterDAO.read(1);
        parameterDAO.delete(createParameter);
        Parameter deletedParameter = parameterDAO.read(1);
        assertNull(deletedParameter);
    }

    @Test
    void getAll() {
        if (!parameterDAO.getAll().isEmpty()) {
            List<Parameter> parameterList = parameterDAO.getAll();
            assertNotNull(parameterList);
        }
    }
}