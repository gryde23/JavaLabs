package lab4.car;

public class Passenger extends Car {
    public Passenger(String brand, String color, Engine engine, int wheels) {
        super(brand, color, engine, Type.PASSENGER, wheels);
    }
    public Passenger(String brand, String regNumber, String color, Engine engine, int wheels) {
        super(brand, regNumber, color, engine, Type.PASSENGER, wheels);
    }
}
