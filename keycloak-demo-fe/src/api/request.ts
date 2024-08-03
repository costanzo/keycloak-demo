import process from "process";
import axios from "axios";

const service = axios.create({
  // baseURL: process.env.NEXT_PUBLIC_API_BASE_URL,
  baseURL: 'http://127.0.0.1:8089',
  timeout: 60000,
  withCredentials: true
})

export default service