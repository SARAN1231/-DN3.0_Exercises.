package com.saran.Bookstoreapi.Controller;

import com.saran.Bookstoreapi.DTO.BookDTO;
import com.saran.Bookstoreapi.Models.Book;
import com.saran.Bookstoreapi.Service.BookService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @PostMapping("/add-book")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        String s = bookService.addBook(book);
            return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody Book book) {
        BookDTO bookDTO = bookService.updateBook(id,book);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        String s = bookService.deleteBook(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/all-books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        List<BookDTO> bookDTOList = books.stream().map(bookDTO -> {
            Link selfLink =   WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withSelfRel();
            bookDTO.add(selfLink);
            return bookDTO;
        }).toList();

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.getBookById(id);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel();
        bookDTO.add(selfLink);
        Link relLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");
        bookDTO.add(relLink);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<BookDTO> searchbooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        BookDTO book = bookService.SearchBook(title,author);
        if(book == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
