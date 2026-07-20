package com.sreedhar.LibraryManagementSystem.controller;

import org.springframework.web.bind.annotation.*;

import com.sreedhar.LibraryManagementSystem.dto.IssueRequest;
import com.sreedhar.LibraryManagementSystem.entity.IssueBook;
import com.sreedhar.LibraryManagementSystem.service.IssueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    // Issue Book
    @PostMapping
    public IssueBook issueBook(@Valid @RequestBody IssueRequest request) {
        return issueService.issueBook(request);
    }

    // Return Book
    @PutMapping("/{id}/return")
    public IssueBook returnBook(@PathVariable Long id) {
        return issueService.returnBook(id);
    }
}