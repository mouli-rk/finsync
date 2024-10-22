import { useState } from "react";
import { RiEyeFill } from "react-icons/ri";
import { RiEyeOffFill } from "react-icons/ri";
import { FaUser } from "react-icons/fa";
import { FaClipboardUser } from "react-icons/fa6";
import { NavLink } from "react-router-dom";
import { toast } from "react-toastify";
import axios from "axios";


const initialState = {
  //userrole: "",
  username: "",
  password: "",
};

const Login = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [loginDetails, setLoginDetails] = useState(initialState);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      loginDetails.password &&
      loginDetails.username
      //loginDetails.userrole
    ) {
      axios.post(`http://localhost:9001/api/auth/authenticate`, loginDetails).then((data)=>{console.log(data);})
      toast.success("successfully loggedin")
    } else {
      toast.error("please enter input");
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setLoginDetails({ ...loginDetails, [name]: value });
  };

  return (
    <div
      className="h-screen p-4 w-full flex justify-center items-center relative"
      id="login_screen"
    >
      <div className="absolute top-[50%] left-[50%] translate-x-[-50%] translate-y-[-50%] w-[300px] h-[380px] md:w-[500px] md:h-[500px] rounded-[100%] blur-[90px]"></div>

      <div className="form-container bg-zinc-700 backdrop-blur-sm shadow-[0px_0px_10px] shadow-zinc-200 bg-opacity-20 w-full max-w-[350px] h-[500px] px-2 py-4 sm:px-2 sm:py-10 rounded-xl flex flex-col items-center justify-center border-2 border-zinc-700">
        <h1 className="text-3xl md:text-4xl font-normal mb-2 md:mb-5 ">
          Lo<span className="text-blue-500">gi</span>n
        </h1>

        <form
          action=""
          method="post"
          className="p-3 md:p-5 w-full flex flex-col gap-5"
          onSubmit={(e) => handleSubmit(e)}
        >
          {/* <div className="w-full relative">
            <span className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-blue-500">
              <FaClipboardUser />
            </span>
            <select
              name="userrole"
              className="px-3 py-2 text-[18px] w-full bg-transparent outline-none border border-blue-500 bg-zinc-800 bg-opacity-25 rounded-md"
              autoComplete="username"
              onChange={handleInputChange}
            >
              <option value={""} selected disabled>
                Select Role
              </option>
              <option value="Admin" className="text-blue-500">
                Admin
              </option>
              <option value="Bank Manager" className="text-blue-500">
                Bank Manager
              </option>
              <option value="Customer" className="text-blue-500">
                Customer
              </option>
            </select>
          </div> */}

          <div className="w-full relative">
            <span className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-blue-500">
              <FaUser />
            </span>
            <input
              type="text"
              name="username"
              placeholder="User name"
              id="username"
              className="px-3 py-2 text-[18px] w-full bg-transparent outline-none border-l-[3px] border-blue-500 bg-zinc-700 bg-opacity-25 rounded-md placeholder:text-gray-200"
              autoComplete="username"
              onChange={handleInputChange}
            />
          </div>

          <div className="w-full relative">
            <span
              className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-blue-500"
              onClick={() => setShowPassword((prev) => !prev)}
            >
              {showPassword ? <RiEyeOffFill /> : <RiEyeFill />}
            </span>
            <input
              type={!showPassword ? "password" : "text"}
              name="password"
              placeholder="Password"
              id="password"
              className="px-3 py-2 text-[18px] w-full bg-transparent outline-none border-l-[3px] border-blue-500 bg-zinc-700 bg-opacity-25 rounded-md placeholder:text-gray-200"
              autoComplete="current-password"
              onChange={handleInputChange}
            />
          </div>

          <div className="flex justify-between items-center">
            <NavLink to="/forgotpassword" className={`text-md md:text-lg`}>
              -Forgot <span className="text-blue-400">Password?</span>-
            </NavLink>
            <NavLink to="/" className={`text-md md:text-lg`}>
              -Home-
            </NavLink>
          </div>

          <button
            type="submit"
            className="p-1 pb-2 bg-blue-900 text-lg rounded-md outline-none mt-5"
          >
            Login
          </button>

          <NavLink to="/register" className={`text-md md:text-lg w-full`}>
            <button
              type="submit"
              className="p-1 pb-2 bg-gray-200 text-black text-lg rounded-md outline-none w-full flex items-center justify-center gap-5"
            >
              {/* Do not have <span className="text-blue-400">Account?</span> */}
              <img
                src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTgiIGhlaWdodD0iMTgiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGcgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj48cGF0aCBkPSJNMTcuNiA5LjJsLS4xLTEuOEg5djMuNGg0LjhDMTMuNiAxMiAxMyAxMyAxMiAxMy42djIuMmgzYTguOCA4LjggMCAwIDAgMi42LTYuNnoiIGZpbGw9IiM0Mjg1RjQiIGZpbGwtcnVsZT0ibm9uemVybyIvPjxwYXRoIGQ9Ik05IDE4YzIuNCAwIDQuNS0uOCA2LTIuMmwtMy0yLjJhNS40IDUuNCAwIDAgMS04LTIuOUgxVjEzYTkgOSAwIDAgMCA4IDV6IiBmaWxsPSIjMzRBODUzIiBmaWxsLXJ1bGU9Im5vbnplcm8iLz48cGF0aCBkPSJNNCAxMC43YTUuNCA1LjQgMCAwIDEgMC0zLjRWNUgxYTkgOSAwIDAgMCAwIDhsMy0yLjN6IiBmaWxsPSIjRkJCQzA1IiBmaWxsLXJ1bGU9Im5vbnplcm8iLz48cGF0aCBkPSJNOSAzLjZjMS4zIDAgMi41LjQgMy40IDEuM0wxNSAyLjNBOSA5IDAgMCAwIDEgNWwzIDIuNGE1LjQgNS40IDAgMCAxIDUtMy43eiIgZmlsbD0iI0VBNDMzNSIgZmlsbC1ydWxlPSJub256ZXJvIi8+PHBhdGggZD0iTTAgMGgxOHYxOEgweiIvPjwvZz48L3N2Zz4="
                alt="Google-logo"
              />
              Login With Gmail
            </button>
          </NavLink>
        </form>
      </div>
    </div>
  );
};

export default Login;
