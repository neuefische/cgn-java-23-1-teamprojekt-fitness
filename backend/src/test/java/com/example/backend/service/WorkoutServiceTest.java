package com.example.backend.service;

import com.example.backend.model.Workout;
import com.example.backend.repo.WorkoutRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    Workout workout1;
    Workout workout2;

    @BeforeEach
    public void setUp() {
        workoutRepo = mock(WorkoutRepo.class);
        idGenerator = mock(IdGenerator.class);
        workoutService = new WorkoutService(workoutRepo, idGenerator);
        workout1 = new Workout("1", "Training", "Training");
        workout2 = new Workout("2", "Schnell laufen", "Joggen");

    }

    @Test
    void checkListAllWorkouts() {

        //GIVEN
        List<Workout> expectedWorkouts = new ArrayList<>();
        expectedWorkouts.add(new Workout("1", "Joggen gehen", "Joggen"));
        expectedWorkouts.add(new Workout("2", "mit Hanteln trainieren", "Muskeltraining"));
        when(workoutRepo.findAll()).thenReturn(expectedWorkouts);

        //WHEN
        List<Workout> Workouts = workoutService.listAllWorkouts();

        //THEN
        verify(workoutRepo).findAll();
        assertEquals(expectedWorkouts, Workouts);

    }

    @Test
    void deleteWorkout() {
        workoutRepo.save(workout1);
        when(workoutRepo.findById("1")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> workoutService.deleteWorkoutById("1"));
    }

    @Test
    void getWorkoutById() {
        // GIVEN
        when(workoutRepo.findById("1")).thenReturn(Optional.empty());

        // WHEN
        assertThrows(NoSuchElementException.class, () -> workoutService.getWorkoutByID("1"));

        // THEN
        verify(workoutRepo).findById("1");
    }

    @Test
    void checkAddWorkout() {

        //GIVEN
        Workout expectedWorkout = new Workout("1", "Joggen gehen", "Joggen");
        when(idGenerator.generateID()).thenReturn("1");
        when(workoutRepo.save(expectedWorkout)).thenReturn(expectedWorkout);


        //WHEN
        Workout workout = workoutService.addWorkout(expectedWorkout);

        //THEN
        verify(workoutRepo).save(expectedWorkout);
        assertEquals(expectedWorkout, workout);
    }
    @Test
    void checkUpdateWorkout(){
        //GIVEN
        when(workoutRepo.existsById(workout1.id())).thenReturn(true);
        when(workoutRepo.save(workout1)).thenReturn(workout1);

        //WHEN
        Workout actual = workoutService.updateWorkout(workout1.id(), workout1);
        Workout expected=workout1;

        //THEN
        verify(workoutRepo).save(workout1);
        verify(workoutRepo).existsById(workout1.id());
        assertEquals(expected,actual);
    }

    @Test
    void checkUpdateWorkoutWithNotExistingId(){
        //GIVEN
        when(workoutRepo.existsById(workout1.id())).thenReturn(false);

        //WHEN
        assertThrows(NoSuchElementException.class, () -> workoutService.updateWorkout(workout1.id(), workout1));

        //THEN
        verify(workoutRepo).existsById(workout1.id());
    }
}



