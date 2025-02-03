"use client"
import {login} from "./actions"
import Link from 'next/link'
import css from "./src/app/globals.css"

export default function Layout () {
    return (
        <>
            <div className="navbar">
                <h2 id='appName'>FinancialPlanner</h2>
                <Link href="http://localhost:3000/profile"><img src="https://www.dropbox.com/scl/fi/p3jwfys2i99m6ypluwfww/Profile.png?rlkey=r3lppn8mj0msj5ventzkjya83&raw=1" alt="Profile" id="profile"></img></Link>
                
            </div>
        </>
    );
}