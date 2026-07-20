package com.sreedhar.LibraryManagementSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sreedhar.LibraryManagementSystem.entity.Book;
import com.sreedhar.LibraryManagementSystem.exception.BookNotFoundException;
import com.sreedhar.LibraryManagementSystem.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }
    public List<Book> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchByAuthor(String author) {
        return repository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> searchByCategory(String category) {
        return repository.findByCategoryContainingIgnoreCase(category);
    }

    public List<Book> getAvailableBooks() {
        return repository.findByQuantityGreaterThan(0);
    }

    // Save Book
    public Book save(Book book) {
        return repository.save(book);
    }

    // Get All Books
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    // Get Book By Id
    public Book getBook(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book Not Found"));
    }

    // Update Book
    public Book updateBook(Long id, Book book) {

        Book existing = repository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book Not Found"));

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setCategory(book.getCategory());
        existing.setPrice(book.getPrice());
        existing.setQuantity(book.getQuantity());

        return repository.save(existing);
    }

    // Delete Book
    public void deleteBook(Long id) {

        Book book = repository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book Not Found"));

        repository.delete(book);
    }
}