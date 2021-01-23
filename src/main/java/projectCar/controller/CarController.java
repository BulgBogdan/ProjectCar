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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

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

    private String prevPage = "";

    @GetMapping("car/title")
    public ModelAndView createCar() {
        modelAndView.setViewName("car/title");
        modelAndView.addObject("newCar", new Car());
        return modelAndView;
    }

    @PostMapping("car/title")
    public ModelAndView addCar(@ModelAttribute("newCar") Car car,
                               BindingResult result,
                               @AuthenticationPrincipal UserDetails authUser) {
        if (result.hasErrors()) {
            errorIncorrectEnter();
            return modelAndView;
        }
        User user = getUserByLogin(authUser.getUsername());
        car.setUser(user);
        modelAndView.setViewName("redirect:/");
        carService.add(car);
        return modelAndView;
    }

    @GetMapping("car/costs/first/{id}")
    public ModelAndView createFirstCost(@PathVariable("id") int id) {
        modelAndView.setViewName("car/costs/first");
        modelAndView.addObject("registration", new Registration());
        modelAndView.addObject("car", getCarById(id));
        modelAndView.addObject("currency", getCurrencyFromCarById(id));
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
        Currency currency = getCurrencyFromCarById(id);
        registration.setCar(getCarById(id));

        if (currency.getTitle().equals("USD")) {
            double priceCarByBYN = getValueByUSD(registration.getPriceCar());
            registration.setPriceCar((int) priceCarByBYN);
            double priceRegistrationByBYN = getValueByUSD(registration.getPriceRegistration());
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
        Currency currency = getCurrencyFromCarById(id);
        modelAndView.setViewName("car/view");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameter", car.getParameters());

        double valueUSD = getCurrencyValueUSD();

        if (currency.getTitle().equals("USD")
                && Objects.nonNull(car.getRegistration())
                && (car.getRegistration().getPriceRegistration() != 0)
                && (car.getRegistration().getPriceCar() != 0)) {
            int priceCar = (int) (car.getRegistration().getPriceCar() / valueUSD);
            double priceRegistration = car.getRegistration().getPriceRegistration() / valueUSD;
            car.getRegistration().setPriceRegistration(priceRegistration);
            car.getRegistration().setPriceCar(priceCar);
            modelAndView.addObject("registration", car.getRegistration());
        } else {
            modelAndView.addObject("registration", car.getRegistration());
        }

        if ((car.getRegistration() != null) && (car.getParameters() != null)) {
            List<Car> listCar = carService.getListsForCostsByID(id);
            double costs = getCostsByCarId(listCar, car);
            modelAndView.addObject("allCosts", costs);
            double valueByUSD = costs / valueUSD;
            modelAndView.addObject("allCostsUSD", valueByUSD);
        }
        return modelAndView;
    }

    @GetMapping("car/costs/edit/{id}")
    public ModelAndView editPageFirstCost(@PathVariable("id") int id) {
        Car car = getCarById(id);
        modelAndView.setViewName("car/costs/edit");
        double valueUSD = getCurrencyValueUSD();
        if (car.getUser().getCurrency().getTitle().equals("USD")) {
            car.getRegistration().setPriceCar((int) (car.getRegistration().getPriceCar() / valueUSD));
            car.getRegistration().setPriceRegistration(car.getRegistration().getPriceRegistration() / valueUSD);
            modelAndView.addObject("registration", car.getRegistration());
        } else {
            modelAndView.addObject("registration", car.getRegistration());
        }
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
        if (currency.getTitle().equals("BYN")) {
            registrationService.update(registration);
        } else {
            double priceCarByBYN = getValueByUSD(registration.getPriceCar());
            registration.setPriceCar((int) priceCarByBYN);
            double priceRegistrationByBYN = getValueByUSD(registration.getPriceRegistration());
            registration.setPriceRegistration(priceRegistrationByBYN);
            registrationService.update(registration);
        }
        modelAndView.setViewName("redirect:/car/view/{id}");
        return modelAndView;
    }

    @GetMapping("car/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id,
                                 HttpServletRequest request) {
        Car car = getCarById(id);
        String backPage = request.getHeader("referer");
        prevPage = getPrevPage(backPage);
        modelAndView.setViewName("car/edit");
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @PostMapping("car/edit/{id}")
    public ModelAndView editCar(@ModelAttribute("car") Car car,
                                BindingResult result,
                                @AuthenticationPrincipal UserDetails authUser,
                                @PathVariable("id") int id) {
        if (result.hasErrors()) {
            errorIncorrectEnter();
            return modelAndView;
        }

        User user = getUserByLogin(authUser.getUsername());
        car.setUser(user);
        modelAndView.setViewName("redirect:" + prevPage);
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

    private double getCostsByCarId(List<Car> listCar, Car car) {
        double costs = 0;
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
        double registrationCosts = car.getRegistration().getPriceCar() + car.getRegistration().getPriceRegistration();
        costs = costs + registrationCosts;
        return costs;
    }

    private double getCurrencyValueUSD() {
        return currencyService.read(2).getCurrencyValue();
    }

    private Currency getCurrencyFromCarById(int id) {
        Car car = carService.read(id);
        return car.getUser().getCurrency();
    }

    private double getValueByUSD(double valueByBYN) {
        return getCurrencyValueUSD() * valueByBYN;
    }

    private User getUserByLogin(String login) {
        return userService.findByLogin(login);
    }

    private Car getCarById(int id) {
        return carService.read(id);
    }

    private String getPrevPage(String prevPage) {
        return prevPage.substring(21, prevPage.length());
    }

    private void errorIncorrectEnter() {
        modelAndView.addObject("Errors", "Некорректный ввод");
    }

}