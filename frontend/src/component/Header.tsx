import "./Header.css"
import {Link} from "react-router-dom";

export default function Header() {
    return (
        <header className={"app-header"}>
            <h1>Fitness App</h1>

            <img className={"img"}
                src={"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJggdsCjDB8RsGtl-8yt1epSsI36o-LuZoJw&usqp=CAU"} />
            <Link className={"link-header"} to={"/workouts/add"}>Add Workout</Link>
            <Link className={"link-header"} to={"/"}>Go back to Homepage</Link>

           

        </header>
    );
}