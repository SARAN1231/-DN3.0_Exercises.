package com.saran.Bookstoreapi.Controller;

import com.saran.Bookstoreapi.Models.Customer;
import com.saran.Bookstoreapi.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
       Customer createdCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@ModelAttribute Customer customer) { // modalAttributes used to get the inputs from the form from client side. for  testing  postman in body use form-urlencoded and five ilp as key-value pair
        Customer registeredCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(registeredCustomer,HttpStatus.CREATED);
    }
}
