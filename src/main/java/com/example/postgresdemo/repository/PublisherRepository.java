package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findByName(String name);

}
