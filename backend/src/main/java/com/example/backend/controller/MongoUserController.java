package com.example.backend.controller;

import com.example.backend.model.MongoUser;
import com.example.backend.repo.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class MongoUserController {

    private final MongoUserRepository mongoUserRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public MongoUser create (@RequestBody MongoUser user) {
        if (user.username() == null || user.username().length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
        }

        if (user.password() == null || user.password().length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }

        if (mongoUserRepository.existsByUsername(user.username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User already exists"
            );
        }

        MongoUser newUser = new MongoUser(
                UUID.randomUUID().toString(),
                user.username(),
                passwordEncoder.encode(user.password()),
                "BASIC"
        );

        MongoUser out = mongoUserRepository.save(newUser);

        return new MongoUser(
                out.id(),
                out.username(),
                null,
                out.role()
        );
    }

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


    @GetMapping("/admin")
    public String getAdminStatus () {
        return "Admin OK";
    }
    @GetMapping
    public String getStatus() {
        return "OK";
    }




}
