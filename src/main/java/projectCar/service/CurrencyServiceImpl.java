package projectCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectCar.dao.CurrencyDAOImpl;
import projectCar.dao.interfaces.ICurrencyDAO;
import projectCar.entity.Currency;
import projectCar.service.interfaces.ICurrencyService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CurrencyServiceImpl implements ICurrencyService {

    @Autowired
    private ICurrencyDAO currencyDAO = new CurrencyDAOImpl();

    @Override
    @Transactional
    public Currency read(int id) {
        return currencyDAO.read(id);
    }

    @Override
    @Transactional
    public void update(Currency currency) {
        currencyDAO.update(currency);
    }

    @Override
    @Transactional
    public List<Currency> getAll() {
        return currencyDAO.getAll();
    }
}
