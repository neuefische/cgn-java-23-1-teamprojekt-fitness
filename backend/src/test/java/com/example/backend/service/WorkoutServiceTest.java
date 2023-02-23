package com.example.backend.service;

import com.example.backend.model.Workout;
import com.example.backend.repo.WorkoutRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkoutServiceTest {
    WorkoutService workoutService;
    WorkoutRepo workoutRepo;

    @BeforeEach
    public void setUp() {
        workoutRepo = mock(WorkoutRepo.class);
        workoutService = new WorkoutService(workoutRepo);
    }

    @Test
    void checkListAllWorkouts() {
        //GIVEN
        List<Workout> expectedWorkouts = new ArrayList<>();
        expectedWorkouts.add(new Workout("Joggen gehen", "1", "Joggen"));
        expectedWorkouts.add(new Workout("mit Hanteln trainieren", "2", "Muskeltraining"));
        when(workoutRepo.listAllWorkouts()).thenReturn(expectedWorkouts);

        //WHEN
        List<Workout> Workouts =workoutService.listAllWorkouts();

        //THEN
        verify(workoutRepo).listAllWorkouts();
        assertEquals(expectedWorkouts, Workouts);

    }

    }
}