package com.example.backend.controller;

import com.example.backend.model.Workout;
import com.example.backend.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor

public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping("/workouts")
    public List<Workout> workoutList() {
        return workoutService.listAllWorkouts();
    }

    @PostMapping("/workouts")
    public Workout addWorkout(@RequestBody Workout workout) {
        return workoutService.addWorkout(workout);
    }

    @GetMapping("/workouts/{id}")
    Workout getWorkoutById(@PathVariable String id) {
        return workoutService.getWorkoutByID(id);
    }

    @DeleteMapping("workouts/{id}")
    void deleteWorkout(@PathVariable String id) {
        workoutService.deleteWorkoutById(id);
    }

    @PutMapping("/workouts/{id}")
    public Workout updateWorkout(@PathVariable String id, @RequestBody Workout workoutToChange) {
        return workoutService.updateWorkout(id, workoutToChange);
    }

}

