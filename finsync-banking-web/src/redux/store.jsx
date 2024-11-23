import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./reducers/AuthSlice/authSlice";
import userReducer from "./reducers/userSlice/userSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    user: userReducer,
  },
});
