package com.example.backend.controller;

import com.example.backend.model.Workout;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class WorkoutControllerTest {

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

    @DirtiesContext
    @Test
    void expectWorkoutOnGetById() throws Exception {
        String actual = mockMvc.perform(
                        post("http://localhost:8080/api/workouts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                    {
                                        "description": "Training",
                                        "title": "Training"}
                                                    """)
                )
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                                               
                            "description": "Training",
                            "title": "Training"
                        }
                        """))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Workout actualTodo = objectMapper.readValue(actual, Workout.class);
        String id = actualTodo.id();

        mockMvc.perform(get("http://localhost:8080/api/workouts/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "id": "<ID>",
                          "description": "Training",
                          "title": "Training"
                        }
                        """.replaceFirst("<ID>", id)));
    }

    @DirtiesContext
    @Test
    void expectSuccessfulDelete() throws Exception {
        String saveResult = mockMvc.perform(
                        post("http://localhost:8080/api/workouts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                    {"description": "Training",
                                        "title": "Training"}
                                                    """)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Workout saveResultTodo = objectMapper.readValue(saveResult, Workout.class);
        String id = saveResultTodo.id();

        mockMvc.perform(delete("http://localhost:8080/api/workouts/" + id))
                .andExpect(status().isOk());

        mockMvc.perform(get("http://localhost:8080/api/workouts"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                        """));
    }

}






