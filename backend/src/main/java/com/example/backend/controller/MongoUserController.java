package com.example.backend.controller;

import com.example.backend.model.MongoUser;
import com.example.backend.repo.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class MongoUserController {

    private final MongoUserRepository mongoUserRepository;


    @PostMapping
    public MongoUser create (@RequestBody MongoUser mongoUser) {
        return mongoUserRepository.save(mongoUser);
    }



}
