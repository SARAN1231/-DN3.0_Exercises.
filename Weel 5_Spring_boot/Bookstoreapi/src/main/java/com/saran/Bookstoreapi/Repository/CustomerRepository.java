package com.saran.Bookstoreapi.Repository;

import com.saran.Bookstoreapi.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
