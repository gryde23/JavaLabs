package lab4.car;

public final class Bus extends Car{
    public Bus(String brand, String color, Engine engine,int wheels) {
        super(brand, color, engine, Type.BUS, wheels);
    }
    public Bus(String brand, String color, Engine engine,int wheels,String regNumber) {
        super(brand, color, engine, Type.BUS, wheels, regNumber);
    }
}
