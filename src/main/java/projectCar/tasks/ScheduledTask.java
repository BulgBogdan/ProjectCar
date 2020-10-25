package projectCar.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import projectCar.entity.Document;
import projectCar.service.DocumentServiceImpl;
import projectCar.service.interfaces.IDocumentService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {

    @Autowired
    private IDocumentService documentService = new DocumentServiceImpl();

    private int countDays (Date start, Date end){
        long count = Math.abs(end.getTime() - start.getTime());
        int days = (int) TimeUnit.DAYS.convert(count, TimeUnit.MILLISECONDS);
        return days;
    }

    @Scheduled(cron = "1 0 0 * * ?")
    private void correctAmountOFDays (){
        List<Document> listDocuments = documentService.getAll();
        for (Document document : listDocuments) {
            int value = countDays(Date.valueOf(LocalDate.now()), document.getEndDate());
            if (value >= 0) {
                document.setNumberOf(value);
                documentService.update(document);
            }
        }
    }

}
