package com.saran.Bookstoreapi.Controller;

import com.saran.Bookstoreapi.DTO.CustomerDTO;
import com.saran.Bookstoreapi.ExceptionHandlers.ResourceNotFoundException;
import com.saran.Bookstoreapi.Mappers.GlobalMapper;
import com.saran.Bookstoreapi.Models.Customer;
import com.saran.Bookstoreapi.Repository.CustomerRepository;
import com.saran.Bookstoreapi.Service.BookService;
import com.saran.Bookstoreapi.Service.CustomerService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final GlobalMapper globalMapper;

    public CustomerController(CustomerService customerService, CustomerRepository customerRepository, GlobalMapper globalMapper) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.globalMapper = globalMapper;
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

    @GetMapping("/all-customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customersList = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = customersList.stream().map(globalMapper::toCustomerDTO).toList();
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Customer not found with id "+id));
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setEmail(customer.getEmail());
        customerRepository.save(customerToUpdate);
        return new ResponseEntity<>(globalMapper.toCustomerDTO(customer),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        Customer customerToDelete = customerRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Customer not found with id "+id));
        customerRepository.delete(customerToDelete);
        return new ResponseEntity<>("Customer deleted",HttpStatus.OK);
    }
}
