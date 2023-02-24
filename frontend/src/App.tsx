import React, {useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {Workout} from "./model/Workout";
import axios from "axios";
import Gallery from "./component/Gallery";
import AddWorkout from "./component/AddWorkout";


function App() {

    const [workout, setWorkout] = useState<Workout[]>([])

    function fetchWorkouts() {
        axios.get("/api/workouts")
            .then(response => {
                setWorkout(response.data);
            })
            .catch(console.error);
    }

    useEffect(() => {
        fetchWorkouts()
    }, [])

    function addWorkout(workoutToAdd: Workout){
        axios.post("/api/workouts", workoutToAdd)
            .then(() => {
                fetchWorkouts();
            })
            .catch((error) => {
                console.error("I'm sorry. Something went wrong!" + error)
            });
    }

    return (
        <div className="App">

            <AddWorkout addWorkout={addWorkout}/>
            <Gallery workouts={workout}/>

        </div>
    );
}

export default App;
