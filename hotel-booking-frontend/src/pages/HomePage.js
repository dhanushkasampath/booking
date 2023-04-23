import React from 'react'
import {useHistory} from 'react-router-dom'

export default function HomePage() {

    const history = useHistory();

    async function userLogout(event) {
        event.preventDefault();
        localStorage.removeItem('token');
        //redirect to home page
        history.replace('/');
    }

    return (
        <div className="text-center">
            <h1 className="main-title home-page-title">welcome to our app</h1>
            <button className="primary-button" onClick={userLogout}>Log out</button>
        </div>
    )
}
