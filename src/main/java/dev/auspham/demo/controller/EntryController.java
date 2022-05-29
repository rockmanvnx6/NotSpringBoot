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
