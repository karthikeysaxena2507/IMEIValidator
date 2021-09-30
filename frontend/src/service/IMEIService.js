import axios from "axios";

const BACKEND_URL = "https://imei-validation.herokuapp.com/api";
const config = { headers: {"Content-Type": "text/plain"} };

const checkIMEICode = async(imeiCode) => {
    const response = await axios.post(BACKEND_URL, imeiCode, config);
    return response.data
}

export {
    checkIMEICode
}