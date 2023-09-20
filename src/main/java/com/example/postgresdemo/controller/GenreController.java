package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Genre;
import com.example.postgresdemo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/genres")
    public Page<Genre> getGenres(Pageable pageable) {

        return genreRepository.findAll(pageable);
    }


    @PostMapping("/genres")
    public Genre createGenre(@Valid @RequestBody Genre genre) {

        return genreRepository.save(genre);
    }

    @PutMapping("/genres/{genreId}")
    public Genre updateGenre(@PathVariable Long genreId,
                                   @Valid @RequestBody Genre genreRequest) {
        return genreRepository.findById(genreId)
                .map(genre -> {
                    genre.setGenreName(genreRequest.getGenreName());
                    return genreRepository.save(genre);
                }).orElseThrow(() -> new ResourceNotFoundException("Genre not found with id " + genreId));
    }


    @DeleteMapping("/genres/{genreId}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long genreId) {
        return genreRepository.findById(genreId)
                .map(genre -> {
                    genreRepository.delete(genre);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Genre not found with id " + genreId));
    }

}
