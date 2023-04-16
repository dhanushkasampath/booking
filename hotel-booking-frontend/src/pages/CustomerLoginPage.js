import React from 'react';
import {useState} from 'react';
import {Link, useHistory} from 'react-router-dom';
import '../App.css';
import axios from "axios";

export default function SignInPage() {
    const history = useHistory();

    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    async function userLogin(event) {
        event.preventDefault();
        setUserName("");
        setPassword("");
        try {
            await axios.post("http://localhost:8088/api/v1/user/login?userLoginType=GENERAL_LOGIN",
                {
                    userName: userName,
                    password: password
                }).then((response) => {
                console.log(response.status);
                console.log('RESPONSE: ' + response.data.token);

                if (response.status === 200) {
                    localStorage.setItem('token', response.data.token);

                    //redirect to home page
                    history.replace('/home');
                } else {
                    //stay in the same page
                    alert("Invalid Credentials. Please try again");
                }
            });
        } catch (err) {
            alert("Failed login. Please try again");
        }
    }

    return (
        <div className="text-center m-5-auto">
            <h2>Sign in to us</h2>
            <form action="/home">
                <p>
                    <label>Username</label><br/>
                    <input type="text" name="user_name" required
                           value={userName}
                           onChange={(event) => {
                               setUserName(event.target.value);
                           }}
                    />
                </p>
                <p>
                    <label>Password</label>
                    <Link to="/forget-password"><label className="right-label">Forget password?</label></Link>
                    <br/>
                    <input type="password" name="password" required
                           value={password}
                           onChange={(event) => {
                               setPassword(event.target.value);
                           }}
                    />
                </p>
                <p>
                    <button id="sub_btn" type="submit" onClick={userLogin}>Login</button>
                </p>
            </form>
            <footer>
                <p>First time? <Link to="/register">Create an account</Link>.</p>
                <p><Link to="/">Back to Homepage</Link>.</p>
            </footer>
        </div>
    )
}
