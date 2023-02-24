import {Workout} from "../model/Workout";
import {ChangeEvent, FormEvent, useState} from "react";

type UpdateWorkoutProps = {

    updateWorkout: (workoutToUpdate: Workout) => void

}

export default function UpdateWorkout(props: UpdateWorkoutProps) {


    const[title, setTitle] = useState<string>("")
    const[description, setDescription] = useState<string>("")

    function onChangeTitle(event: ChangeEvent<HTMLInputElement>){
         setTitle(event.target.value)
    }

    function onChangeDescription(event: ChangeEvent<HTMLInputElement>){
        setDescription(event.target.value)
    }

    function onSave(){
        const updatedWorkout: Workout = {title, description, id:""}
        props.updateWorkout(updatedWorkout)

    }

    return(
        <form onSubmit={onSave}>
            <input type="text" placeholder="title" value={title} onChange={onChangeTitle}/>
            <input type="text" placeholder="description" value={description} onChange={onChangeDescription}/>
            <button>Update</button>
        </form>
    )


}