import { useState } from "react";
import { NavLink } from "react-router-dom";
import { RiEyeFill } from "react-icons/ri";
import { RiEyeOffFill } from "react-icons/ri";
import { toast } from "react-toastify";

const initialState = {
  newpassword: "",
  confirmpassword: "",
};

const Reset = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [showCPassword, setShowCPassword] = useState(false);
  const [credintials, setCredintials] = useState(initialState);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCredintials({ ...credintials, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (credintials.newpassword === "" && credintials.confirmpassword === "") {
      return toast.error("please enter input");
    }

    if (credintials.newpassword === credintials.confirmpassword) {
      console.log(credintials);
      toast.success("password changed successfully");
      setCredintials(initialState);
    } else {
      toast.error("passwords do not match");
    }
  };

  return (
    <div className="h-screen p-4 w-full flex justify-center items-center relative">
      <div className="absolute top-[50%] left-[50%] translate-x-[-50%] translate-y-[-50%] w-[300px] h-[380px] md:w-[500px] md:h-[500px] bg-blue-950 rounded-[100%] blur-[90px]"></div>

      <div className="form-container bg-zinc-700 backdrop-blur-md bg-opacity-30 w-full max-w-[450px] h-[350px] md:h-[500px] px-2 py-4 sm:px-10 sm:py-10 rounded-xl flex flex-col justify-center items-center ">
        <h1 className="text-3xl md:text-4xl font-normal mb-2 md:mb-5">
          Re<span className="text-blue-500">se</span>t
        </h1>

        <form
          action=""
          method="post"
          onSubmit={handleSubmit}
          className="p-3 md:p-5 w-full flex flex-col gap-5"
        >
          <div className="w-full relative">
            <span
              className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-blue-500"
              onClick={() => setShowPassword((prev) => !prev)}
            >
              {showPassword ? <RiEyeOffFill /> : <RiEyeFill />}
            </span>
            <input
              type={!showPassword ? "password" : "text"}
              name="newpassword"
              placeholder="New Password"
              value={credintials.newpassword}
              className="px-3 py-2 text-[18px] w-full bg-transparent outline-none border border-blue-500 bg-zinc-800 bg-opacity-25 rounded-md placeholder:text-gray-200"
              autoComplete="current-password"
              onChange={handleInputChange}
            />
          </div>

          <div className="w-full relative">
            <span
              className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-blue-500"
              onClick={() => setShowCPassword((prev) => !prev)}
            >
              {showCPassword ? <RiEyeOffFill /> : <RiEyeFill />}
            </span>
            <input
              type={!showCPassword ? "password" : "text"}
              name="confirmpassword"
              placeholder="Confirm Password"
              value={credintials.confirmpassword}
              className="px-3 py-2 text-[18px] w-full bg-transparent outline-none border border-blue-500 bg-zinc-800 bg-opacity-25 rounded-md placeholder:text-gray-200"
              autoComplete="current-password"
              onChange={handleInputChange}
            />
          </div>

          <div className="flex justify-between items-center">
            <NavLink to="/login" className={`text-md md:text-lg`}>
              -Login-
            </NavLink>
            <NavLink to="/" className={`text-md md:text-lg`}>
              -Home-
            </NavLink>
          </div>

          <button
            type="submit"
            className="p-1 pb-2 bg-blue-900 text-lg rounded-md outline-none mt-5"
          >
            Reset
          </button>
        </form>
      </div>
    </div>
  );
};

export default Reset;
