import React, {useState} from 'react'
import {Link, useHistory} from 'react-router-dom'
import axios from 'axios'

import '../App.css'

export default function SignUpPage() {
    const history = useHistory();

    const [userName, setUserName] = useState("");
    const [emailAddress, setEmailAddress] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [contactNumber, setContactNumber] = useState("");
    const [dateOfBirth, setDateOfBirth] = useState("");
    const [province, setProvince] = useState("");
    const [district, setDistrict] = useState("");
    const [town, setTown] = useState("");

    async function save(event)
    {
        console.log(userName);
        console.log(emailAddress);
        console.log(firstName);
        console.log(lastName);
        console.log(contactNumber);
        console.log(dateOfBirth);
        console.log(province);
        console.log(district);
        console.log(town);
        event.preventDefault();
        setUserName("");
        setEmailAddress ("")
        setFirstName("");
        setLastName("");
        setContactNumber("");
        setDateOfBirth("");
        setProvince("");
        setDistrict("") ;
        setTown ("");
        try
        {
            await axios.post("http://localhost:8088/api/v1/user/create",
                {

                    userName : userName,
                    email: emailAddress,
                    firstName : firstName,
                    lastName : lastName,
                    contactNo: contactNumber,
                    dateOfBirth: dateOfBirth,
                    province : province,
                    district: district,
                    town: town

                }).then((response) => {
                console.log(response.status);
                console.log('RESPONSE: ' + response.data.token);

                if (response.status === 200) {
                    alert("User Registration Successful.");

                    //redirect to home page
                    history.replace('/');
                } else {
                    //stay in the same page
                    alert("Failed to register user. Please try again");
                }
            });
        }
        catch(err)
        {
            alert("User Registration Failed");
        }
    }

    return (
        <div className="text-center m-5-auto">
            <h2>Join us</h2>
            <h5>Create your personal account</h5>
            <form action="/home">
                <p>
                    <label>UserName</label><br/>
                    <input type="text" name="userName" required
                           value={userName}
                           onChange={(event) =>
                           {
                               setUserName(event.target.value);
                           }}
                    />
                </p>
                <p>
                    <label>Email address</label><br/>
                    <input type="email" name="email" required
                           value={emailAddress}
                           onChange={(event) =>
                           {
                               setEmailAddress(event.target.value);
                           }}
                    />
                </p>
                <p>
                    <label>FirstName</label><br/>
                    <input type="text" name="first_name" required
                           value={firstName}
                           onChange={(event) =>
                           {
                               setFirstName(event.target.value);
                           }}
                    />
                </p>
                <p>
                    <label>LastName</label><br/>
                    <input type="text" name="last_name" required
                           value={lastName}
                           onChange={(event) =>
                           {
                               setLastName(event.target.value);
                           }}
                    />
                </p>
                <p>
                    <label>Contact Number</label><br/>
                    <input type="text" name="contact" required
                           value={contactNumber}
                           onChange={(event) =>
                           {
                               setContactNumber(event.target.value);
                           }}
                    />
                </p>
                <p>
                    <label>Date of Birth</label><br/>
                    <input type="date" name="bday" required
                           value={dateOfBirth}
                           onChange={(event) =>
                           {
                               setDateOfBirth(event.target.value);
                           }}
                    />
                </p>
                <p>
                    <label>Province</label><br/>
                    <input type="text" name="province" required
                           value={province}
                           onChange={(event) =>
                           {
                               setProvince(event.target.value);
                           }}
                    />
                </p>
                <p>
                    <label>District</label><br/>
                    <input type="text" name="district" required
                           value={district}
                           onChange={(event) =>
                           {
                               setDistrict(event.target.value);
                           }}
                    />
                </p>
                <p>
                    <label>Town</label><br/>
                    <input type="text" name="town" required
                           value={town}
                           onChange={(event) =>
                           {
                               setTown(event.target.value);
                           }}
                    />
                </p>

                <p>
                    <input type="checkbox" name="checkbox" id="checkbox" required /> <span>I agree all statements in <a href="https://google.com" target="_blank" rel="noopener noreferrer">terms of service</a></span>.
                </p>
                <p>
                    <button id="sub_btn" type="submit" onClick={save}>Register</button>
                </p>
            </form>
            <footer>
                <p><Link to="/">Back to Homepage</Link>.</p>
            </footer>
        </div>
    )

}
