package com.example.postgresdemo.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Введите название")
    @Size(min=2, message = "Название не может быть меньше 2 букв")
    private String title;

    @NotNull(message = "Заполните количество страниц")
    @Min(value = 10, message = "Не меньше 10")
    @Max(value = 1000, message = "Не больше 1000")
    private float pages;

    @NotNull(message = "Заполните количество страниц")
    @Min(value = 10, message = "Не меньше 10")
    @Max(value = 1000, message = "Не больше 1000")
    private float price;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    public Book(String title, float pages, float price, Author author) {
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.price = price;
    }

    public Book() {

    }

// Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPages() {
        return pages;
    }

    public void setPages(float pages) {
        this.pages = pages;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
