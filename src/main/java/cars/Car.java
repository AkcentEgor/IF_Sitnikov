package cars;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Car {
    private final int year;
    private final String model;
    private final String body;
    private double engine;
    private final boolean transmission;
    private final String drive;
    private String color;

    public Car(int year, String model, String body, double engine, boolean transmission, String drive, String color) {
        this.year = year;
        this.model = model;
        this.body = body;
        this.engine = engine;
        this.transmission = transmission;
        this.drive = drive;
        this.color = color;
    }

    public void ShowInfo() {
        System.out.println(getClass().getSimpleName() + " (" + year + ")");
        System.out.println("Модель: " + model);
        System.out.println("Тип кузова: " + body);
        System.out.println("Объем двигателя: " + engine + 'л');
        System.out.println("Тип трансмиссии: " + (transmission ? "Автоматическая" : "Ручная"));
        System.out.println("Вид двигателя: " + drive);
        System.out.println("Цвет автомобиля: " + color);
        System.out.println();
    }

    public void setColor(String color) {
        if (color != null && !color.isEmpty()) {
            this.color = color;
        } else {
            System.out.println("Некорректный цвет автомобиля.");
        }
    }
}