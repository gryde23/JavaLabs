package lab4.car;

public class Truck extends Car{
    public Truck(String brand, String color, Engine engine,int wheels) {
        super(brand, color, engine, Type.TRUCK, wheels);
    }

    public Truck(String brand, String regNumber, String color, Engine engine, int wheels) {
        super(brand, regNumber, color, engine, Type.TRUCK, wheels);
    }
}
