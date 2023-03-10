import React, {useEffect, useState} from 'react';
import './App.css';
import {Workout} from "./model/Workout";
import axios from "axios";
import Gallery from "./component/Gallery";
import Header from "./component/Header";
import AddWorkout from "./component/AddWorkout";
import {Route, Routes} from "react-router-dom";
import WorkoutDetails from "./component/WorkoutDetails";
import SignUpPage from "./component/SignUpPage";
import Cookies from "js-cookie";
import SignInPage from "./component/SignInPage";
import LogOut from "./component/Logout";



function App() {

    const [workout, setWorkout] = useState<Workout[]>([])

    axios.interceptors.request.use(function (config) {
        return fetch("/api/csrf").then(() => {
            config.headers["X-XSRF-TOKEN"] = Cookies.get("XSRF-TOKEN");
            return config;
        });
    }, function (error) {
        return Promise.reject(error);
    });

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
            .then((response) => {
                setWorkout([...workout, response.data])
            })
            .catch((error) => {
                console.error("I'm sorry. Something went wrong!" + error)
            });
    }

    return (
        <div className="App">

            <Header/>

            <Routes>
                <Route path={"/sign-in"} element={<SignInPage fetchWorkouts={fetchWorkouts}/> } />
                <Route path={"/sign-up"} element={<SignUpPage/>}/>
                <Route path={"/"} element={<Gallery workouts={workout} deleteWorkout={deleteWorkout}/>}/>
                <Route path={"/workouts/add"} element={<AddWorkout addWorkout={addWorkout}/>}/>
                <Route path={"/workouts/:id"} element={<WorkoutDetails/>}/>
                <Route path={"/logout"} element={<LogOut/>}/>
            </Routes>

        </div>
    );
}

export default App;
