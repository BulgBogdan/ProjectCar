package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Document;
import projectCar.service.DocumentServiceImpl;
import projectCar.service.interfaces.IDocumentService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DocumentController extends MethodsCarForControllers {

    @Autowired
    private IDocumentService documentService = new DocumentServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    private int page;

    @GetMapping("/car/documents/{id}")
    public ModelAndView pageDocuments(@PathVariable("id") int id,
                                      @RequestParam(defaultValue = "1") int page) {
        car = getCarWithWires(id);
        List<Document> documentList = documentService.getDocuments(page, id);
        int documentsCount = documentService.docsCount(id);
        int pagesCount = (documentsCount + 9) / 10;
        modelAndView.setViewName("car/documents");
        modelAndView.addObject("car", car);
        modelAndView.addObject("page", page);
        modelAndView.addObject("documentsCount", documentsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("documents", documentList);
        this.page = page;
        double documentsCosts = 0;
        for (Document document : car.getDocuments()) {
            documentsCosts = documentsCosts + document.getDocumentCost();
        }
        modelAndView.addObject("allDocumentsCosts", documentsCosts);
        return modelAndView;
    }

    @GetMapping("/car/documents/create/{id}")
    public ModelAndView pageAddDocuments(@PathVariable("id") int id) {
        car = getCarById(id);
        modelAndView.setViewName("car/documents/create");
        modelAndView.addObject("doc", new Document());
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @PostMapping("/car/documents/create/{id}")
    public ModelAndView addDocument(@PathVariable("id") int id,
                                    @ModelAttribute("doc") Document document) {
        int numberOfMonths;
        car = getCarById(id);

        if (document.getEndDate() == null) {
            LocalDate endDate = document.getBeginDate().toLocalDate().plusMonths(document.getNumberOfMonth());
            document.setEndDate(Date.valueOf(endDate));
        }

        if (document.getNumberOfMonth() == 0) {
            numberOfMonths = amountOfMonths(document.getBeginDate(), document.getEndDate());
            document.setNumberOfMonth(numberOfMonths);
        }

        int numberOfDays = amountOfDays(document.getBeginDate(), document.getEndDate());
        document.setNumberOfDays(numberOfDays);
        document.setCar(car);
        modelAndView.setViewName("redirect:/car/documents/{id}");
        documentService.add(document);
        return modelAndView;
    }

    @GetMapping("car/documents/edit/{id}")
    public ModelAndView editDocument(@PathVariable("id") int id) {
        Document document = documentService.read(id);
        modelAndView.setViewName("car/documents/edit");
        modelAndView.addObject("docs", document);
        modelAndView.addObject("car", document.getCar());
        return modelAndView;
    }

    @PostMapping("car/documents/edit/{id}")
    public ModelAndView editDocument(@ModelAttribute("docs") Document documentEdit,
                                     BindingResult result,
                                     @PathVariable("id") int id) {
        if (result.hasErrors()) {
            errorIncorrectEnter();
            return modelAndView;
        }

        Document document = documentService.read(id);
        int carId = document.getCar().getId();
        LocalDate endDate;
        boolean dateEditEqualDate = document.getEndDate().getTime() == documentEdit.getEndDate().getTime();
        boolean monthsEditEqualMonths = document.getNumberOfMonth() == documentEdit.getNumberOfMonth();
        int numberOfMonth;

        if (!dateEditEqualDate) {
            numberOfMonth = amountOfMonths(documentEdit.getBeginDate(), documentEdit.getEndDate());
            documentEdit.setNumberOfMonth(numberOfMonth);
        }

        if ((!monthsEditEqualMonths) && (dateEditEqualDate)) {
            endDate = documentEdit.getBeginDate().toLocalDate().plusMonths(documentEdit.getNumberOfMonth());
            documentEdit.setEndDate(Date.valueOf(endDate));
        }

        int numberOfDays = amountOfDays(documentEdit.getBeginDate(), documentEdit.getEndDate());
        documentEdit.setNumberOfDays(numberOfDays);
        documentEdit.setCar(getCarById(carId));
        modelAndView.addObject("carId", carId);
        modelAndView.setViewName("redirect:/car/documents/{carId}");
        documentService.update(documentEdit);
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
