import React, { useState } from "react";
import { Spinner } from "react-bootstrap";
const imeiService = require("../service/IMEIService");

const Home = () => {

    const [message, setMessage] = useState("");
    const [imeiCode, setImeiCode] = useState("");
    const [loading, setLoading] = useState(false);

    const checkIMEICode = async() => {
        try {
            setLoading(true);
            const data = await imeiService.checkIMEICode(imeiCode);
            console.log(data);
            setMessage(data);
            setLoading(false);
        }
        catch(err) {
            console.log(err);
        }
    }

    return (
    <div className = "box">
        <div>
            <h1> IMEI Validator </h1>
        </div>
        <div>
            <input 
                type = "text"
                value = {imeiCode}
                onChange = {(e) => setImeiCode(e.target.value)}
                placeholder = "Enter IMEI Code"
            />
        </div>
        <div>
            <button onClick = {checkIMEICode}>
                Check
            </button>
        </div>
        <div style = {!loading ? {display: "none"}: null}>
            <Spinner animation="border" role="status">
                <p className="visually-hidden">Loading...</p>
            </Spinner>
        </div>
        <div>
            <p> {message} </p>
        </div>
    </div>);
}

export default Home;