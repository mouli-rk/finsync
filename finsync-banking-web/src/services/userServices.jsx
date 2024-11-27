import axios from "axios";
import { toast } from "react-toastify";

const BACKEND_URL = import.meta.env.VITE_BACKEND_URL;

// Get users by name
export const findByName = async (name, bearer) => {
  try {
    const users = await axios.get(
      `${BACKEND_URL}/api/user/findByAllName?name=${name}`,
      {
        headers: {
          Authorization: `Bearer ${bearer}`,
        },
      }
    );
    return users.data;
  } catch (error) {
    const message =
      (error.response && error.response.data && error.response.data.message) ||
      error.message ||
      error.toString();
    toast.error(message);
  }
};

// Get users by code
export const findByCode = async (code, bearer) => {
  try {
    const users = await axios.get(
      `${BACKEND_URL}/api/user/findByCode?code=${code}`,
      {
        headers: {
          Authorization: `Bearer ${bearer}`,
        },
      }
    );
    return users.data;
  } catch (error) {
    const message =
      (error.response && error.response.data && error.response.data.message) ||
      error.message ||
      error.toString();
    toast.error(message);
  }
};

// Get all Users
export const getAllUsers = async (bearer) => {
  try {
    const users = await axios.get(`${BACKEND_URL}/api/user/loadUserGrid`, {
      headers: {
        Authorization: `Bearer ${bearer}`,
      },
    });
    return users.data;
  } catch (error) {
    const message =
      (error.response && error.response.data && error.response.data.message) ||
      error.message ||
      error.toString();
    toast.error(message);
  }
};

// Find user by id and status
export const findUserById = async (status, id, bearer) => {
  try {
    const users = await axios.get(
      `${BACKEND_URL}/api/user/loadUserGrid?id=${id}&status=${status}
`,
      {
        headers: {
          Authorization: `Bearer ${bearer}`,
        },
      }
    );
    return users.data;
  } catch (error) {
    const message =
      (error.response && error.response.data && error.response.data.message) ||
      error.message ||
      error.toString();
    toast.error(message);
  }
};
