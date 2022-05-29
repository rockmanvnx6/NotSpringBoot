package dev.auspham.demo.domain;

public class Car {
    private final int year;
    private final String name;

    public Car(int year, String name) {
        this.year = year;
        this.name = name;
    }


    public String toString() {
        return String.format("Car name={%s}, year={%d}", this.name, this.year);
    }
}
