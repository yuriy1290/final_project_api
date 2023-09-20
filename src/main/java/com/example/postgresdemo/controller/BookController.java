package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Book;
import com.example.postgresdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public Page<Book> getBooks(Pageable pageable) {

        return bookRepository.findAll(pageable);
    }


    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody Book book) {

        return bookRepository.save(book);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable Long bookId,
                                   @Valid @RequestBody Book bookRequest) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setTitle(bookRequest.getTitle());
                    book.setPages(bookRequest.getPages());
                    book.setPrice(bookRequest.getPrice());
                    book.setAuthor(bookRequest.getAuthor());
                    book.setGenres(bookRequest.getGenres());
                    book.setPublisher(bookRequest.getPublisher());
                    return bookRepository.save(book);
                }).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
    }


    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    bookRepository.delete(book);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
    }

}
