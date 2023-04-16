import React from 'react';
import { Link } from 'react-router-dom'
import { useState } from 'react';
import axios from 'axios';
import jwt from 'jwt-decode';

import '../App.css'

export default function ResetPassword()  {

    const [enteredPassword, setEnteredPassword] = useState("");
    const[confirmPassword, setConfirmPassword] = useState("");

    async function resetPassword(event){
        event.preventDefault();
        console.log(enteredPassword);
        console.log(confirmPassword);

        const token = window.location.href
            .replace("http://localhost:3000/reset-password/","")
            .split('?')[0];
        console.log(token);

        const decoded = jwt(token);
        console.log(decoded);

        const email = decoded.email;
        console.log(email);

        if(enteredPassword === confirmPassword){
            alert("Passwords match");
            console.log(window.location.href); 
            try
            {
                await axios.post("http://localhost:8088/api/v1/user/login?userLoginType=FORGET_PASSWORD_LOGIN",
                {
                    userName : email,
                    password: enteredPassword
                });
                alert("User Password Updated Successfully. Please login with new password.");
                setEnteredPassword("");
                setConfirmPassword("");
            }
            catch(err)
            {
                alert("Failed to update password. Please try again");
            }
        }else{
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
                    onChange={(event) =>
                    {
                        setEnteredPassword(event.target.value);      
                    }}/>
                </p>
                <p>
                    <label>Confirm Password</label>                    
                    <input type="password" name="confirmPassword" required 
                    value={confirmPassword}
                    onChange={(event) =>
                    {
                        setConfirmPassword(event.target.value);      
                    }}/>
                </p>
                <p>
                    <Link to="/">
                        <button id="sub_btn" type="submit" onClick={resetPassword}>Reset</button>
                    </Link>
                </p>
            </form>            
        </div>
    )
}
