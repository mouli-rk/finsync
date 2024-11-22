import axios from "axios";
import { toast } from "react-toastify";

const BACKEND_URL = import.meta.env.VITE_BACKEND_URL;

// Accessign the menu from backend
export const menuAccess = async (username, role, bearer) => {
  try {
    console.log(bearer)
    const menu = await axios.get(
      `${BACKEND_URL}/system/module/findModulesByUsernameAndRole?username=${username}&role=${role}`,
      {
        headers: {
          Authorization: `Bearer ${bearer}`,
        },
      }
    );
    return menu.data;
  } catch (error) {
    const message =
      (error.response && error.response.data && error.response.data.message) ||
      error.message ||
      error.toString();
    toast.error(message);
  }
};
