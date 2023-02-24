package com.example.backend.service;

import com.example.backend.model.Workout;
import com.example.backend.repo.WorkoutRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkoutServiceTest {
    WorkoutService workoutService;
    WorkoutRepo workoutRepo;
    IdGenerator idGenerator;

    @BeforeEach
    public void setUp() {
        workoutRepo = mock(WorkoutRepo.class);
        idGenerator = mock(IdGenerator.class);
        workoutService = new WorkoutService(workoutRepo, idGenerator);
    }

    @Test
    void checkListAllWorkouts() {

        //GIVEN
        List<Workout> expectedWorkouts = new ArrayList<>();
        expectedWorkouts.add(new Workout("1", "Joggen gehen", "Joggen"));
        expectedWorkouts.add(new Workout("2", "mit Hanteln trainieren", "Muskeltraining"));
        when(workoutRepo.listAllWorkouts()).thenReturn(expectedWorkouts);

        //WHEN
        List<Workout> Workouts = workoutService.listAllWorkouts();

        //THEN
        verify(workoutRepo).listAllWorkouts();
        assertEquals(expectedWorkouts, Workouts);

    }

    @Test
    void checkAddWorkout() {

        //GIVEN
        Workout expectedWorkout = new Workout("1", "Joggen gehen", "Joggen");
        when(idGenerator.generateID()).thenReturn("1");
        when(workoutRepo.addWorkout(expectedWorkout)).thenReturn(expectedWorkout);


        //WHEN
        Workout workout = workoutService.addWorkout(expectedWorkout);

        //THEN
        verify(workoutRepo).addWorkout(expectedWorkout);
        assertEquals(expectedWorkout, workout);
    }

}


