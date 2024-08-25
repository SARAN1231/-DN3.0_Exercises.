package com.saran.Bookstoreapi.Service;

import com.saran.Bookstoreapi.DTO.BookDTO;
import com.saran.Bookstoreapi.ExceptionHandlers.ResourceNotFoundException;
import com.saran.Bookstoreapi.Mappers.GlobalMapper;
import com.saran.Bookstoreapi.Models.Book;
import com.saran.Bookstoreapi.Repository.BookRepository;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GlobalMapper globalMapper;

    public BookService(BookRepository bookRepository, GlobalMapper globalMapper) {
        this.bookRepository = bookRepository;
        this.globalMapper = globalMapper;
    }

    public List<BookDTO> getAllBooks() {
      List<Book> books = bookRepository.findAll();
      List<BookDTO> bookDTOs = books.stream().map(globalMapper::toBookDTO).collect(Collectors.toList());
      return bookDTOs;
    }

    public String addBook(Book book) {
        try {
            bookRepository.save(book);
            return "Book added successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Book not added";
    }


    public BookDTO updateBook(Long id, Book book) {
       Book existinBook = bookRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("Book not found with id"+ id));
       existinBook.setTitle(book.getTitle());
       existinBook.setAuthor(book.getAuthor());
       existinBook.setPrice(book.getPrice());
       bookRepository.save(existinBook);
       return globalMapper.toBookDTO(existinBook);
    }

    public String deleteBook(Long id) {
        Book book  = bookRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book not found with id"+ id));
        bookRepository.delete(book);
        return "Book deleted successfully";
    }

    public BookDTO getBookById(Long id) {
       Book book =  bookRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("Book not found with id"+ id));
       BookDTO bookDTO = globalMapper.toBookDTO(book);

       return bookDTO;
    }

    public BookDTO SearchBook(String title, String author) {
        Book book = bookRepository.findByTitleAndAuthor(title,author);

        if(book != null) {
            return globalMapper.toBookDTO(book);
        }
        else if(title == null){
            return globalMapper.toBookDTO(book);
        }
        else if(author == null){
            return globalMapper.toBookDTO(book);
        }
        return null;
    }
}
