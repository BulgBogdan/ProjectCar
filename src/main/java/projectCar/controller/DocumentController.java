package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Currency;
import projectCar.entity.Document;
import projectCar.methods.Calculations;
import projectCar.methods.ServiceSolution;
import projectCar.service.interfaces.IDocumentService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class DocumentController {

    private ServiceSolution solutions;

    private IDocumentService documentService;

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    private int page;

    @Autowired
    public DocumentController(ServiceSolution solutions, IDocumentService documentService) {
        this.solutions = solutions;
        this.documentService = documentService;
    }

    @GetMapping("/car/documents/{id}")
    public ModelAndView pageDocuments(@PathVariable("id") int id,
                                      @RequestParam(defaultValue = "1") int page) {
        car = solutions.getCarWithWires(id);
        List<Document> documentList = documentService.getDocuments(page, id);
        int documentsCount = documentService.docsCount(id);

        List<Document> greenDate = new ArrayList<>();
        List<Document> yellowDate = new ArrayList<>();
        List<Document> redDate = new ArrayList<>();
        List<Document> endDate = new ArrayList<>();

        for (Document doc : documentList) {
            int validityPeriodOfDays = Calculations.amountOfDays(doc.getBeginDate(), doc.getEndDate());

            if (doc.getNumberOfDays() > (validityPeriodOfDays / 2)) {
                greenDate.add(doc);
            }
            if ((doc.getNumberOfDays() <= (validityPeriodOfDays / 2))
                    && (doc.getNumberOfDays() > (validityPeriodOfDays / 4))) {
                yellowDate.add(doc);
            }
            if ((doc.getNumberOfDays() <= (validityPeriodOfDays / 4))
                    &&
                    (doc.getNumberOfDays() > 0)) {
                redDate.add(doc);
            } else if (doc.getNumberOfDays() <= 0) {
                endDate.add(doc);
            }
        }

        modelAndView.addObject("greenDate", greenDate);
        modelAndView.addObject("yellowDate", yellowDate);
        modelAndView.addObject("redDate", redDate);
        modelAndView.addObject("endDate", endDate);

        int pagesCount = (documentsCount + 9) / 10;
        double valueUSD = solutions.getCurrencyValueUSD();
        modelAndView.setViewName("car/documents");
        modelAndView.addObject("car", car);
        modelAndView.addObject("page", page);
        modelAndView.addObject("documentsCount", documentsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("documents", documentList);
        modelAndView.addObject("valueUSD", valueUSD);
        this.page = page;
        double documentsCosts = 0;
        for (Document document : car.getDocuments()) {
            documentsCosts = documentsCosts + document.getDocumentCost();
        }
        //currency = BYN
        if (car.getUser().getCurrency().getTitle().equals("BYN")) {
            modelAndView.addObject("allDocumentsCosts", documentsCosts);
        }
        //currency = USD
        else {
            double docValueByUSD = documentsCosts / valueUSD;
            modelAndView.addObject("allDocumentsCosts", docValueByUSD);
        }

        return modelAndView;
    }

    @GetMapping("/car/documents/create/{id}")
    public ModelAndView pageAddDocuments(@PathVariable("id") int id) {
        car = solutions.getCarById(id);
        Currency currency = solutions.getCurrencyFromCarById(id);
        modelAndView.setViewName("car/documents/create");
        modelAndView.addObject("doc", new Document());
        modelAndView.addObject("car", car);
        modelAndView.addObject("currency", currency);
        return modelAndView;
    }

    @PostMapping("/car/documents/create/{id}")
    public ModelAndView addDocument(@ModelAttribute("doc") Document document,
                                    BindingResult result,
                                    @PathVariable("id") int id) {
        car = solutions.getCarById(id);
        Currency currency = solutions.getCurrencyFromCarById(id);
        if (Objects.isNull(document.getEndDate())) {
            LocalDate endDate = document.getBeginDate().toLocalDate().plusMonths(document.getNumberOfMonth());
            document.setEndDate(Date.valueOf(endDate));
        }

        if (document.getNumberOfMonth() == 0) {
            int numberOfMonths = Calculations.amountOfMonths(document.getBeginDate(), document.getEndDate());
            document.setNumberOfMonth(numberOfMonths);
        }

        int numberOfDays = Calculations.amountOfDays(document.getBeginDate(), document.getEndDate());
        document.setNumberOfDays(numberOfDays);
        document.setCar(car);
        modelAndView.setViewName("redirect:/car/documents/{id}");

        if (currency.getTitle().equals("USD")) {
            double priceDocByBYN = solutions.getValueByUSD(document.getDocumentCost());
            document.setDocumentCost(priceDocByBYN);
            documentService.add(document);
        } else {
            documentService.add(document);
        }

        return modelAndView;
    }

    @GetMapping("car/documents/edit/{id}")
    public ModelAndView editDocument(@PathVariable("id") int id) {
        Document document = documentService.read(id);
        modelAndView.setViewName("car/documents/edit");
        double valueUSD = solutions.getCurrencyValueUSD();
        if (document.getCar().getUser().getCurrency().getTitle().equals("USD")) {
            document.setDocumentCost(document.getDocumentCost() / valueUSD);
            modelAndView.addObject("docs", document);
        } else {
            modelAndView.addObject("docs", document);
        }
        modelAndView.addObject("car", document.getCar());
        return modelAndView;
    }

    @PostMapping("car/documents/edit/{id}")
    public ModelAndView editDocument(@ModelAttribute("docs") Document documentEdit,
                                     BindingResult result,
                                     @PathVariable("id") int id) {
        if (result.hasErrors()) {
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            return modelAndView;
        }

        Document document = documentService.read(id);
        int carId = document.getCar().getId();
        Currency currency = solutions.getCurrencyFromCarById(carId);

        boolean dateEditEqualDate = (document.getEndDate().getTime() == documentEdit.getEndDate().getTime());
        boolean monthsEditEqualMonths = (document.getNumberOfMonth() == documentEdit.getNumberOfMonth());

        int numberOfMonth;
        if (!dateEditEqualDate) {
            numberOfMonth = Calculations.amountOfMonths(documentEdit.getBeginDate(), documentEdit.getEndDate());
            documentEdit.setNumberOfMonth(numberOfMonth);
        }

        LocalDate endDate;
        if ((!monthsEditEqualMonths) && (dateEditEqualDate)) {
            endDate = documentEdit.getBeginDate().toLocalDate().plusMonths(documentEdit.getNumberOfMonth());
            documentEdit.setEndDate(Date.valueOf(endDate));
        }

        int numberOfDays = Calculations.amountOfDays(documentEdit.getBeginDate(), documentEdit.getEndDate());
        documentEdit.setNumberOfDays(numberOfDays);
        documentEdit.setCar(solutions.getCarById(carId));
        modelAndView.addObject("carId", carId);
        modelAndView.setViewName("redirect:/car/documents/{carId}");

        if (currency.getTitle().equals("BYN")) {
            documentService.update(documentEdit);
        } else {
            double priceDocByBYN = solutions.getValueByUSD(documentEdit.getDocumentCost());
            documentEdit.setDocumentCost(priceDocByBYN);
            documentService.update(documentEdit);
        }

        return modelAndView;
    }

    @GetMapping("/car/documents/delete/{id}")
    public ModelAndView deleteDocument(@PathVariable("id") int id) {
        Document document = documentService.read(id);
        modelAndView.addObject("carId", document.getCar().getId());
        modelAndView.setViewName("redirect:/car/documents/{carId}");
        documentService.delete(document);
        return modelAndView;
    }
}