import axios from "axios";

const api = axios.create({
    baseURL: `${import.meta.env.VITE_DEV_BACK_END_URL}/api`,
    withCredentials: true,
});

export default api;