import React, {useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {Workout} from "./model/Workout";
import axios from "axios";
import Gallery from "./component/Gallery";
import UpdateWorkout from "./component/UpdateWorkout";


function App() {

    const [workout, setWorkout] = useState<Workout[]>([])

    function fetchWorkouts() {
        axios.get("/api/workout")
            .then(response => {
                setWorkout(response.data);
            })
            .catch(console.error);
    }

    useEffect(() => {
        fetchWorkouts()
    }, [])

    /*function updateWorkout(workout: Workout) {
        axios.put("api/workouts/" + workout.id, workout)
            .then(response => response.data)
            .then(data => setWorkout(prevState => {
                return prevState.map(currentWorkout => {
                    if (currentWorkout.id === workout.id) {
                        return data
                    }
                    return currentWorkout
                })
            }))
    } */

    function updateWorkout(item: Workout) {
        axios.put("/api/workouts/" + item.id, item)
            .then(fetchWorkouts)
            .catch(console.error);
    }

    return (
        <div className="App">

            <Gallery workouts={workout} updateWorkout={updateWorkout}/>



        </div>
    );
}

export default App;
