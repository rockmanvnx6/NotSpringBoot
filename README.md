# Simple `@Autowired` Implementation

Didn't understand how `@Autowired` works so I coded one ¯\_(ツ)_/¯

Usage:

```java
import dev.auspham.notspringboot.NotSpringBoot;
import dev.auspham.demo.domain.Car;
import dev.auspham.demo.domain.Customer;
import dev.auspham.notspringboot.NotSpringBoot;
import dev.auspham.notspringboot.annotation.Bean;

public class Application {
    public static void main(String[] args) throws Exception{
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
```
Run the application with `NotSpringBoot.run()`

Register your dependency injection with `@Bean`

Use it in your `@Controller` class with `@Autowired`

```java

package dev.auspham.demo.controller;

import dev.auspham.demo.domain.Car;
import dev.auspham.demo.domain.Customer;
import dev.auspham.notspringboot.annotation.Autowired;
import dev.auspham.notspringboot.annotation.Controller;
import dev.auspham.notspringboot.annotation.Start;

@Controller
public class EntryController {
    @Autowired
    private Customer myCustomer;

    @Autowired
    private Car myCar;

    @Start
    public void start() {
        System.out.println("Running EntryController");
        System.out.println(myCustomer);
        System.out.println(myCar);
    }
}

```

The `@Start` is the entry point of `@Controller`






