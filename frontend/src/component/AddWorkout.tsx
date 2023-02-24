import {Workout} from "../model/Workout";
import {ChangeEvent, useState} from "react";


type AddWorkoutProps = {
    addWorkout: (workoutToAdd: Workout) => void,
}

export default function AddWorkout(props: AddWorkoutProps){

    const [workoutToAdd, setWorkoutToAdd] = useState<Workout>({
        id: "",
        description: "",
        title: "",
    });

    function handleChangeDescription(event: ChangeEvent<HTMLInputElement>){
        setWorkoutToAdd({
            ...workoutToAdd,
            description: event.target.value,
        });
    }

    function handleChangeTitle(event: ChangeEvent<HTMLInputElement>){
        setWorkoutToAdd({
            ...workoutToAdd,
            title: event.target.value,
        });
    }

    function handleClickAddWorkout(){
        props.addWorkout(workoutToAdd);
        setWorkoutToAdd({
            ...workoutToAdd,
            id: "",
            title: "",
            description: "",
        })
    }

    return(
        <div>
            Title: <input value={workoutToAdd.title} onChange={handleChangeTitle} />
            Description: <input value={workoutToAdd.description} onChange={handleChangeDescription} />
            <button onClick={handleClickAddWorkout}>Add your Workout</button>
        </div>
    );
}