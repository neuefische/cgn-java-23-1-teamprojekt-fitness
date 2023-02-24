package com.example.backend.repo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.example.backend.model.Workout;
import org.springframework.stereotype.Repository;


import java.util.Map;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WorkoutRepo {
    private final Map<String, Workout> workoutMapMap;
    private final List<Workout> workoutList = new ArrayList<>();

    public List<Workout> listAllWorkouts() {
        return workoutList;
    }
    public Optional<Workout> getTodoItemById(String id) {
        return Optional.ofNullable(workoutMapMap.get(id));
    }
    public void deleteWorkoutById(String id) {
        workoutMapMap.remove(id);
    }
}
