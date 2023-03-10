import React from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import Layout from "./Layout";
import AuthForm from "../component/AuthForm";
import useAuth from "../hooks/useAuth";
import App from "../App";

type SignInPageProps = {
    fetchWorkouts:() => void;

}

export default function SignInPage (props: SignInPageProps) {
    const navigate = useNavigate();
    const { setUsername, setPassword, username, password } = useAuth(true);

    const handleSignIn = (username: string, password: string) => {
        axios
            .post(
                "/api/user/login",
                {},
                {
                    headers: {
                        Authorization: `Basic ${window.btoa(`${username}:${password}`)}`,
                    },
                }
            )
            .then(() => {
                const redirect =
                    window.sessionStorage.getItem("signInRedirect") || "/";
                window.sessionStorage.removeItem("signInRedirect");
                navigate(redirect);
                props.fetchWorkouts();
            })
            .catch((err) => {
                alert(err.response.data.error);
            });
    };

    return (
        <Layout>
            <AuthForm
                title="Sign In"
                buttonText="Sign In"
                onSubmit={handleSignIn}
            />
        </Layout>
    );
}