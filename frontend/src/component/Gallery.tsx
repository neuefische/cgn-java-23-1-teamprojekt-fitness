import {Workout} from "../model/Workout";
import WorkoutCard from "./WorkoutCard";
import "./Gallery.css"


type GalleryProps = {

    workouts: Workout[]
    deleteWorkout: (workout: Workout) => void
}
//{props.todos.map(todo => <TodoCard key={todo.id} todo={todo} updateTodo={props.updateTodo}/>)}
export default function Gallery(props: GalleryProps) {
    const workouts = props.workouts
        .map((workout) => {
            return (
                <WorkoutCard workout={workout} key={workout.id} deletWorkout={props.deleteWorkout}/>)
        })
    return (

        <div className="Gallery-Workouts">
            {workouts}
        </div>
    )

}