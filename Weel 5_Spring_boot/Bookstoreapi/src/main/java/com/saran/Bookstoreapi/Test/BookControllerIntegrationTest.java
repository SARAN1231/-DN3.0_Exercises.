package com.saran.Bookstoreapi;

import com.saran.Bookstoreapi.Models.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Rollback database changes after each test
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddBook() throws Exception {
        // Arrange
        Book book = new Book();
        book.setTitle("Spring Integration");
        book.setAuthor("Jane Doe");

        // Convert the Book object to JSON format
        String bookJson = objectMapper.writeValueAsString(book);

        // Act & Assert
        mockMvc.perform(post("/book/add-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetBookById() throws Exception {
        // Assume there is a book with id 1 in the database

        mockMvc.perform(get("/book/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Spring Integration"));
    }
}
