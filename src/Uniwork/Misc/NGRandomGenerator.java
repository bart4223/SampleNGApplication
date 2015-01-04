package Uniwork.Misc;

import Uniwork.Base.NGObject;

import java.util.Random;

public class NGRandomGenerator extends NGObject {

    protected Random FRandom;

    public static NGRandomGenerator GlobalRandomGenerator = new NGRandomGenerator();

    public NGRandomGenerator() {
        super();
        FRandom = new Random();
    }

    public Boolean getBoolean() {
        return FRandom.nextBoolean();
    }

    public Integer getInteger(Integer aMaxValue) {
        return getInteger(0, aMaxValue);
    }

    public Integer getInteger(Integer aMinValue, Integer aMaxValue) {
        Integer value = Math.abs(aMaxValue - aMinValue) + 1;
        if (aMaxValue > aMinValue) {
            return aMinValue + FRandom.nextInt(value);
        }
        else {
            return aMaxValue + FRandom.nextInt(value);
        }
    }

    public Integer getInteger() {
        return FRandom.nextInt();
    }

    public Double getDouble() {
        return FRandom.nextDouble();
    }

}
