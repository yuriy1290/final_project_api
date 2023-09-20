package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Author;
import com.example.postgresdemo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public Page<Author> getAuthors(Pageable pageable) {

        return authorRepository.findAll(pageable);
    }


    @PostMapping("/authors")
    public Author createAuthor(@Valid @RequestBody Author author) {

        return authorRepository.save(author);
    }

    @PutMapping("/authors/{authorId}")
    public Author updateAuthor(@PathVariable Long authorId,
                                   @Valid @RequestBody Author authorRequest) {
        return authorRepository.findById(authorId)
                .map(author -> {
                    author.setName(authorRequest.getName());
                    author.setSurname(authorRequest.getSurname());
                    author.setAuthorDetails(authorRequest.getAuthorDetails());
                    return authorRepository.save(author);
                }).orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + authorId));
    }


    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long authorId) {
        return authorRepository.findById(authorId)
                .map(author -> {
                    authorRepository.delete(author);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + authorId));
    }

}
