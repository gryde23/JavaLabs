package lab4.car;

public class Truck extends Car{
    public Truck(String brand, String color, Engine engine,int wheels) {
        super(brand, color, engine, Type.TRUCK, wheels);
    }

    public Truck(String brand, String color, Engine engine, int wheels, String regNumber) {
        super(brand, color, engine, Type.TRUCK, wheels, regNumber);
    }
}
