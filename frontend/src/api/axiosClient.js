import axios from "axios";

const axiosClient = axios.create({
  baseURL: "http://localhost:8080/api",
});

axiosClient.interceptors.response.use(
  (response) => {
    console.log("Backend Response:", response);
    return response;
  },
  (error) => {
    console.log("Backend Error:", error.response);
    return Promise.reject(error);
  }
);

export default axiosClient;