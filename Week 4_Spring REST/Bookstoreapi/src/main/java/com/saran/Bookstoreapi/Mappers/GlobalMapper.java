package com.saran.Bookstoreapi.Mappers;

import com.saran.Bookstoreapi.DTO.BookDTO;
import com.saran.Bookstoreapi.DTO.CustomerDTO;
import com.saran.Bookstoreapi.Models.Book;
import com.saran.Bookstoreapi.Models.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring") // it creates the spring bean and this class can be used in any class by dependency injection
public interface GlobalMapper {

    BookDTO toBookDTO(Book book);
    CustomerDTO toCustomerDTO(Customer customer);
}
