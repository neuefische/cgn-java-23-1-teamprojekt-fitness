import {Workout} from "../model/Workout";
import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import axios from "axios";

type UpdateWorkoutProps = {

    updateWorkout: (workoutToUpdate: Workout) => Promise<void>


}

export default function UpdateWorkout(props: UpdateWorkoutProps) {

    const params1 = useParams();
    const id: string | undefined = params1.id;



    const [workoutToUpdate, setWorkoutToUpdate] = useState<Workout>({
        id: "",
        description: "",
        title: "",
    });

    function onChangeTitle(event: ChangeEvent<HTMLInputElement>) {
        setWorkoutToUpdate({
            ...workoutToUpdate,
            title: event.target.value
        })
    }

    function onChangeDescription(event: ChangeEvent<HTMLInputElement>){
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

    return(
        <form onSubmit={onSave}>
            <input type="text" placeholder="title" value={workoutToUpdate.title} onChange={onChangeTitle}/>
            <input type="text" placeholder="description" value={workoutToUpdate.description} onChange={onChangeDescription}/>
            <button>Update</button>
        </form>
    )


}