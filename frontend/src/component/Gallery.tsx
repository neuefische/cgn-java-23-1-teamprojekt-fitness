import {Workout} from "../model/Workout";
import WorkoutCard from "./WorkoutCard";
import "./Gallery.css"


type GalleryProps = {

    workouts: Workout[]
}
export default function Gallery(props:GalleryProps){
       const workouts=props.workouts
       .map((i) =>{
           return(
            <WorkoutCard workout={i} key={i.id} />)})


return(

        <div className="Gallery-Workouts">
    {workouts}
    </div>
)
}