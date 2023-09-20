package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.PublisherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PublisherDetailsRepository extends JpaRepository<PublisherDetails, Long> {

    PublisherDetails findByInformation(String information);

}
