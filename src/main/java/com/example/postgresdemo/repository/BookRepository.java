package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContains(String title);
    List<Book> findByTitleContaining(String title);
    List<Book> findByTitleIsContaining(String title);
    List<Book> findByTitleEquals(String title);

}
