import {Workout} from "../model/Workout";
import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import "./UpdateWorkout.css"
import axios from "axios";


type UpdateWorkoutProps = {
    workout:Workout[]
    updateWorkout: (workoutToUpdate: Workout) => void
}

export default function UpdateWorkout(props: UpdateWorkoutProps) {

    const params = useParams()
    const id: string | undefined = params.id

    const [workoutToUpdate, setWorkoutToUpdate] = useState<Workout>({
        id: id ? id : "",
        description: "",
        title: "",
    });

    const [workout, setWorkout] = useState<Workout | undefined>();

    const requestURL: string = "/api/workouts/" + id

     useEffect(() => {
        axios
            .get(requestURL)
            .then((response) => {
                setWorkout(response.data);
                console.log(workout);
            })
            .catch((error) => console.error(error));
    }, [requestURL]);

    function onChangeTitle(event: ChangeEvent<HTMLInputElement>) {
        setWorkoutToUpdate({
            ...workoutToUpdate,
            title: event.target.value
        })
    }

    function onChangeDescription(event: ChangeEvent<HTMLInputElement>) {
        setWorkoutToUpdate({
            ...workoutToUpdate,
            description: event.target.value
        })
    }

    function onSave(e: FormEvent<HTMLFormElement>) {
        e.preventDefault()
        props.updateWorkout(workoutToUpdate);
        setWorkoutToUpdate({
            ...workoutToUpdate,
            id: "",
            title: "",
            description: "",
        })
    }

    return (
        <form onSubmit={onSave}>
            <div className={"row"}>
                <input className={"text-input"} type="text" placeholder={workout?.title} value={workoutToUpdate.title} onChange={onChangeTitle}/>
                <input className={"large-input"} type="text" placeholder={workout?.description} value={workoutToUpdate.description}
                       onChange={onChangeDescription}/>
            </div>
            <button>Update</button>
        </form>

    )
}