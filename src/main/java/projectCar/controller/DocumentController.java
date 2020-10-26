package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        car = getCarById(id);
        document.setNumberOf(
                amountOfDays(document.getBeginDate(), document.getEndDate()));
        document.setCar(car);
        modelAndView.setViewName("redirect:/car/documents/{id}");
        documentService.add(document);
        return modelAndView;
    }

    @GetMapping("car/documents/edit/{id}")
    public ModelAndView editParameter(@PathVariable("id") int id) {
        Document document = documentService.read(id);
        modelAndView.setViewName("car/documents/edit");
        modelAndView.addObject("docs", document);
        modelAndView.addObject("car", document.getCar());
        return modelAndView;
    }

    @PostMapping("car/documents/edit/{id}")
    public ModelAndView editParameter(@ModelAttribute("docs") Document document,
                                      @ModelAttribute("car") Car car,
                                      @PathVariable("id") int id) {
        document.setCar(
                getCarById(car.getId()));
        document.setNumberOf(
                amountOfDays(document.getBeginDate(), document.getEndDate()));
        modelAndView.setViewName("redirect:/car/documents/{id}");
        documentService.update(document);
        return modelAndView;
    }

}