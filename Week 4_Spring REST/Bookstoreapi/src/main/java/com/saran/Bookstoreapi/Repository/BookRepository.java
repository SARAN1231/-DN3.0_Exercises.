package com.saran.Bookstoreapi.Repository;

import com.saran.Bookstoreapi.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findByTitle(String title);
    public Book findByAuthor(String author);
    public Book findByTitleAndAuthor(String title, String author);
}
