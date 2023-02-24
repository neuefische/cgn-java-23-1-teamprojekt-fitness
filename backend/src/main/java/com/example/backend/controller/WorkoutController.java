package com.example.backend.controller;

import com.example.backend.model.Workout;
import com.example.backend.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PutMapping("/workouts/{id}")
    public Workout updateWorkout(@PathVariable String id, @RequestBody Workout workoutToChange){
        if(!workoutToChange.id().equals(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Element with id " + id + " found.");
        }
        return workoutService.updateWorkout(id, workoutToChange);
    }
}
