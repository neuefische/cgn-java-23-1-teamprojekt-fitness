package com.example.backend.service;

import com.example.backend.repo.WorkoutRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.example.backend.model.Workout;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class WorkoutService {


    private final WorkoutRepo workoutrepo;

    public List<Workout> listAllWorkouts() {
        return workoutrepo.listAllWorkouts();

    }

    public Workout updateWorkout(String id, Workout workoutToChange){
        return workoutrepo.updateWorkout(id,workoutToChange);
    }
}
