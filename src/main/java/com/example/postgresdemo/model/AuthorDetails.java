package com.example.postgresdemo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "author_details")
public class AuthorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Введите биографию")
    @Size(min=25, message = "Биография не может быть меньше 25 букв")
    private String biography;
    @NotNull(message = "Заполните возраст")
    @Min(value = 15, message = "Не меньше 15")
    @Max(value = 100, message = "Не больше 100")
    private float age;

    @OneToOne(mappedBy = "authorDetails")
    private Author author;

    public AuthorDetails(String biography, float age, Author author) {
        this.biography = biography;
        this.age = age;
        this.author = author;
    }

    public AuthorDetails() {

    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}