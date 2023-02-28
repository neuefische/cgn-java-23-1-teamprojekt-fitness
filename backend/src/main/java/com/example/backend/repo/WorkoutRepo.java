package com.example.backend.repo;

import com.example.backend.service.IdGenerator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.example.backend.model.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface  WorkoutRepo extends MongoRepository<Workout,String> {


    }

