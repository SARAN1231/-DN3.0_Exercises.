package com.saran.Bookstoreapi.Service;

import com.saran.Bookstoreapi.Models.Book;
import com.saran.Bookstoreapi.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        try {
            return bookRepository.findAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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


    public Book updateBook(Long id, Book book) {
        try {
            Optional<Book> book1 = bookRepository.findById(id);
            if (book1.isPresent()) {
                Book existingBook = book1.get();
                existingBook.setTitle(book.getTitle());
                existingBook.setAuthor(book.getAuthor());
                existingBook.setPrice(book.getPrice());
                bookRepository.save(existingBook);
                return existingBook;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteBook(Long id) {
        try{
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                bookRepository.deleteById(id);
                return "Book deleted successfully";
            }
            else {
                return "No Book Found";
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Book not deleted";
    }

    public Book getBookById(Long id) {
        try {
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                return book.get();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book SearchBook(String title, String author) {
        Book book = bookRepository.findByTitleAndAuthor(title,author);
        if(book != null) {
            return book;
        }
        else if(title == null){
            return bookRepository.findByAuthor(author);
        }
        else if(author == null){
            return bookRepository.findByTitle(title);
        }
        return null;
    }
}
