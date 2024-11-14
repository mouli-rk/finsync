import { createSlice } from "@reduxjs/toolkit";
import Cookies from "js-cookie";

const user = JSON.parse(localStorage?.getItem("Fyn_User"));

const initialState = {
  isLoggedIn: Cookies.get("Bearer") ? true : false,
  name: user?.username ? user?.username : "",
  role: user?.role ? user?.role : "",
  user: {
    email: "",
    firstName: "",
    lastName: "",
    phoneNo: "",
  },
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    SET_ISLOGGEDIN(state, action) {
      state.isLoggedIn = action.payload;
    },
    SET_NAME(state, action) {
      localStorage.setItem("Fyn_User", JSON.stringify(action.payload));
      const { username, role } = action.payload;
      state.name = username;
      state.role = role;
    },
    SET_USER(state, action) {
      const { email, firstName, lastName, fullName, phoneNo } = action.payload;
      state.user.email = email;
      state.user.firstName = firstName;
      state.user.lastName = lastName;
      state.user.fullName = fullName;
      state.user.phoneNo = phoneNo;
    },
  },
});

export const { SET_ISLOGGEDIN, SET_NAME, SET_USER } = authSlice.actions;

export const selectIsLoggedIn = (state) => state.auth.isLoggedIn;
export const selectName = (state) => state.auth.name;
export const selectRole = (state) => state.auth.role;
export const selectUser = (state) => state.auth.user;

export default authSlice.reducer;
