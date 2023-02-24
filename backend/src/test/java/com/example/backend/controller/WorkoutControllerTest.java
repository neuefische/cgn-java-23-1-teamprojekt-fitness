package com.example.backend.controller;

import com.example.backend.model.Workout;
import com.example.backend.repo.WorkoutRepo;
import com.example.backend.service.WorkoutService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
    WorkoutService workoutService;
    Workout workout1, workout2;

    @BeforeEach
    void setUp() {
        workout1 = new Workout(" Training ", "1", "Training");
        workout2 = new Workout("Schnell laufen", "2", "Joggen");

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
    void getWorkoutById() throws Exception {
        // GIVEN
        workoutRepo.addWorkout(workout1);
        workoutRepo.addWorkout(workout2);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/workout/" + workout1.id()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "1",
                            "description": "Training",
                            "title": "Training"
                        }
                        """));

        // THEN
    }

    @Test
    @DirtiesContext
    void deleteTodoItem() throws Exception {
        // GIVEN
        workoutRepo.addWorkout(workout1);
        workoutRepo.addWorkout(workout2);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/" + workout1.id()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "1",
                            "description": "Training",
                            "title": "Training"
                        }
                        """));

        // THEN
    }

}






