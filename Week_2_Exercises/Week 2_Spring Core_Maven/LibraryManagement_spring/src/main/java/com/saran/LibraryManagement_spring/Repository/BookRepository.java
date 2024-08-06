package com.saran.LibraryManagement_spring.Repository;

import com.saran.LibraryManagement_spring.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
