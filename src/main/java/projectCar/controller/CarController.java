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
import projectCar.entity.Car;
import projectCar.entity.Currency;
import projectCar.entity.Registration;
import projectCar.entity.User;
import projectCar.methods.Calculations;
import projectCar.methods.ServiceSolution;
import projectCar.service.RegistrationServiceImpl;
import projectCar.service.UserServiceImpl;
import projectCar.service.interfaces.IRegistrationService;
import projectCar.service.interfaces.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class CarController {

    @Autowired
    private ServiceSolution solutions;

    @Autowired
    private IUserService userService = new UserServiceImpl();

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
        boolean haveMistakes = result.hasErrors();
        if (haveMistakes) {
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            return modelAndView;
        }
        User user = userService.findByLogin(authUser.getUsername());
        car.setUser(user);
        modelAndView.setViewName("redirect:/");
        solutions.createCar(car);
        return modelAndView;
    }

    @GetMapping("car/costs/first/{id}")
    public ModelAndView createFirstCost(@PathVariable("id") int id) {
        modelAndView.setViewName("car/costs/first");
        modelAndView.addObject("registration", new Registration());
        modelAndView.addObject("car", solutions.getCarById(id));
        modelAndView.addObject("currency", solutions.getCurrencyFromCarById(id));
        return modelAndView;
    }

    @PostMapping("car/costs/first/{id}")
    public ModelAndView addFirstCost(@PathVariable("id") int id,
                                     @ModelAttribute("registration") Registration registration,
                                     BindingResult result) {
        if (result.hasErrors()) {
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            return modelAndView;
        }
        Currency currency = solutions.getCurrencyFromCarById(id);
        registration.setCar(solutions.getCarById(id));

        if (currency.getTitle().equals("USD")) {
            double priceCarByBYN = solutions.getValueByUSD(registration.getPriceCar());
            registration.setPriceCar((int) priceCarByBYN);
            double priceRegistrationByBYN = solutions.getValueByUSD(registration.getPriceRegistration());
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
        Car car = solutions.getCarById(id);
        Currency currency = solutions.getCurrencyFromCarById(id);
        modelAndView.setViewName("car/view");
        modelAndView.addObject("car", car);
        modelAndView.addObject("parameter", car.getParameters());

        double valueUSD = solutions.getCurrencyValueUSD();

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

        if ((Objects.nonNull(car.getRegistration())) && (Objects.nonNull(car.getParameters()))) {
            List<Car> listCar = solutions.getListAllCostsByCar(id);
            double costs = Calculations.getAllCostsByCarId(listCar, car);
            modelAndView.addObject("allCosts", costs);
            double valueByUSD = costs / valueUSD;
            modelAndView.addObject("allCostsUSD", valueByUSD);
        }
        return modelAndView;
    }

    @GetMapping("car/costs/edit/{id}")
    public ModelAndView editPageFirstCost(@PathVariable("id") int id) {
        Car car = solutions.getCarById(id);
        modelAndView.setViewName("car/costs/edit");
        double valueUSD = solutions.getCurrencyValueUSD();
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
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            return modelAndView;
        }

        Car car = solutions.getCarById(id);
        Currency currency = car.getUser().getCurrency();
        registration.setCar(car);
        if (currency.getTitle().equals("BYN")) {
            registrationService.update(registration);
        } else {
            double priceRegistrationByBYN = solutions.getValueByUSD(registration.getPriceRegistration());
            registration.setPriceRegistration(priceRegistrationByBYN);
            double priceCarByBYN = solutions.getValueByUSD(registration.getPriceCar());
            registration.setPriceCar((int) priceCarByBYN);
            registrationService.update(registration);
        }
        modelAndView.setViewName("redirect:/car/view/{id}");
        return modelAndView;
    }

    @GetMapping("car/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id,
                                 HttpServletRequest request) {
        Car car = solutions.getCarById(id);
        String backPage = request.getHeader("referer");
        prevPage = Calculations.getPrevPage(backPage);
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
            modelAndView.addObject("Errors", "Некоректный ввод данных");
            return modelAndView;
        }

        User user = userService.findByLogin(authUser.getUsername());
        car.setUser(user);
        modelAndView.setViewName("redirect:" + prevPage);
        solutions.updateCar(car);
        return modelAndView;
    }

    @GetMapping("car/delete/{id}")
    public ModelAndView deletePage(@PathVariable("id") int id) {
        Car car = solutions.getCarById(id);
        modelAndView.setViewName("redirect:/");
        solutions.deleteCar(car);
        return modelAndView;
    }
}