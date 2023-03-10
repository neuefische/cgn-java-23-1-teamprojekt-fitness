import {Workout} from "../model/Workout";
import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import "./UpdateWorkout.css"
import axios from "axios";


type UpdateWorkoutProps = {

    updateWorkout: (workoutToUpdate: Workout) => void

}

export default function UpdateWorkout(props: UpdateWorkoutProps) {

    const params = useParams()
    const id: string | undefined = params.id

    const [workout, setworkout] = useState<Workout | undefined>();

    const requestURL: string = "/api/workouts/" + id

    useEffect(() => {
        axios
            .get(requestURL)
            .then((response) => {
                setworkout(response.data);
                console.log(workout);
            })
            .catch((error) => console.error(error));
    }, [requestURL]);

    const [workoutToUpdate, setWorkoutToUpdate] = useState<Workout>({
        id: id ? id : "",
        description: "",
        title: "",
    });

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

    function onSave() {
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