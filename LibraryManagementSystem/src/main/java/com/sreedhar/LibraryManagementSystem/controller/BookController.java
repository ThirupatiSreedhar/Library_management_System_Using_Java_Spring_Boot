package com.sreedhar.LibraryManagementSystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.sreedhar.LibraryManagementSystem.entity.Book;
import com.sreedhar.LibraryManagementSystem.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // Add Book
    @PostMapping
    public Book save(@Valid @RequestBody Book book) {
        return service.save(book);
    }

    // Get All Books
    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    // Get Book By Id
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

    // Update Book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,
                           @Valid @RequestBody Book book) {

        return service.updateBook(id, book);
    }

    // Delete Book
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {

        service.deleteBook(id);

        return "Book Deleted Successfully";
    }
    @GetMapping("/title/{title}")
    public List<Book> searchByTitle(@PathVariable String title) {
        return service.searchByTitle(title);
    }

    @GetMapping("/author/{author}")
    public List<Book> searchByAuthor(@PathVariable String author) {
        return service.searchByAuthor(author);
    }

    @GetMapping("/category/{category}")
    public List<Book> searchByCategory(@PathVariable String category) {
        return service.searchByCategory(category);
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        return service.getAvailableBooks();
    }
}