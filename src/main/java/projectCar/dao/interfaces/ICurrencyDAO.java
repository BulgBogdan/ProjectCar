package projectCar.dao.interfaces;

import projectCar.entity.Currency;

import java.util.List;

public interface ICurrencyDAO {

    Currency read(int id);

    void update(Currency currency);

    List<Currency> getAll();
}
