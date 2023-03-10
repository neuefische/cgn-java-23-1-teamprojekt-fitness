import React, {ReactNode} from "react";
import {Link, useLocation} from "react-router-dom";
import LogOut from "./Logout";

type Props = {
    children: ReactNode;
}

export default function Layout ({children}: Props) {
    const location = useLocation();

    return (
        <div>
            {!["/sign-in", "/sign-up"].includes(location.pathname) && (
                <div>
                    <Link to={"/sign-up"}>Sign Up</Link> &nbsp; <Link to={"/sign-in"}>Sign In</Link> &nbsp; <LogOut/>
                </div>
            )}
            <div>
                {children}
            </div>
        </div>
    )
}