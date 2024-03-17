package lab4.car;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Autobase {
    private final int maxCars;
    private final ArrayList<Car> onBase = new ArrayList<>();
    private final ArrayList<Car> onRepair = new ArrayList<>();
    private final ArrayList<Car> onTrip = new ArrayList<>();
    public Autobase(int max){
        this.maxCars = max;
    }

    public void addCarOnBase(Car car) {
        if (onBase.size() + onRepair.size() + onTrip.size() < maxCars) onBase.add(car);
        else System.out.println("База переполнена");
    }
    public void removeCarFromBase(Car car){
        onBase.remove(car);
    }
    public void carToTrip(Car car){
        if (onBase.contains(car)) {
            onTrip.add(car);
            removeCarFromBase(car);
            System.out.printf("Авто %s  %s отправлено в рейс\n", car.getMark(), car.getRegNumber());
        }
        else System.out.printf("Авто %s  %s нет на базе\n", car.getMark(), car.getRegNumber());
    }
    public void carToRepair(Car car){
        if(onBase.contains(car)) {
            onRepair.add(car);
            removeCarFromBase(car);
            System.out.printf("Авто %s  %s отправлено на ремонт\n", car.getMark(), car.getRegNumber());
        }
        else System.out.printf("Авто %s  %s нет на базе\n", car.getMark(), car.getRegNumber());
    }
    public void carFromRepair(Car car) {
        if (onRepair.contains(car)) {
            addCarOnBase(car);
            onRepair.remove(car);
            System.out.printf("Авто %s  %s отремонтировано и доставлено на базу\n", car.getMark(), car.getRegNumber());
        }
        else System.out.printf("Авто %s  %s не на ремонте\n", car.getMark(), car.getRegNumber());
    }
    public void carFromTrip(Car car) {
        if (onTrip.contains(car)) {
            addCarOnBase(car);
            onTrip.remove(car);
            System.out.printf("Авто %s  %s вернулось из рейса\n", car.getMark(), car.getRegNumber());
        }
        else System.out.printf("Авто %s  %s не в рейсе\n", car.getMark(), car.getRegNumber());
    }
    public void getCarsOnBase(){
        System.out.println("Авто на базе:");
        for(Car car: onBase){
            System.out.println(car.getMark()+ "   " + car.getRegNumber());
        }
    }
    public void getCarsOnRepair(){
        System.out.println("Авто на ремонте:");
        for(Car car: onRepair) {
            System.out.println(car.getMark() + "   " + car.getRegNumber());
        }
    }
    public void getCarsOnTrip(){
        System.out.println("Авто в рейсе:");
        for(Car car: onTrip){
            System.out.println(car.getMark() + "   " + car.getRegNumber());
        }
    }
}
