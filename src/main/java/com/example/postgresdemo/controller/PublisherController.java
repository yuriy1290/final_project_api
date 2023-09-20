package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Publisher;
import com.example.postgresdemo.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
public class PublisherController {

    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping("/publishers")
    public Page<Publisher> getPublishers(Pageable pageable) {

        return publisherRepository.findAll(pageable);
    }


    @PostMapping("/publishers")
    public Publisher createPublisher(@Valid @RequestBody Publisher publisher) {

        return publisherRepository.save(publisher);
    }

    @PutMapping("/publishers/{publisherId}")
    public Publisher updatePublisher(@PathVariable Long publisherId,
                                   @Valid @RequestBody Publisher publisherRequest) {
        return publisherRepository.findById(publisherId)
                .map(publisher -> {
                    publisher.setName(publisherRequest.getName());
                    publisher.setPublisherDetails(publisherRequest.getPublisherDetails());
                    return publisherRepository.save(publisher);
                }).orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id " + publisherId));
    }


    @DeleteMapping("/publishers/{publisherId}")
    public ResponseEntity<?> deletePublisher(@PathVariable Long publisherId) {
        return publisherRepository.findById(publisherId)
                .map(publisher -> {
                    publisherRepository.delete(publisher);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id " + publisherId));
    }

}
