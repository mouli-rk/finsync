import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  users: [],
  filteredUser: [],
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    SET_USERS(state, action) {
      state.users = action.payload.length
        ? action.payload.map((user) => user)
        : [];
    },
    SET_FILTER_USER(state, action) {
      const { users, id } = action.payload;
      state.filteredUser = users.filter((user) => user.id === id);
    },
  },
});

export const { SET_USERS, SET_FILTER_USER } = userSlice.actions;

export const selectUsers = (state) => state.user.users;
export const selectFilterdUser = (state) => state.user.filteredUser;

export default userSlice.reducer;
