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
import java.util.Optional;


@AllArgsConstructor
@Service
public class WorkoutService {


    private final WorkoutRepo workoutrepo;
    private final IdGenerator idGenerator;

    public List<Workout> listAllWorkouts() {
        return workoutrepo.findAll();
    }

    public Workout addWorkout(Workout workout) {
        Workout workoutToAdd = new Workout(
                idGenerator.generateID(),
                workout.description(),
                workout.title()
        );
        return workoutrepo.save(workoutToAdd);
    }

    public Workout getWorkoutByID(String id) {
        return workoutrepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void deleteWorkoutById(String id) {
        Optional<Workout> workoutToDelete = workoutrepo.findById(id);
        if (workoutToDelete.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            workoutrepo.deleteById(id);

        }
       // return workoutToDelete.get();

    }
}

