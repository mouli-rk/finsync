import axios from "axios";
import { toast } from "react-toastify";

const BACKEND_URL = import.meta.env.VITE_BACKEND_URL;

export const validEmail = (email) => {
  return email.match(
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  );
};

// User Login Service
export const loginUser = async (userData) => {
  try {
    const response = await axios.post(
      `${BACKEND_URL}/api/auth/authenticate`,
      userData,
      {
        withCredentials: true, // Required to send cookies
      }
    );
    if (response.status === 200) {
      toast.success("User Login Success");
    }
    return response.data;
  } catch (error) {
    const message =
      (error.response && error.response.data && error.response.data.message) ||
      error.message ||
      error.toString();
    toast.error(message);
  }
};

export const logoutUser = async (bearer) => {
  try {
    const response = await axios.get(`${BACKEND_URL}/api/auth/logout`, {
      headers: {
        Authorization: `Bearer ${bearer}`,
      },
    });
    if (response.data.message) {
      toast.success(`Logout ${response.data.message}`);
    }
    return response.data;
  } catch (error) {
    const message =
      (error.response && error.response.data && error.response.data.message) ||
      error.message ||
      error.toString();
    toast.error(message);
  }
};
