import axios from "axios";

const axiosClient = axios.create({
  baseURL: "http://localhost:8080/api",
});

// taking token from browser --------

axiosClient.interceptors.request.use(
  (config) => {
    try {
      const token = localStorage.getItem("token");
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
    } catch (error) {
      console.warn("localStorage not available");
    }

    return config;
  },
  (error) => Promise.reject(error)
);

axiosClient.interceptors.response.use(
  (response) => {
    console.log("Backend Response:", response);
    return response;
  },
  (error) => {
    console.log("Backend Error:", error.response);
    console.log("Backend Error:", error.response);
    if (error.response?.status === 401) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

export default axiosClient;