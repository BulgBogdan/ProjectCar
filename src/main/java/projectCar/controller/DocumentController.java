package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.Car;
import projectCar.entity.Document;
import projectCar.service.DocumentServiceImpl;
import projectCar.service.interfaces.IDocumentService;

@Controller
public class DocumentController extends MethodsCarForControllers {

    @Autowired
    private IDocumentService documentService = new DocumentServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Car car = new Car();

    @GetMapping("/car/documents/{id}")
    public ModelAndView pageDocuments(@PathVariable("id") int id) {
        car = getCarWithWires(id);
        modelAndView.setViewName("car/documents");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("documents", car.getDocuments());
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
                                    @ModelAttribute("doc") Document document,
                                    BindingResult result) {
        if (result.hasErrors()){
            errorIncorrectEnter();
        }
        car = getCarById(id);
        int numberOfDays = amountOfDays(document.getBeginDate(), document.getEndDate());
        int numberOfMonths = amountOfMonths(document.getBeginDate(),document.getEndDate());

        document.setNumberOfDays(numberOfDays);
        document.setNumberOfMonth(numberOfMonths);
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
    public ModelAndView editDocument(@ModelAttribute("docs") Document document,
                                      @ModelAttribute("car") Car car,
                                      @PathVariable("id") int id,
                                     BindingResult result) {
        if (result.hasErrors()){
            errorIncorrectEnter();
        }
        int carId = car.getId();
        int numberOfDays = amountOfDays(document.getBeginDate(), document.getEndDate());

        document.setCar(getCarById(carId));
        document.setNumberOfDays(numberOfDays);
        modelAndView.setViewName("redirect:/car/documents/{id}");
        documentService.update(document);
        return modelAndView;
    }

    @GetMapping("/car/documents/delete/{id}")
    public ModelAndView deleteDocument(@PathVariable("id") int id){
        Document document = documentService.read(id);
        modelAndView.addObject("carId", document.getCar().getId());
        modelAndView.setViewName("redirect:/car/documents/{carId}");
        documentService.delete(document);
        return modelAndView;
    }

}
