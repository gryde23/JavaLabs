package lab4.car;

public class Bus extends Car{
    public Bus(String brand, String color, Engine engine,int wheels) {
        super(brand, color, engine, Type.BUS, wheels);
    }
    public Bus(String brand, String regNumber, String color, Engine engine,int wheels) {
        super(brand, regNumber, color, engine, Type.BUS, wheels);
    }
}
