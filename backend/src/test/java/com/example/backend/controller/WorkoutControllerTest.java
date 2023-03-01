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
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class WorkoutControllerTest {
    WorkoutRepo workoutRepo;


    @Autowired
    MockMvc mockMvc;
    @Autowired

    ObjectMapper objectMapper;
    Workout workout1, workout2;

    @BeforeEach
    void setUp() {
        workout1 = new Workout("1", "Training", "Training");
        workout2 = new Workout("2", "Schnell laufen", "Joggen");

    }

    @Test
    @DirtiesContext
    void checkListAllWorkouts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/workouts"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext

    void getWorkoutById() throws Exception {
        // GIVEN
        // workoutRepo.addWorkout(workout1);
        // workoutRepo.addWorkout(workout2);
        workout1= new Workout("1", "Training", "Training");

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/" + workout1.id()))
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
    void deleteWorkout() throws Exception {
        // GIVEN
        // workoutRepo.addWorkout(workout1);
        // workoutRepo.addWorkout(workout2);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/workouts/" + "1"))
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

    void checkAddWorkout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/workouts").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                      
                        "description": "Mit dem Fahrrad fahren durch den Wald",
                        "title": "Fahrrad fahren"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                        {
                       
                        "description": "Mit dem Fahrrad fahren durch den Wald",
                        "title": "Fahrrad fahren"
                        }
                        """)
                )
                .andExpect(jsonPath("$.id").isNotEmpty());

    }
}
