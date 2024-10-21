import { useState } from "react";
import { RiEyeFill } from "react-icons/ri";
import { RiEyeOffFill } from "react-icons/ri";
import { FaUser } from "react-icons/fa";
import { FaClipboardUser } from "react-icons/fa6";
import { NavLink } from "react-router-dom";
import { toast } from "react-toastify";

const initialState = {
  userrole: "",
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
      loginDetails.username &&
      loginDetails.userrole
    ) {
      console.log(loginDetails);
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
    <div className="h-screen p-4 w-full flex justify-center items-center relative">
      <div className="absolute top-[50%] left-[50%] translate-x-[-50%] translate-y-[-50%] w-[300px] h-[380px] md:w-[500px] md:h-[500px] bg-blue-950 rounded-[100%] blur-[90px]"></div>

      <div className="form-container bg-zinc-700 backdrop-blur-md bg-opacity-30 w-full max-w-[450px] px-2 py-4 sm:px-10 sm:py-10 rounded-xl flex flex-col items-center">
        <h1 className="text-3xl md:text-4xl font-normal mb-2 md:mb-5 ">
          Lo<span className="text-blue-500">gi</span>n
        </h1>

        <form
          action=""
          method="post"
          className="p-3 md:p-5 w-full flex flex-col gap-5"
          onSubmit={(e) => handleSubmit(e)}
        >
          <div className="w-full relative">
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
          </div>

          <div className="w-full relative">
            <span className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-blue-500">
              <FaUser />
            </span>
            <input
              type="text"
              name="username"
              placeholder="User name"
              id="username"
              className="px-3 py-2 text-[18px] w-full bg-transparent outline-none border border-blue-500 bg-zinc-800 bg-opacity-25 rounded-md placeholder:text-gray-200"
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
              className="px-3 py-2 text-[18px] w-full bg-transparent outline-none border border-blue-500 bg-zinc-800 bg-opacity-25 rounded-md placeholder:text-gray-200"
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

          {/* <NavLink to="/register" className={`text-md md:text-lg`}>
            <button type="submit" className="rounded-md outline-none w-full">
              Do not have <span className="text-blue-400">Account?</span>
            </button>
          </NavLink> */}
        </form>
      </div>
    </div>
  );
};

export default Login;
