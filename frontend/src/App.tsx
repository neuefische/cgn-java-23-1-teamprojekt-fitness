import React, {useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {Workout} from "./model/Workout";
import axios from "axios";
import Gallery from "./component/Gallery";
import Header from "./component/Header";
import AddWorkout from "./component/AddWorkout";
import {Route, Routes} from "react-router-dom";


function App() {

    const [workout, setWorkout] = useState<Workout[]>([])

    function fetchWorkouts() {
        axios.get("/api/workouts")
            .then(response => {
                setWorkout(response.data);
            })
            .catch(console.error);
    }

    function deleteWorkout(id: string) {
        axios.delete("/api/workouts/" + id)
            .then(fetchWorkouts)
            .catch(console.error);
    }


    useEffect(() => {
        fetchWorkouts()
    }, [])

    function addWorkout(workoutToAdd: Workout) {
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
            <Header/>
            <Routes>
                <Route path={"/"} element={<Gallery workouts={workout} deleteWorkout={deleteWorkout}/>}/>
                <Route path={"/workouts/add"} element={<AddWorkout addWorkout={addWorkout} />}/>
            </Routes>

        </div>
    );
}

export default App;
