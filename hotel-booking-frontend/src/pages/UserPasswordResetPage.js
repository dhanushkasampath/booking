import React from 'react';
import {useHistory} from 'react-router-dom'
import {useState} from 'react';
import axios from 'axios';
import jwt from 'jwt-decode';

import '../App.css'

export default function ResetPassword() {
    const history = useHistory();

    const [enteredPassword, setEnteredPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    async function resetPassword(event) {
        event.preventDefault();
        setEnteredPassword("");
        setConfirmPassword("");
        const token = window.location.href
            .replace("http://localhost:3000/reset-password/", "")
            .split('?')[0];
        console.log(token);

        const decoded = jwt(token);
        console.log(decoded);

        const email = decoded.email;
        console.log(email);

        if (enteredPassword === confirmPassword) {
            console.log("Passwords match");
            console.log(window.location.href);
            try {
                await axios.post("http://localhost:8088/api/v1/user/login?userLoginType=FORGET_PASSWORD_LOGIN",
                    {
                        userName: email,
                        password: enteredPassword
                    }).then((response) => {
                    console.log(response.status);
                    console.log('RESPONSE: ' + response.data.token);

                    if (response.status === 200) {
                        alert("User Password Updated Successfully. Please login with new password.");

                        //redirect to home page
                        history.replace('/');
                    } else {
                        //stay in the same page
                        alert("Failed to update password. Please try again");
                    }
                });
            } catch (err) {
                alert("Failed to update password. Please try again");
            }
        } else {
            alert("Passwords do not match!");
        }
    }

    return (
        <div className="text-center m-5-auto">
            <h2>Please reset your password here!</h2>
            <form action="/login">
                <p>
                    <label>New Password</label><br/>
                    <input type="password" name="newPassword" required
                           value={enteredPassword}
                           onChange={(event) => {
                               setEnteredPassword(event.target.value);
                           }}/>
                </p>
                <p>
                    <label>Confirm Password</label>
                    <input type="password" name="confirmPassword" required
                           value={confirmPassword}
                           onChange={(event) => {
                               setConfirmPassword(event.target.value);
                           }}/>
                </p>
                <p>
                    <button id="sub_btn" type="submit" onClick={resetPassword}>Reset</button>
                </p>
            </form>
        </div>
    )
}
