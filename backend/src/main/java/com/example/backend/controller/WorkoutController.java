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

    @GetMapping("/workout")
    public List<Workout> workoutList() {
        return workoutService.listAllWorkouts();

    }
    @DeleteMapping("workout/{id}")
    public Workout deleteWorkout(@PathVariable String id) {
        return workoutService.deleteWorkoutById(id);
    }
}
