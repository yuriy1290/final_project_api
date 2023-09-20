package com.example.postgresdemo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "publishers")
public class Publisher {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @NotBlank(message = "Введите название")
        @Size(min=2, message = "Название не может быть меньше 2 букв")
        private String name;
        @OneToOne(optional = true, cascade = CascadeType.ALL)
        @JoinColumn(name = "publisher_details_id")
        private PublisherDetails publisherDetails;

        @OneToMany(mappedBy = "publisher")
        private List<Book> books;

    public Publisher() {
    }

    public Publisher(Long id, String name, PublisherDetails publisherDetails) {
        this.id = id;
        this.name = name;
        this.publisherDetails = publisherDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublisherDetails getPublisherDetails() {
        return publisherDetails;
    }

    public void setPublisherDetails(PublisherDetails publisherDetails) {
        this.publisherDetails = publisherDetails;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
