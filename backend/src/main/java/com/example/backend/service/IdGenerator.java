package com.example.backend.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGenerator {

    public String generateID() {
        return UUID.randomUUID().toString();
    }
}
