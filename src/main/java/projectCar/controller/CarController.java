package projectCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import projectCar.entity.*;
import projectCar.service.CarServiceImpl;
import projectCar.service.CurrencyServiceImpl;
import projectCar.service.RegistrationServiceImpl;
import projectCar.service.UserServiceImpl;
import projectCar.service.interfaces.ICarService;
import projectCar.service.interfaces.ICurrencyService;
import projectCar.service.interfaces.IRegistrationService;
import projectCar.service.interfaces.IUserService;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private ICurrencyService currencyService = new CurrencyServiceImpl();

    @Autowired
    private IUserService userService = new UserServiceImpl();

    @Autowired
    private ICarService carService = new CarServiceImpl();

    @Autowired
    private IRegistrationService registrationService = new RegistrationServiceImpl();

    private ModelAndView modelAndView = new ModelAndView();

    private Currency getCurrencyById(int id) {
        Currency currencyById = currencyService.read(id);
        return currencyById;
    }

    private User getUserByLogin(String login) {
        User userById = userService.findByLogin(login);
        return userById;
    }

    private Car getCarById(int id) {
        Car carById = carService.read(id);
        return carById;
    }

    private ModelAndView errorIncorrectEnter() {
        modelAndView.addObject("Errors", "Incorrect enter");
        return modelAndView;
    }

    @GetMapping("car/title")
    public ModelAndView createCar() {
        modelAndView.setViewName("car/title");
        modelAndView.addObject("newCar", new Car());
        return modelAndView;
    }

    @PostMapping("car/title")
    public ModelAndView addCar(@ModelAttribute("newCar") Car car,
                               BindingResult result,
                               @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            modelAndView.addObject("Errors", "Incorrect enter");
            return modelAndView;
        }
        String userName = userDetails.getUsername();
        User user = getUserByLogin(userName);
        car.setUser(user);
        modelAndView.setViewName("redirect:/");
        carService.add(car);
        return modelAndView;
    }

    @GetMapping("car/costs/first/{id}")
    public ModelAndView createFirstCost(@PathVariable("id") int id) {
        int currencyId = getCarById(id).getUser().getCurrency().getId();
        Currency currency = getCurrencyById(currencyId);
        modelAndView.setViewName("car/costs/first");
        modelAndView.addObject("registration", new Registration());
        modelAndView.addObject("currency", currency);
        return modelAndView;
    }

    @PostMapping("car/costs/first/{id}")
    public ModelAndView addFirstCost(@PathVariable("id") int id,
                                     @ModelAttribute("registration") Registration registration,
                                     BindingResult result) {
        if (result.hasErrors()) {
            errorIncorrectEnter();
            return modelAndView;
        }
        Car car = getCarById(id);
        Currency currency = car.getUser().getCurrency();
        registration.setCar(car);
        if (currency.getTitle().equals("USD")) {
            double priceCarByBYN = registration.getPriceCar() * 2.6;
            registration.setPriceCar((int) priceCarByBYN);
            double priceRegistrationByBYN = registration.getPriceRegistration() * 2.6;
            registration.setPriceRegistration(priceRegistrationByBYN);
            registrationService.add(registration);
        } else {
            registrationService.add(registration);
        }
        modelAndView.setViewName("redirect:/car/view/{id}");
        return modelAndView;
    }

    @GetMapping("car/view/{id}")
    public ModelAndView viewCar(@PathVariable("id") int id) {
        Car car = getCarById(id);
        modelAndView.setViewName("car/view");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameter", car.getParameters());
        Currency currency = car.getUser().getCurrency();
        if (currency.getTitle().equals("BYN")) {
            modelAndView.addObject("registration", car.getRegistration());
        } else {
            int priceCar = (int) (car.getRegistration().getPriceCar() / 2.6);
            double priceRegistration = car.getRegistration().getPriceRegistration() / 2.6;
            car.getRegistration().setPriceRegistration(priceRegistration);
            car.getRegistration().setPriceCar(priceCar);
            modelAndView.addObject("registration", car.getRegistration());
        }
        if ((car.getRegistration() != null) && (car.getParameters() != null)) {
            double costs = 0;
            List<Car> listCar = carService.getLists(id);
            for (Car cars : listCar) {
                for (Fuel fuel : cars.getFuels()) {
                    costs = costs + fuel.getSumm();
                }
                for (OtherCosts otherCost : cars.getOtherCosts()) {
                    costs = costs + otherCost.getCost();
                }
                for (Repair repair : cars.getRepairs()) {
                    costs = costs + repair.getCostsRepair();
                }
                for (Document document : cars.getDocuments()) {
                    costs = costs + document.getDocumentCost();
                }
            }
            double firstCosts = car.getRegistration().getPriceCar() + car.getRegistration().getPriceRegistration();
            costs = costs + firstCosts;
            if (currency.getTitle().equals("BYN")) {
                modelAndView.addObject("allCosts", costs);
            } else {
                double valueByUSD = costs / 2.6;
                String costsByUSD = String.format("%.2f", valueByUSD);
                modelAndView.addObject("allCosts", costsByUSD);
            }
        }
        return modelAndView;
    }

    @GetMapping("car/costs/edit/{id}")
    public ModelAndView editPageFirstCost(@PathVariable("id") int id) {
        Car car = getCarById(id);
        modelAndView.setViewName("car/costs/edit");
        modelAndView.addObject("registration", car.getRegistration());
        return modelAndView;
    }

    @PostMapping("car/costs/edit/{id}")
    public ModelAndView editFirstCost(@ModelAttribute("registration") Registration registration,
                                      BindingResult result,
                                      @PathVariable("id") int id) {
        if (result.hasErrors()) {
            errorIncorrectEnter();
            return modelAndView;
        }

        Car car = getCarById(id);
        Currency currency = car.getUser().getCurrency();
        registration.setCar(car);
        if (currency.getTitle().equals("USD")) {
            double priceCarByBYN = registration.getPriceCar() * 2.6;
            registration.setPriceCar((int) priceCarByBYN);
            double priceRegistrationByBYN = registration.getPriceRegistration() * 2.6;
            registration.setPriceRegistration(priceRegistrationByBYN);
            registrationService.update(registration);
        } else {
            registrationService.update(registration);
        }
        modelAndView.setViewName("redirect:/car/view/{id}");
        return modelAndView;
    }

    @GetMapping("car/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        Car car = getCarById(id);
        modelAndView.setViewName("car/edit");
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @PostMapping("car/edit/{id}")
    public ModelAndView editCar(@ModelAttribute("car") Car car,
                                BindingResult result,
                                @AuthenticationPrincipal UserDetails userDetails,
                                @PathVariable("id") int id) {
        if (result.hasErrors()) {
            errorIncorrectEnter();
            return modelAndView;
        }

        String userName = userDetails.getUsername();
        User user = userService.findByLogin(userName);
        car.setUser(user);
        modelAndView.setViewName("redirect:/car/view/{id}");
        carService.update(car);
        return modelAndView;
    }

    @GetMapping("car/delete/{id}")
    public ModelAndView deletePage(@PathVariable("id") int id) {
        Car car = getCarById(id);
        modelAndView.setViewName("redirect:/");
        carService.delete(car);
        return modelAndView;
    }

}