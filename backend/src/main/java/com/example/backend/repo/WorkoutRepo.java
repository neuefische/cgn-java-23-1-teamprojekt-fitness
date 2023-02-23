package com.example.backend.repo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.example.backend.model.Workout;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class WorkoutRepo {

    private final List<Workout> workoutList = List.of(new Workout("Im Wald joggen mit anschließenden Dehnübungen", "1", "Joggen"),
            new Workout("Muskeln trainieren mit Hanteln", "2", "Muskeltraining"),
     new Workout("Muskeln trainieren mit Hanteln", "3", "Muskeltraining"));

    public List<Workout> listAllWorkouts(){
        return workoutList;
    }



}
