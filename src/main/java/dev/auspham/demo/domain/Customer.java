package dev.auspham.demo.domain;

public class Customer {
    private final String name;
    private final int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return String.format("Customer name={%s}, age={%d}", this.name, this.age);
    }
}
