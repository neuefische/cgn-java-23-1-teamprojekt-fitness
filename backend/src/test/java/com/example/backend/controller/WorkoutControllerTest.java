package com.example.backend.controller;

import com.example.backend.model.Workout;
import com.example.backend.repo.WorkoutRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
    @WithMockUser(username = "user", password = "password")
    void checkListAllWorkouts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/workouts").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    @WithMockUser(username = "user", password = "password")
    void getWorkoutById() throws Exception {
        // GIVEN
        workoutRepo.save(workout1);
        workoutRepo.save(workout2);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/workouts/" + workout1.id()).with(csrf()))
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
    @WithMockUser(username = "user", password = "password")
    void deleteWorkout() throws Exception {
        // GIVEN
        workoutRepo.save(workout1);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/workouts/" + workout1.id()).with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    @WithMockUser(username = "user", password = "password")
    void checkAddWorkout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/workouts").with(csrf()).contentType(MediaType.APPLICATION_JSON)
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
    @WithMockUser(username = "user", password = "password")
    void checkUpdateWorkout() throws Exception {
        workoutRepo.save(workout1);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/workouts/" + "1")
                        .with(csrf())
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
