import React from 'react'
import { Link } from 'react-router-dom'


import '../App.css'

export default function SignUpPage() {

    return (
        // <div>
        <div className="text-center m-5-auto">
            <h2>Join us</h2>
            <h5>Create your personal account</h5>
            <form action="/home">
                <p>
                    <label>FirstName</label><br/>
                    <input type="text" name="first_name" required />
                </p>
                <p>
                    <label>LasttName</label><br/>
                    <input type="text" name="last_name" required />
                </p>
                <p>
                    <label>Contact Number</label><br/>
                    <input type="text" name="contact" required />
                </p>
                <p>
                    <label>Date of Birth</label><br/>
                    <input type="date" name="bday" required />
                </p>
                <p>
                    <label>Province</label><br/>
                    <input type="text" name="province" required />
                </p>
                <p>
                    <label>District</label><br/>
                    <input type="text" name="district" required />
                </p>
                <p>
                    <label>Town</label><br/>
                    <input type="text" name="town" required />
                </p>
                <p>
                    <label>Email address</label><br/>
                    <input type="email" name="email" required />
                </p>
                
                <p>
                    <input type="checkbox" name="checkbox" id="checkbox" required /> <span>I agree all statements in <a href="https://google.com" target="_blank" rel="noopener noreferrer">terms of service</a></span>.
                </p>
                <p>
                    <button id="sub_btn" type="submit">Register</button>
                </p>
            </form>
            <footer>
                <p><Link to="/">Back to Homepage</Link>.</p>
            </footer>
        </div>
    )

}
