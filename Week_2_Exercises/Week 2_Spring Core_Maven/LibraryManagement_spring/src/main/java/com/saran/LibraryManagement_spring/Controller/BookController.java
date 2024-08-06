package com.saran.LibraryManagement_spring.Controller;

import com.saran.LibraryManagement_spring.Models.Book;
import com.saran.LibraryManagement_spring.Repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            bookRepository.save(book);
            return new ResponseEntity<>(" Book added Successfully", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-books")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/update-book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        try {
            Book books = bookRepository.findById(id).get();
            books.setTitle(book.getTitle());
            books.setAuthor(book.getAuthor());
            books.setPublisher(book.getPublisher());
            return new ResponseEntity<>(bookRepository.save(books), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
