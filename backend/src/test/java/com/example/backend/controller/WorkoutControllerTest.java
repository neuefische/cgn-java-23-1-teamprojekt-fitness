package com.example.backend.controller;

import com.example.backend.model.Workout;
import com.example.backend.repo.WorkoutRepo;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


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
        workout1 = new Workout(" Training ", "1", "Training");
        workout2 = new Workout("Schnell laufen", "2", "Joggen");

    }

  /*  @Test
    @DirtiesContext
   // void checkListAllWorkouts() throws Exception {
      //  mockMvc.perform(MockMvcRequestBuilders.get("api/Workout"))
              //  .andExpect(status().isOk())
               // .andExpect(content().json("[]"));
   // }

*/
}






