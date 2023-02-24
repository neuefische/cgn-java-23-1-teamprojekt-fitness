import {Workout} from "../model/Workout";
import "./WorkoutCard.css";
type WorkoutCardProps ={
    workout:Workout
}
export default function WorkoutCard(props:WorkoutCardProps){
    return(
        <div className={"workout-card"}>
            <h2>{props.workout.title}</h2>
            <h3>{props.workout.description}</h3>
        </div>
    )
}
