package lab4.car;

import java.util.regex.Pattern;

public class SpecialCar extends Car{
    private final Pattern patternRegNumSP = Pattern.compile("[АВЕКМНОРСТУХ]\\d{4}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS");
    public SpecialCar(String brand, String color, Engine engine,int wheels) {
        super(brand, color, engine, Type.SPECIAL, wheels);
    }
    public SpecialCar(String brand, String regNumber, String color, Engine engine,int wheels) {
        super(brand, color, engine, Type.SPECIAL, wheels);
        setRegNumber(regNumber, patternRegNumSP);
    }

}
