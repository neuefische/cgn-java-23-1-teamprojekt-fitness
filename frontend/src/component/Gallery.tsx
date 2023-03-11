import {Workout} from "../model/Workout";
import WorkoutCard from "./WorkoutCard";
import "./Gallery.css"
import useAuth from "../hooks/useAuth";
import Layout from "./Layout";

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
    return (
        <Layout>
            {!user ? <p>Loading...</p> : (
        <div className="Gallery-Workouts">
            {workouts}
        </div>
    )}
        </Layout>
    )
}