package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.PublisherDetails;
import com.example.postgresdemo.repository.PublisherDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
public class PublisherDetailsConroller {

    @Autowired
    private PublisherDetailsRepository publisherDetailsRepository;

    @GetMapping("/publisherDetails")
    public Page<PublisherDetails> getPublisherDetails(Pageable pageable) {

        return publisherDetailsRepository.findAll(pageable);
    }


    @PostMapping("/publisherDetails")
    public PublisherDetails createPublisherDetails(@Valid @RequestBody PublisherDetails publisherDetails) {

        return publisherDetailsRepository.save(publisherDetails);
    }

    @PutMapping("/publisherDetails/{publisherDetailsId}")
    public PublisherDetails updatePublisherDetails(@PathVariable Long publisherDetailsId,
                                                   @Valid @RequestBody PublisherDetails publisherDetailsRequest) {
        return publisherDetailsRepository.findById(publisherDetailsId)
                .map(publisherDetails -> {
                    publisherDetails.setInformation(publisherDetailsRequest.getInformation());
                    return publisherDetailsRepository.save(publisherDetails);
                }).orElseThrow(() -> new ResourceNotFoundException("PublisherDetails not found with id " + publisherDetailsId));
    }


    @DeleteMapping("/publisherDetails/{publisherDetailsId}")
    public ResponseEntity<?> deletePublisherDetails(@PathVariable Long publisherDetailsId) {
        return publisherDetailsRepository.findById(publisherDetailsId)
                .map(publisherDetails -> {
                    publisherDetailsRepository.delete(publisherDetails);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("PublisherDetails not found with id " + publisherDetailsId));
    }

}
