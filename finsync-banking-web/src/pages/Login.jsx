import { useState } from "react";
import { RiEyeFill } from "react-icons/ri";
import { RiEyeOffFill } from "react-icons/ri";
import { FaSyncAlt } from "react-icons/fa";
import { FaUser } from "react-icons/fa";
import { NavLink } from "react-router-dom";
import { toast } from "react-toastify";
import loginImg from "../assets/login3.png";
import plantImg from "../assets/plant1.png";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const initialState = {
  //userrole: "",
  username: "",
  password: "",
};

const Login = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [loginDetails, setLoginDetails] = useState(initialState);

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      loginDetails.password &&
      loginDetails.username
      //loginDetails.userrole
    ) {
      axios
        .post(`http://localhost:9001/api/auth/authenticate`, loginDetails)
        .then((data) => {
          console.log(data);
        });
      toast.success("successfully loggedin");
      navigate("/dashboard");
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
      {/* <div className="absolute top-[50%] left-[50%] bg-blue-950 translate-x-[-50%] translate-y-[-50%] w-[300px] h-[380px] md:w-[500px] md:h-[500px] rounded-[100%] blur-[90px]"></div> */}

      <div className="form-container bg-black bg-opacity-25 backdrop-blur-sm shadow-[0px_0px_10px] shadow-teal-500  px-2 py-4 sm:px-0 sm:py-0 overflow-hidden rounded-xl flex items-center justify-center z-10">
        <div className="image-container bg-teal-400 bg-opacity-55 relative p-10">
          <div className="login_logo text-black">
            <h1 className="text-4xl flex justify-center items-center font-bold">
              Fin
              <span className="inline-block mx-2 text-teal-200">
                <FaSyncAlt />
              </span>
              <span className="">Sync</span>
            </h1>
          </div>
          <div className="image_1 w-[400px] relative top-[2rem]">
            <img
              src={loginImg}
              alt="girl with mobile"
              className="w-full scale-[1.4]"
            />
          </div>
          <div className="image_1 w-[200px] absolute bottom-0 -right-[6rem]">
            <img src={plantImg} alt="girl with mobile" className="w-full" />
          </div>
        </div>

        <form
          action=""
          method="post"
          className="p-3 md:p-8 w-[400px] flex flex-col gap-5"
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
            <span className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-teal-200">
              <FaUser />
            </span>
            <input
              type="text"
              name="username"
              placeholder="User name"
              id="username"
              className="px-5 py-3 text-[16px] w-full bg-gray-100 outline-none rounded-full bg-opacity-10 placeholder:text-gray-200"
              autoComplete="username"
              onChange={handleInputChange}
            />
          </div>

          <div className="w-full relative">
            <span
              className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-teal-200"
              onClick={() => setShowPassword((prev) => !prev)}
            >
              {showPassword ? <RiEyeOffFill /> : <RiEyeFill />}
            </span>
            <input
              type={!showPassword ? "password" : "text"}
              name="password"
              placeholder="Password"
              id="password"
              className="px-5 py-3 text-[16px] w-full bg-gray-100 outline-none rounded-full bg-opacity-10 placeholder:text-gray-200"
              autoComplete="current-password"
              onChange={handleInputChange}
            />
          </div>

          <div className="flex justify-between items-center">
            <NavLink to="/forgotpassword" className={`text-md`}>
              -Forgot <span className="text-teal-200">Password?</span>-
            </NavLink>
            <NavLink to="/" className={`text-md`}>
              -Home-
            </NavLink>
          </div>

          <button
            type="submit"
            className="p-1 pb-2 bg-teal-700 hover:bg-teal-400 transition-all duration-300 text-lg rounded-full outline-none mt-3"
          >
            Login
          </button>

          <NavLink to="/register" className={`w-full`}>
            <button
              type="submit"
              className="p-1 pb-2 bg-gray-200 text-black text-[16px] font-semibold rounded-full outline-none w-full flex items-center justify-center gap-5 hover:bg-gradient-to-l from-red-500 to-green-400 via-yellow-400"
            >
              {/* Do not have <span className="text-blue-400">Account?</span> */}
              <img
                src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTgiIGhlaWdodD0iMTgiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGcgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj48cGF0aCBkPSJNMTcuNiA5LjJsLS4xLTEuOEg5djMuNGg0LjhDMTMuNiAxMiAxMyAxMyAxMiAxMy42djIuMmgzYTguOCA4LjggMCAwIDAgMi42LTYuNnoiIGZpbGw9IiM0Mjg1RjQiIGZpbGwtcnVsZT0ibm9uemVybyIvPjxwYXRoIGQ9Ik05IDE4YzIuNCAwIDQuNS0uOCA2LTIuMmwtMy0yLjJhNS40IDUuNCAwIDAgMS04LTIuOUgxVjEzYTkgOSAwIDAgMCA4IDV6IiBmaWxsPSIjMzRBODUzIiBmaWxsLXJ1bGU9Im5vbnplcm8iLz48cGF0aCBkPSJNNCAxMC43YTUuNCA1LjQgMCAwIDEgMC0zLjRWNUgxYTkgOSAwIDAgMCAwIDhsMy0yLjN6IiBmaWxsPSIjRkJCQzA1IiBmaWxsLXJ1bGU9Im5vbnplcm8iLz48cGF0aCBkPSJNOSAzLjZjMS4zIDAgMi41LjQgMy40IDEuM0wxNSAyLjNBOSA5IDAgMCAwIDEgNWwzIDIuNGE1LjQgNS40IDAgMCAxIDUtMy43eiIgZmlsbD0iI0VBNDMzNSIgZmlsbC1ydWxlPSJub256ZXJvIi8+PHBhdGggZD0iTTAgMGgxOHYxOEgweiIvPjwvZz48L3N2Zz4="
                alt="Google-logo"
              />
              Login with gmail
            </button>
          </NavLink>
        </form>
      </div>
    </div>
  );
};

export default Login;
