package com.saran.Bookstoreapi.Service;

import com.saran.Bookstoreapi.Models.Customer;
import com.saran.Bookstoreapi.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private  final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
