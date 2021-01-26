package projectCar.methods;

import projectCar.entity.*;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class Calculations {

    public static int amountOfDays(Date startDate, Date endDate) {
        long period = Math.abs(endDate.getTime() - startDate.getTime());
        return (int) TimeUnit.DAYS.convert(period, TimeUnit.MILLISECONDS);
    }

    public static int amountOfMonths(Date startDate, Date endDate) {
        return (int) ChronoUnit.MONTHS.between(startDate.toLocalDate(), endDate.toLocalDate());
    }

    public static double fuelSum(double liter, double value) {
        return liter * value;
    }

    public static double fuelValue(double sum, double liter) {
        return sum / liter;
    }

    public static int fuelDistance(double literValue, double averageRate) {
        double distance = (literValue / averageRate) * 100;
        return (int) Math.round(distance);
    }

    public static double getAllOtherCost(List<OtherCosts> otherCostsList) {
        double costs = 0;
        for (OtherCosts listCosts : otherCostsList) {
            costs = costs + listCosts.getCost();
        }
        return costs;
    }

    public static int endMileageRepairs(int startMileage, int serviceMileage) {
        return startMileage + serviceMileage;
    }

    public static double getAllCostsByCarId(List<Car> listCar, Car car) {
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

    public static int getCountPage(int page, int listSize) {
        return (listSize + 10 * page) / 10;
    }

    public static String getPrevPage(String prevPage) {
        return prevPage.substring(21, prevPage.length());
    }
}