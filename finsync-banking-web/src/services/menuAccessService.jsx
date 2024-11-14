import axios from "axios";
import { toast } from "react-toastify";

const BACKEND_URL = import.meta.env.VITE_BACKEND_URL;

// Accessign the menu from backend
export const menuAccess = async (role) => {
  try {
    const menu = await axios.get(
      `${BACKEND_URL}/system/module/fetchModulesByRoleType?role=${role}`,
    );
    console.log(menu.data)
    return menu.data.map((item) => item.module);
  } catch (error) {
    const message =
      (error.response && error.response.data && error.response.data.message) ||
      error.message ||
      error.toString();
    toast.error(message);
  }
};
