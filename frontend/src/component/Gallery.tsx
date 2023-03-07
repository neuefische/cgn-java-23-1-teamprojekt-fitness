import {Workout} from "../model/Workout";
import WorkoutCard from "./WorkoutCard";
import "./Gallery.css"
import {useNavigate} from "react-router-dom";
import useAuth from "../hooks/useAuth";

type GalleryProps = {

    workouts: Workout[]
    deleteWorkout: (id: string) => void

}
export default function Gallery(props: GalleryProps) {
    const user = useAuth(true);
    const workouts = props.workouts
        .map((workout) => {
            return (
                <WorkoutCard workout={workout} key={workout.id} deleteWorkout={props.deleteWorkout}/>)
        })
    return !user ? null : (

        <div className="Gallery-Workouts">
            {workouts}
        </div>
    )


}