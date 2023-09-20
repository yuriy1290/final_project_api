package com.example.postgresdemo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Введите имя")
    @Size(min=2, message = "Имя не может быть меньше 2 букв")
    private String name;
    @NotBlank(message = "Введите фамилию")
    @Size(min=2, message = "Фамилия не может быть меньше 2 букв")
    private String surname;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_details_id")
    private AuthorDetails authorDetails;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(String name, String surname, AuthorDetails authorDetails) {
        this.name = name;
        this.surname = surname;
        this.authorDetails = authorDetails;
    }

    public Author() {

    }

    // Геттеры и сеттеры
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public AuthorDetails getAuthorDetails() {
        return authorDetails;
    }

    public void setAuthorDetails(AuthorDetails authorDetails) {
        this.authorDetails = authorDetails;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
