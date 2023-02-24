package com.example.backend.service;

import com.example.backend.repo.WorkoutRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.example.backend.model.Workout;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@AllArgsConstructor
@Service
public class WorkoutService {


    private final WorkoutRepo workoutrepo;

    public List<Workout> listAllWorkouts() {
        return workoutrepo.listAllWorkouts();

    }

    public Workout getWorkoutById(String id) {
        return workoutrepo.getTodoItemById(id).orElseThrow(NoSuchElementException::new);
    }

    public void deleteWorkoutById(String id) {
        workoutrepo.deleteWorkoutById(id);
    }
}

