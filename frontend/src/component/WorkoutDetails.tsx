
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import "./WorkoutCard.css";
import {Workout} from "../model/Workout";


export default function DetailsWorkout() {

    const params = useParams();
    const id: string | undefined = params.id;

    const [details, setDetails] = useState<Workout | undefined>();

    const requestURL: string = "/api/workouts/" + id

    useEffect(() => {
        axios
            .get(requestURL)
            .then((response) => {
                setDetails(response.data);
                console.log(details);
            })
            .catch((error) => console.error(error));
    }, [requestURL]);


    if (!details) {
        return <h1>NO DATA</h1>;
    }

    return (
        <body className={"workout-detail-body"}>
        <div className={"workout-card-Details"}>
            <p>{details.title}</p>
            <p>{details.description}</p>
        </div>
        </body>
    )
}