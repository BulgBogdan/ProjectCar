package projectCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import projectCar.dao.interfaces.ICurrencyDAO;
import projectCar.entity.Currency;
import projectCar.service.interfaces.ICurrencyService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class CurrencyServiceImplTest {

    @Autowired
    private ICurrencyService currencyService;

    @MockBean
    private ICurrencyDAO currencyDAO;

    private Currency useCurrency() {
        Currency currency = new Currency();
        currency.setTitle("EUR");
        return currency;
    }

    @Test
    void read() {
        Currency currency = useCurrency();
        Mockito.when(currencyDAO.read(1)).thenReturn(currency);
        Currency currencyFounded = currencyService.read(1);
        assertEquals("EUR", currencyFounded.getTitle());
    }
}