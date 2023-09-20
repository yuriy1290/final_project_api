package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByGenreName(String genreName);

}
