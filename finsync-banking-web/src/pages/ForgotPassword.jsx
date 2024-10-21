import { useState } from "react";
import { NavLink } from "react-router-dom";
import { MdEmail } from "react-icons/md";
import { toast } from "react-toastify";

const ForgotPassword = () => {
  const [email, setEmail] = useState("");

  const handleInputChange = (e) => {
    const mailId = e.target.value;
    setEmail(mailId);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (email) {
     console.log(email);
     setEmail("");
    } else {
      toast.error("please enter input");
    }
    
  };

  return (
    <div className="h-screen p-4 w-full flex justify-center items-center relative">
      <div className="absolute top-[50%] left-[50%] translate-x-[-50%] translate-y-[-50%] w-[300px] h-[380px] md:w-[500px] md:h-[500px] bg-blue-950 rounded-[100%] blur-[90px]"></div>

      <div className="form-container bg-zinc-700 backdrop-blur-md bg-opacity-30 w-full max-w-[450px] h-[350px] md:h-[500px] px-2 py-4 sm:px-10 sm:py-10 rounded-xl flex flex-col justify-center items-center ">
        <h1 className="text-3xl md:text-4xl font-normal mb-2 md:mb-5">
          For<span className="text-blue-500">go</span>t
        </h1>

        <form
          action=""
          method="post"
          onSubmit={handleSubmit}
          className="p-3 md:p-5 w-full flex flex-col gap-5"
        >
          <div className="w-full relative">
            <span className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-blue-500">
              <MdEmail />
            </span>
            <input
              type="email"
              name="email"
              placeholder="Email"
              id="email"
              onChange={handleInputChange}
              value={email}
              className="px-3 py-2 text-[18px] w-full bg-transparent outline-none border border-blue-500 bg-zinc-800 bg-opacity-25 rounded-md placeholder:text-gray-200"
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
            Submit
          </button>
        </form>
      </div>
    </div>
  );
};

export default ForgotPassword;
