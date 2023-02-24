package com.example.backend.repo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.example.backend.model.Workout;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class WorkoutRepo {

    private final List<Workout> workoutList = new ArrayList<>();

    public List<Workout> listAllWorkouts() {
        return workoutList;
    }

    public Workout updateWorkout(String id, Workout workoutToChange) {
        for (Workout workout : workoutList) {
            if (workout.id().equals(id)) {
                workoutList.set(workoutList.indexOf(workout), workoutToChange);
            }
        }
        return workoutToChange;

    }

}
