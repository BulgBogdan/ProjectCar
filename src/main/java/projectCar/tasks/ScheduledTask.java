package projectCar.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import projectCar.entity.Document;
import projectCar.entity.Repair;
import projectCar.service.DocumentServiceImpl;
import projectCar.service.RepairServiceImpl;
import projectCar.service.interfaces.IDocumentService;
import projectCar.service.interfaces.IRepairService;

import java.sql.Date;
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

    private int countDays (Date start, Date end){
        long daysBetween = Math.abs(end.getTime() - start.getTime());
        int days = (int) TimeUnit.DAYS.convert(daysBetween, TimeUnit.MILLISECONDS);
        return days;
    }

    private int countMonths (Date start, Date end){
        int months = (int) ChronoUnit.MONTHS.between(start.toLocalDate(),end.toLocalDate());
        return months;
    }

    private int mileageEnd(int mileageStart, int mileageService){
        int mileageEnd = mileageStart + mileageService;
        return mileageEnd;
    }

    @Scheduled(cron = "0/5 * * ? * *")    //every 5 seconds
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

    @Scheduled(cron = "0/5 * * ? * *") //every 5 seconds
    private void correctGaranteMileageRepair(){
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

}

//for server 0 0 0 * * ? every midnight
