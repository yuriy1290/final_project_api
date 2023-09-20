package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.AuthorDetails;
import com.example.postgresdemo.repository.AuthorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthorDetailsController {


    @Autowired
    private AuthorDetailsRepository authorDetailsRepository;

    @GetMapping("/author_details")
    public Page<AuthorDetails> getAuthor_details(Pageable pageable) {

        return authorDetailsRepository.findAll(pageable);
    }


    @PostMapping("/author_details")
    public AuthorDetails createAuthor_details(@Valid @RequestBody AuthorDetails authorDetails) {

        return authorDetailsRepository.save(authorDetails);
    }

    @PutMapping("/author_details/{author_detailsId}")
    public AuthorDetails updateAuthor_details(@PathVariable Long author_detailsId,
                                   @Valid @RequestBody AuthorDetails author_detailsRequest) {
        return authorDetailsRepository.findById(author_detailsId)
                .map(author_details -> {
                    author_details.setBiography(author_detailsRequest.getBiography());
                    author_details.setAge(author_detailsRequest.getAge());
                    return authorDetailsRepository.save(author_details);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + author_detailsId));
    }


    @DeleteMapping("/author_details/{author_detailsId}")
    public ResponseEntity<?> deleteAuthor_details(@PathVariable Long author_detailsId) {
        return authorDetailsRepository.findById(author_detailsId)
                .map(author_details -> {
                    authorDetailsRepository.delete(author_details);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Author_details not found with id " + author_detailsId));
    }
}
