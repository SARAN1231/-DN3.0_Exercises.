package com.saran.Bookstoreapi.Controller;

import com.saran.Bookstoreapi.DTO.BookDTO;
import com.saran.Bookstoreapi.Models.Book;
import com.saran.Bookstoreapi.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Add a new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/add-book")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        String result = bookService.addBook(book);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody Book book) {
        BookDTO bookDTO = bookService.updateBook(id, book);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) })
    })
    @GetMapping("/all-books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
