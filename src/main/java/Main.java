import cars.Car;
import models.*;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Suzuki(2005, "Swift", "Седан", 1.6, true, "Передний", "Голубой"));
        cars.add(new Toyota(2010, "RAV4", "SUV", 2.0, false, "Полный", "Зеленый"));
        cars.add(new Honda(2015, "Civic", "Хэтчбек", 1.8, true, "Передний", "Красный"));
        cars.add(new Ford(2004, "F-150", "Пикап", 3.5, false, "Задний", "Черный"));
        cars.add(new BMW(2020, "M4", "Купе", 3.0, true, "Задний", "Белый"));
        cars.add(new Suzuki(2007, "Baleno", "Седан", 1.4, false, "Передний", "Зеленый"));
        cars.add(new Toyota(2012, "Corolla", "Седан", 1.6, true, "Передний", "Серебряный"));
        cars.add(new Honda(2018, "CR-V", "SUV", 2.4, true, "Полный", "Синий"));
        cars.add(new Ford(2019, "Escape", "SUV", 2.0, true, "Полный", "Серый"));
        cars.add(new BMW(2021, "3 Series", "Седан", 2.0, true, "Задний", "Черный"));

        printCarsAfter2006(cars);
        changeGreenToRed(cars);
        printCarsWithManualTransmission(cars);
    }

    public static void printCarsAfter2006(List<Car> cars) {
        System.out.println("=== Автомобили, выпущенные после 2006 года ===");
        for (Car car : cars) {
            if (car.getYear() > 2006) {
                car.ShowInfo();
            } else {
                System.out.println(car.getModel() + " (" + car.getYear() + ") - устаревший авто");
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void changeGreenToRed(List<Car> cars) {
        System.out.println("=== Изменение цвета с зеленого на красный ===");
        for (Car car : cars) {
            if ("Зеленый".equalsIgnoreCase(car.getColor())) {
                System.out.println("Цвет изменен для " + car.getModel() + " с " + car.getColor() + " на Красный");
                car.setColor("Красный");
            }
        }
        System.out.println();
    }

    public static void printCarsWithManualTransmission(List<Car> cars) {
        System.out.println("=== Автомобили с ручной трансмиссией ===");
        for (Car car : cars) {
            if (!car.isTransmission()) {
                car.ShowInfo();
            }
        }
        System.out.println();
    }
}

