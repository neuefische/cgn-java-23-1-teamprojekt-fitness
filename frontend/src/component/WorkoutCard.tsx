import {Workout} from "../model/Workout";
import "./WorkoutCard.css";
import {Link} from "react-router-dom";

type WorkoutCardProps = {
    workout: Workout
    deleteWorkout: (id: string) => void
   //updateWorkout:(id:string)=> void
}

export default function WorkoutCard(props: WorkoutCardProps) {
    function handleDelete() {
        props.deleteWorkout(props.workout.id)

    }

    return (
        <div className={"workout-card"}>
            <h2>{props.workout.title}</h2>
            <p>{props.workout.description}</p>
            <p>{props.workout.id}</p>
            <Link to={"/workouts/" + props.workout.id}>Details</Link>
            <button className={"workout-delete"} onClick={handleDelete}>Delete this workout</button>
            <button className={"button-header"}><Link className={"link-header"} to={"/workouts/{id}"}>Update Workout</Link></button>

        </div>
    )
}
