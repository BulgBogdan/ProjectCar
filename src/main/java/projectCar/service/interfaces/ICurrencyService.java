package projectCar.service.interfaces;

import projectCar.entity.Currency;

import java.util.List;

public interface ICurrencyService {

    Currency read(int id);

    void update(Currency currency);

    List<Currency> getAll();

}
