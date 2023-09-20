package com.example.postgresdemo.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "publisher_details")
public class PublisherDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Введите информацию")
    @Size(min=25, message = "Информация не может быть меньше 25 букв")
    private String information;

    @OneToOne(mappedBy = "publisherDetails")
    private Publisher publisher;

    public PublisherDetails() {

    }

    public PublisherDetails(Long id, String information, Publisher publisher) {
        this.id = id;
        this.information = information;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
