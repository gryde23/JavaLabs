package lab4.car;

public class Passenger extends Car {
    public Passenger(String brand, String color, Engine engine, int wheels) {
        super(brand, color, engine, Type.PASSENGER, wheels);
    }
    public Passenger(String brand, String color, Engine engine, int wheels, String regNumber) {
        super(brand, color, engine, Type.PASSENGER, wheels, regNumber);
    }
}
