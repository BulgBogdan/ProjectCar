package projectCar.task;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import projectCar.entity.Currency;
import projectCar.entity.Document;
import projectCar.entity.Repair;
import projectCar.service.CurrencyServiceImpl;
import projectCar.service.DocumentServiceImpl;
import projectCar.service.RepairServiceImpl;
import projectCar.service.interfaces.ICurrencyService;
import projectCar.service.interfaces.IDocumentService;
import projectCar.service.interfaces.IRepairService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {

    @Autowired
    private IDocumentService documentService = new DocumentServiceImpl();

    @Autowired
    private IRepairService repairService = new RepairServiceImpl();

    @Autowired
    private ICurrencyService currencyService = new CurrencyServiceImpl();

    private int countDays (Date start, Date end){
        long daysBetween = Math.abs(end.getTime() - start.getTime());
        return (int) TimeUnit.DAYS.convert(daysBetween, TimeUnit.MILLISECONDS);
    }

    private int countMonths (Date start, Date end){
        return (int) ChronoUnit.MONTHS.between(start.toLocalDate(), end.toLocalDate());
    }

    private int mileageEnd(int mileageStart, int mileageService){
        return mileageStart + mileageService;
    }

    private String getTodayDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date todayDate = new java.util.Date();
        return dateFormat.format(todayDate);
    }

    private double getTodayCurrencyUSD(String todayDate) {
        double valueCurrency = 0;
        try {
            String urlFromBank = "https://www.nbrb.by/api/exrates/rates/145?ondate=" + todayDate;
            URL url = new URL(urlFromBank);
            URLConnection connection = url.openConnection();
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine = input.readLine();

            JSONObject obj = (JSONObject) JSONValue.parse(inputLine);
            valueCurrency = (Double) obj.get("Cur_OfficialRate");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return valueCurrency;
    }

    @Scheduled(cron = "0 * * ? * *")    // every minute
    private void correctAmountOFDays (){
        List<Document> listDocuments = documentService.getAll();
        for (Document document : listDocuments) {
            int days = countDays(Date.valueOf(LocalDate.now()), document.getEndDate());
            int months = countMonths(Date.valueOf(LocalDate.now()),document.getEndDate());
            if ((days >= 0) && (months >=0)) {
                document.setNumberOfDays(days);
                document.setNumberOfMonth(months);
                documentService.update(document);
            }
        }
    }

    @Scheduled(cron = "0 * * ? * *")    // every minute
    private void correctGarantMileageRepair(){
        List<Repair> listRepair = repairService.getAll();
        for (Repair repair : listRepair) {
            int mileageCarNow = repair.getCar().getMileage();
            int mileageGarantLast = mileageEnd(repair.getBeginMileage(),repair.getServiceLife()) - mileageCarNow;
            if (mileageGarantLast >= 0){
                repair.setEndMileage(mileageGarantLast);
                repairService.update(repair);
            }
        }
    }

    @Scheduled(cron = "0 * * ? * *")    // every minute
    private void correctTodayCurrencyUSD() {
        Currency currency = currencyService.read(2);
        double currencyTodayUSD = getTodayCurrencyUSD(getTodayDate());
        currency.setCurrencyValue(currencyTodayUSD);
        currencyService.update(currency);
    }

}

// for server 0 0 0 * * ? every midnight
// for test 0 * * ? * * every minute
// for test * * * ? * * every second