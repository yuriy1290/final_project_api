package com.example.postgresdemo.controller;

import com.example.postgresdemo.model.RegistrationRequest;
import com.example.postgresdemo.model.ModelUser;
import com.example.postgresdemo.repository.RoleRepository;
import com.example.postgresdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/registration")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping()
    private ResponseEntity<String> reg(@RequestBody RegistrationRequest registrationBody){
        ModelUser userFromDB = userRepository.findByUsername(registrationBody.getUsername());
        if (userFromDB != null){
            return ResponseEntity.badRequest().body("Пользователь с таким логином уже существует");
        }

        ModelUser user = new ModelUser();
        user.setActive(true);
        user.setRole(roleRepository.findByName("USER"));
        user.setUsername(registrationBody.getUsername());
        user.setPassword(passwordEncoder.encode(registrationBody.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("success");
    }

}
