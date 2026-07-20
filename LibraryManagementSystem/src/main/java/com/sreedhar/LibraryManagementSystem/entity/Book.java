package com.sreedhar.LibraryManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book title cannot be empty")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author name cannot be empty")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "Category cannot be empty")
    @Column(nullable = false)
    private String category;

    @NotNull(message = "Price is required")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Quantity is required")
    @Column(nullable = false)
    private Integer quantity;

    public Book() {
    }

    public Book(Long id, String title, String author,
                String category, Double price, Integer quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}