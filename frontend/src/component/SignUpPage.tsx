
import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import Layout from "./Layout";
import useAuth from "../hooks/useAuth";



export default function SignUpPage () {
    const {username,password,setUsername,setPassword} =useAuth(true) ;
    const navigate = useNavigate();

    return (
        <Layout>
        <div style={{padding: "5rem 0"}}>

            <h1>Sign In</h1>

            <form onSubmit={e => {
                e.preventDefault();
                axios.post("/api/user", {
                    username,
                    password
                }).then(() => {
                    navigate("/sign-in");
                }).catch(err => {
                    alert(err.response.data.error);
                });
            }}>
                <div>
                    <label>
                        Username
                        <input
                            type="text"
                            value={username}
                            onChange={e => setUsername(e.currentTarget.value)}
                        />
                    </label>
                </div>

                <div>
                    <label>
                        Password
                        <input
                            type="password"
                            value={password}
                            onChange={e => setPassword(e.currentTarget.value)}
                        />
                    </label>
                </div>

                <button type="submit">Sign Up</button>
            </form>
        </div>
        </Layout>
    );
}