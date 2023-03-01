import {Workout} from "../model/Workout";
import "./WorkoutCard.css";

type WorkoutCardProps = {
    workout: Workout
    deleteWorkout: (id: string) => void
}

export default function WorkoutCard(props: WorkoutCardProps) {
    function handleDelete() {
        props.deleteWorkout(props.workout.id)

    }
    return (
        <div className={"workout-card"}>
            <h2>{props.workout.title}</h2>
            <p>{props.workout.description}</p>
            <button onClick={handleDelete}>delete this workout</button>
        </div>
    )
}
