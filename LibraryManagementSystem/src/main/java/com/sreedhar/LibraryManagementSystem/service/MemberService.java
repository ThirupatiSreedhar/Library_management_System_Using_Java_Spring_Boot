package com.sreedhar.LibraryManagementSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sreedhar.LibraryManagementSystem.entity.Member;
import com.sreedhar.LibraryManagementSystem.exception.MemberNotFoundException;
import com.sreedhar.LibraryManagementSystem.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }
    public List<Member> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    // Save Member
    public Member save(Member member) {
        return repository.save(member);
    }

    // Get All Members
    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    // Get Member By Id
    public Member getMember(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("Member Not Found"));
    }

    // Update Member
    public Member updateMember(Long id, Member member) {

        Member existing = repository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("Member Not Found"));

        existing.setName(member.getName());
        existing.setPhone(member.getPhone());
        existing.setEmail(member.getEmail());
        existing.setAddress(member.getAddress());

        return repository.save(existing);
    }

    // Delete Member
    public void deleteMember(Long id) {

        Member member = repository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("Member Not Found"));

        repository.delete(member);
    }
}