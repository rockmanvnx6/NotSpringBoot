package dev.auspham.demo;

import dev.auspham.demo.domain.Car;
import dev.auspham.demo.domain.Customer;
import dev.auspham.notspringboot.NotSpringBoot;
import dev.auspham.notspringboot.annotation.Bean;
public class Application {
    public static void main(String[] args) throws Exception {
        NotSpringBoot.run(Application.class, args);
    }

    @Bean
    public Customer getCustomer() {
        return new Customer("Austin", 19);
    }

    @Bean
    public Car getCar() {
        return new Car(2012, "Toyota");
    }
}
