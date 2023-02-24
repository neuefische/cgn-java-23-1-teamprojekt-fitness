package com.example.backend.controller;

import com.example.backend.model.Workout;
import com.example.backend.repo.WorkoutRepo;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class WorkoutControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WorkoutRepo workoutRepo;
    Workout workout1, workout2;

    @BeforeEach
    void setUp() {
        workout1 = new Workout("1", "trainieren", "Training");
        workout2 = new Workout("2", "joggen", "Joggen");

    }

    @Test
    @DirtiesContext
    void checkListAllWorkouts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/workout"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void checkUpdateWorkout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/workouts/" + workout1.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "id":"1", "description":"walken", "title":"Walken"
                                } 
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id":"1", "description":"walken", "title":"Walken"
                        }
                        """));
    }
}






