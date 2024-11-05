import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./reducers/AuthSlice/authSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
  },
});
