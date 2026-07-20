package com.sreedhar.LibraryManagementSystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.sreedhar.LibraryManagementSystem.entity.Member;
import com.sreedhar.LibraryManagementSystem.service.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    // Add Member
    @PostMapping
    public Member save(@Valid @RequestBody Member member) {
        return service.save(member);
    }

    // Get All Members
    @GetMapping
    public List<Member> getAllMembers() {
        return service.getAllMembers();
    }

    // Get Member By Id
    @GetMapping("/{id}")
    public Member getMember(@PathVariable Long id) {
        return service.getMember(id);
    }

    // Update Member
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id,
                               @Valid @RequestBody Member member) {

        return service.updateMember(id, member);
    }

    // Delete Member
    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Long id) {

        service.deleteMember(id);

        return "Member Deleted Successfully";
    }
    @GetMapping("/name/{name}")
    public List<Member> searchByName(@PathVariable String name) {
        return service.searchByName(name);
    }
}