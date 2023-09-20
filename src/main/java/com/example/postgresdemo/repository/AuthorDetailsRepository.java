package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.AuthorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AuthorDetailsRepository extends JpaRepository<AuthorDetails, Long> {

    AuthorDetails findByBiography(String biography);

}
