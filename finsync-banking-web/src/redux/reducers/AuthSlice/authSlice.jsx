import { createSlice } from "@reduxjs/toolkit";
import Cookies from "js-cookie";

const user = JSON.parse(localStorage?.getItem("Fyn_User"));

const initialState = {
  isLoggedIn: Cookies.get("Bearer") ? true : false,
  name: user?.username ? user?.username : "",
  role: user?.role ? user?.role : "",
  jwtToken: user?.jwtToken ? user.jwtToken : "",
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
      const { username, role, jwtToken } = action.payload;
      state.name = username;
      state.role = role;
      state.jwtToken = jwtToken;
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
export const selectjwtToken = (state) => state.auth.jwtToken;

export default authSlice.reducer;
