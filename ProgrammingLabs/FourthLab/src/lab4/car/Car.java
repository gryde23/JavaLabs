package lab4.car;

import java.util.regex.Pattern;

public abstract class Car {
    public enum Type {PASSENGER, BUS, TRUCK, SPECIAL}
    private final Pattern patternRegNum = Pattern.compile("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS");
    private String regNumber, color;
    private final String brand;
    private Engine engine;
    private final int wheels;
    private final Type type;

    public Car(String brand, String color, Engine engine, Type type, int wheels) {
        this.brand = brand;
        this.color = color;
        this.engine = engine;
        this.type = type;
        this.wheels = wheels;
    }
    public Car(String brand, String color, Engine engine, Type type, int wheels, String regNumber) {
       setRegNumber(regNumber);
        this.brand = brand;
        this.color = color;
        this.engine = engine;
        this.type = type;
        this.wheels = wheels;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNumber='" + regNumber + '\'' + '\n' +
                " color='" + color + '\'' + '\n' +
                " brand='" + brand + '\'' + '\n' +
                " engine=" + engine + '\n' +
                " wheels=" + wheels + '\n' +
                " type=" + type +
                "}\n";
    }

    public final void setColor(String color) {
        this.color = color;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setRegNumber(String regNumber) {
        setRegNumber(regNumber, patternRegNum);
    }

    public int getWheels() {
        return wheels;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getColor() {
        return color;
    }

    public String getMark() {
        return brand;
    }

    public Type getType() {
        return type;
    }
    protected void setRegNumber(String regNum, Pattern pattern) {
        if (pattern.matcher(regNum).matches()) this.regNumber = regNum;
        else System.out.println("Некорректно задан номер");
    }
}
