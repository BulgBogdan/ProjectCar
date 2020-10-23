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
import projectCar.service.CarServiceImpl;
import projectCar.service.DocumentServiceImpl;
import projectCar.service.interfaces.ICarService;
import projectCar.service.interfaces.IDocumentService;

import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    private ICarService carService = new CarServiceImpl();

    @Autowired
    private IDocumentService documentService = new DocumentServiceImpl();

    private ModelAndView modelAndView;

    @GetMapping("/car/documents/{id}")
    public ModelAndView pageDocuments(@PathVariable("id") int id){
        Car car = carService.read(id);
        modelAndView.setViewName("car/documents");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameters", car.getParameters());
        modelAndView.addObject("documents", car.getDocuments());
        return modelAndView;
    }

    @GetMapping("/car/documents/create/{id}")
    public ModelAndView pageAddDocuments(@PathVariable("id") int id){
        modelAndView.setViewName("car/documents/create");
        modelAndView.addObject("documents",new Document());
        return modelAndView;
    }

    @PostMapping("/car/documents/{id}")
    public ModelAndView addDocuments(@PathVariable("id") int id, @ModelAttribute("documents") Document document){
        Car car = carService.read(id);
        document.setCar(car);
        documentService.add(document);
        modelAndView.setViewName("redirect:/car/documents");
        return modelAndView;
    }
}
