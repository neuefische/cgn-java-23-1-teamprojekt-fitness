package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("workout")
public record Workout(

        @Id
        String id,
        String description,
        String title

) {
}
