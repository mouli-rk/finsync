import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    isLoggedIn : false,
    userId : "",
    user : {
        name:"",
        email:"",
        firstName:"",
        lastName:"",
        fullName:"",
        phoneNo:""
    }
}

const authSlice = createSlice({
    name : "auth", 
    initialState, 
    reducers:{
        SET_ISLOGGEDIN(state, action){
            state.isLoggedIn = action.payload 
        },
        SET_USER(state, action){
            const{name, email, firstName, lastName, fullName, phoneNo} = action.payload
        }
    }
})