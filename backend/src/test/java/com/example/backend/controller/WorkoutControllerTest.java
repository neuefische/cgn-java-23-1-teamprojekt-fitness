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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class WorkoutControllerTest {
    @Autowired
    WorkoutRepo workoutRepo;

    @Autowired
    MockMvc mockMvc;

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
        workoutRepo.save(workout1);
        workoutRepo.save(workout2);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/workouts/" + workout1.id()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {                    
                         "description": "Training",
                         "title": "Training"
                         }
                         """));
    }

    @Test
    @DirtiesContext
    void deleteWorkout() throws Exception {
        // GIVEN
        workoutRepo.save(workout1);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/workouts/" + workout1.id()))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
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

    @Test
    @DirtiesContext
    void checkUpdateWorkout() throws Exception {
        workoutRepo.save(workout1);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/workouts/" + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "id":"1", "description":"walken", "title":"walken"
                                } 
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id":"1", "description":"walken", "title":"walken"
                        }
                        """));
    }
}
