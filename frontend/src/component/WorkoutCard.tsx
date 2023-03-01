import {Workout} from "../model/Workout";
import "./WorkoutCard.css";

type WorkoutCardProps = {
    workout: Workout
    deleteWorkout: (workout: Workout) => void
}
export default function WorkoutCard(props: WorkoutCardProps) {
    return (
        <div className={"workout-card"}>
            <h2>{props.workout.title}</h2>
            <p>{props.workout.description}</p>
        </div>
    )
}
