package com.sreedhar.LibraryManagementSystem.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.sreedhar.LibraryManagementSystem.dto.IssueRequest;
import com.sreedhar.LibraryManagementSystem.entity.Book;
import com.sreedhar.LibraryManagementSystem.entity.IssueBook;
import com.sreedhar.LibraryManagementSystem.entity.Member;
import com.sreedhar.LibraryManagementSystem.exception.BookNotFoundException;
import com.sreedhar.LibraryManagementSystem.exception.IssueNotFoundException;
import com.sreedhar.LibraryManagementSystem.exception.MemberNotFoundException;
import com.sreedhar.LibraryManagementSystem.repository.BookRepository;
import com.sreedhar.LibraryManagementSystem.repository.IssueBookRepository;
import com.sreedhar.LibraryManagementSystem.repository.MemberRepository;

@Service
public class IssueService {

    private final IssueBookRepository issueBookRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public IssueService(IssueBookRepository issueBookRepository,
                        BookRepository bookRepository,
                        MemberRepository memberRepository) {

        this.issueBookRepository = issueBookRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    // Issue Book
    public IssueBook issueBook(IssueRequest request) {

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() ->
                        new BookNotFoundException("Book Not Found"));

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() ->
                        new MemberNotFoundException("Member Not Found"));

        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Book Not Available");
        }

        // Decrease Book Quantity
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // Create Issue Record
        IssueBook issue = new IssueBook();
        issue.setBook(book);
        issue.setMember(member);
        issue.setIssueDate(LocalDate.now());
        issue.setStatus("ISSUED");
        issue.setFine(0.0);

        return issueBookRepository.save(issue);
    }

    // Return Book
    public IssueBook returnBook(Long issueId) {

        IssueBook issue = issueBookRepository.findById(issueId)
                .orElseThrow(() ->
                        new IssueNotFoundException("Issue Record Not Found"));

        if ("RETURNED".equalsIgnoreCase(issue.getStatus())) {
            throw new RuntimeException("Book Already Returned");
        }

        Book book = issue.getBook();

        // Increase Book Quantity
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        // Update Issue Record
        issue.setReturnDate(LocalDate.now());
        issue.setStatus("RETURNED");

        // Calculate Fine
        long days = ChronoUnit.DAYS.between(issue.getIssueDate(), LocalDate.now());

        double fine = 0;

        if (days > 7) {
            fine = (days - 7) * 10;
        }

        issue.setFine(fine);

        return issueBookRepository.save(issue);
    }
}