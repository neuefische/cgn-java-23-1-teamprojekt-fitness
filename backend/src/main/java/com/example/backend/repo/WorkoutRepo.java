package com.example.backend.repo;

import com.example.backend.model.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepo extends MongoRepository<Workout, String> {


}

