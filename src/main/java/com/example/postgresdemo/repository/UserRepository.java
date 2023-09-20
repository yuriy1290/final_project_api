package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ModelUser,Long> {
    ModelUser findByUsername(String username);
}
