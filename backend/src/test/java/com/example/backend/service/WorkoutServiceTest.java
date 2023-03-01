package com.example.backend.service;

import com.example.backend.model.Workout;
import com.example.backend.repo.WorkoutRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;




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
    void deleteWorkout() {
        // WHEN
        assertThrows(NoSuchElementException.class, () -> workoutService.deleteWorkoutById("1"));
    }
    @Test
    void getWorkoutById() {
        // GIVEN
        when(workoutRepo.getWorkoutById("1")).thenReturn(Optional.empty());

        // WHEN
        assertThrows(NoSuchElementException.class, () -> workoutService.getWorkoutById("1"));

        // THEN
        verify(workoutRepo).getWorkoutById("1");
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


