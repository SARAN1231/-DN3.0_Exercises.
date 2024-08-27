package com.saran.Bookstoreapi.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saran.Bookstoreapi.Controller.BookController;
import com.saran.Bookstoreapi.DTO.BookDTO;
import com.saran.Bookstoreapi.Models.Book;
import com.saran.Bookstoreapi.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private BookDTO bookDTO;
    private Book book;

    @BeforeEach
    void setup() {
        book = new Book(1L, "Test Book", "Test Author", 199.99);
        bookDTO = new BookDTO(1L, "Test Book", "Test Author", 199.99);
    }

    @Test
    void testAddBook() throws Exception {
        when(bookService.addBook(any(Book.class))).thenReturn("Book added successfully");

        mockMvc.perform(post("/book/add-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Book added successfully"));
    }

    @Test
    void testUpdateBook() throws Exception {
        when(bookService.updateBook(any(Long.class), any(Book.class))).thenReturn(bookDTO);

        mockMvc.perform(put("/book/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.price").value(199.99));
    }

    @Test
    void testDeleteBook() throws Exception {
        when(bookService.deleteBook(any(Long.class))).thenReturn("Book deleted successfully");

        mockMvc.perform(delete("/book/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book deleted successfully"));
    }

    @Test
    void testGetAllBooks() throws Exception {
        List<BookDTO> books = Arrays.asList(bookDTO);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/book/all-books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].author").value("Test Author"))
                .andExpect(jsonPath("$[0].price").value(199.99));
    }

    @Test
    void testGetBookById() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookDTO);

        mockMvc.perform(get("/book/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.price").value(199.99));
    }

    @Test
    void testSearchBooks() throws Exception {
        when(bookService.SearchBook("Test Book", "Test Author")).thenReturn(bookDTO);

        mockMvc.perform(get("/book/search")
                        .param("title", "Test Book")
                        .param("author", "Test Author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"));
    }
}
