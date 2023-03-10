import {Workout} from "../model/Workout";
import {ChangeEvent, useState} from "react";
import "./UpdateWorkout.css";

type AddWorkoutProps = {
    addWorkout: (workoutToAdd: Workout) => void,
}

export default function AddWorkout(props: AddWorkoutProps) {

    const [workoutToAdd, setWorkoutToAdd] = useState<Workout>({
        id: "",
        description: "",
        title: "",
    });

    function handleChangeDescription(event: ChangeEvent<HTMLInputElement>) {
        setWorkoutToAdd({
            ...workoutToAdd,
            description: event.target.value,
        });
    }

    function handleChangeTitle(event: ChangeEvent<HTMLInputElement>) {
        setWorkoutToAdd({
            ...workoutToAdd,
            title: event.target.value,
        });
    }

    function handleClickAddWorkout() {
        props.addWorkout(workoutToAdd);
        setWorkoutToAdd({
            ...workoutToAdd,
            id: "",
            title: "",
            description: "",
        })
    }

    return (
        <div className={"row"}>
            <input className={"text-input"} value={workoutToAdd.title} onChange={handleChangeTitle} placeholder={"title"}/>
            <input className={"large-input"} value={workoutToAdd.description} onChange={handleChangeDescription} placeholder={"description"}/>
            <button onClick={handleClickAddWorkout}>Add your Workout</button>
        </div>
    );
}