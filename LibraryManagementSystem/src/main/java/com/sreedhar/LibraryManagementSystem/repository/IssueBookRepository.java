package com.sreedhar.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sreedhar.LibraryManagementSystem.entity.IssueBook;

public interface IssueBookRepository extends JpaRepository<IssueBook, Long> {

}
