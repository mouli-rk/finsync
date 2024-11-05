import { createSlice } from "@reduxjs/toolkit";

const user = JSON.parse(localStorage.getItem("Fyn_User"));
const authToken = JSON.parse(localStorage.getItem("Fyn_Auth_Token"));

const initialState = {
  isLoggedIn: authToken ? true : false,
  userId: user?.id ? user.id : "",
  name: user?.firstName ? user.firstName : "",
  jwtToken: authToken ? authToken : "",
  user: {
    email: user?.email ? user.email : "",
    firstName: "",
    lastName: "",
    fullName: "",
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
      const { id, firstName, email } = action.payload;
      state.name = firstName;
      state.user.email = email;
      state.userId = id;
    },
    SET_USER(state, action) {
      const { email, firstName, lastName, fullName, phoneNo } = action.payload;
      state.user.email = email;
      state.user.firstName = firstName;
      state.user.lastName = lastName;
      state.user.fullName = fullName;
      state.user.phoneNo = phoneNo;
    },
    SET_JWTTOKEN(state, action) {
      localStorage.setItem("Fyn_Auth_Token", JSON.stringify(action.payload));
      state.jwtToken = action.payload;
    },
  },
});

export const { SET_ISLOGGEDIN, SET_NAME, SET_USER, SET_JWTTOKEN } =
  authSlice.actions;

export const selectIsLoggedIn = (state) => state.auth.isLoggedIn;
export const selectName = (state) => state.auth.name;
export const selectJwtToken = (state) => state.auth.jwtToken;
export const selectEmail = (state) => state.auth.user.email;
export const selectUser = (state) => state.auth.user;

export default authSlice.reducer;
