package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Publisher;
import com.example.postgresdemo.model.Role;
import com.example.postgresdemo.repository.PublisherRepository;
import com.example.postgresdemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/roles")
    public Page<Role> getRoles(Pageable pageable) {

        return roleRepository.findAll(pageable);
    }


    @PostMapping("/roles")
    public Role createRole(@Valid @RequestBody Role role) {

        return roleRepository.save(role);
    }

    @PutMapping("/roles/{roleId}")
    public Role updateRole(@PathVariable Long roleId,
                                     @Valid @RequestBody Role roleRequest) {
        return roleRepository.findById(roleId)
                .map(role -> {
                    role.setName(roleRequest.getName());
                    return roleRepository.save(role);
                }).orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));
    }


    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Long roleId) {
        return roleRepository.findById(roleId)
                .map(role -> {
                    roleRepository.delete(role);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));
    }
}
