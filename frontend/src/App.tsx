import React, {useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {Workout} from "./model/Workout";
import axios from "axios";
import Gallery from "./component/Gallery";


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

    return (
        <div className="App">


            <Gallery workouts={workout}/>

        </div>
    );
}

export default App;
