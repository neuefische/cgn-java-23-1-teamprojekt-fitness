package com.example.backend.controller;

import com.example.backend.model.MongoUser;
import com.example.backend.repo.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class MongoUserController {

    private final MongoUserRepository mongoUserRepository;

    @GetMapping("/me")
    public String getMe1(Principal principal) {
        if (principal != null) {
            return principal.getName();
        }
        return "AnonymousUser";
    }

    /*@GetMapping("/me2")
    public String getMe2() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
    */

    @PostMapping
    public MongoUser create (@RequestBody MongoUser mongoUser) {
        return mongoUserRepository.save(mongoUser);
    }



}
