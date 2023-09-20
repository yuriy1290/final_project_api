package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.ModelUser;
import com.example.postgresdemo.model.Publisher;
import com.example.postgresdemo.repository.PublisherRepository;
import com.example.postgresdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Page<ModelUser> getModelUsers(Pageable pageable) {

        return userRepository.findAll(pageable);
    }


    @PostMapping("/users")
    public ModelUser createModelUser(@Valid @RequestBody ModelUser modelUser) {

        return userRepository.save(modelUser);
    }

    @PutMapping("/users/{userId}")
    public ModelUser updateModelUser(@PathVariable Long modelUserId,
                                     @Valid @RequestBody ModelUser modelUserRequest) {
        return userRepository.findById(modelUserId)
                .map(modelUser -> {
                    modelUser.setUsername(modelUserRequest.getUsername());
                    modelUser.setPassword(modelUserRequest.getPassword());
                    modelUser.setRole(modelUserRequest.getRole());
                    return userRepository.save(modelUser);
                }).orElseThrow(() -> new ResourceNotFoundException("ModelUser not found with id " + modelUserId));
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteModelUser(@PathVariable Long modelUserId) {
        return userRepository.findById(modelUserId)
                .map(modelUser -> {
                    userRepository.delete(modelUser);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("ModelUser not found with id " + modelUserId));
    }
}
