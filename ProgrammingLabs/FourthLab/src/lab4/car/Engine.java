package lab4.car;

public class Engine {
    private final int serialNumber, power, capacity, fuelConsumption;
    private final String fuelType;

    public Engine(int serialNumber, int power, int capacity, int fuelConsumption, String fuelType) {
        this.serialNumber = serialNumber;
        this.power = power;
        this.capacity = capacity;
        this.fuelConsumption = fuelConsumption;
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "serialNumber=" + serialNumber + '\n' +
                " power=" + power + '\n' +
                " capacity=" + capacity + '\n' +
                " fuelConsumption=" + fuelConsumption + '\n' +
                " fuelType='" + fuelType + '\'' +
                '}';
    }
}
