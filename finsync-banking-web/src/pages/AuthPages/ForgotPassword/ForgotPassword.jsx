import { useState } from "react";
import { NavLink } from "react-router-dom";
import { MdEmail } from "react-icons/md";
import { FaSyncAlt } from "react-icons/fa";
import { toast } from "react-toastify";
import loginImg from "../../../assets/forgot2.png";
import plantImg from "../../../assets/plant1.png";

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
    <div
      className="h-screen p-4 w-full flex justify-center items-center relative"
      id="login_screen"
    >
      {/* <div className="absolute top-[50%] left-[50%] bg-blue-950 translate-x-[-50%] translate-y-[-50%] w-[300px] h-[380px] md:w-[500px] md:h-[500px] rounded-[100%] blur-[90px]"></div> */}

      <div className="form-container bg-black bg-opacity-25 backdrop-blur-sm shadow-[0px_0px_10px] shadow-teal-500  px-2 py-4 sm:px-0 sm:py-0 overflow-hidden rounded-xl flex items-center justify-center z-10">
        <div className="image-container bg-teal-400 bg-opacity-55 relative p-10">
          <div className="login_logo text-black">
            <h1 className="text-4xl heading_font flex justify-center items-center font-bold">
              Fin
              <span className="inline-block mx-2 text-teal-200">
                <FaSyncAlt />
              </span>
              Sync
            </h1>
          </div>
          <div className="image_1 w-[400px] relative top-[2rem]">
            <img src={loginImg} alt="girl with mobile" className="w-full " />
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
          <div className="mb-5">
            <h1 className="text-3xl heading_font text-center text-teal-400">
              Forgot Password
            </h1>
          </div>

          <div className="w-full relative">
            <span className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-teal-200">
              <MdEmail />
            </span>
            <input
              type="email"
              name="email"
              placeholder="Email"
              id="email"
              onChange={handleInputChange}
              value={email}
              className="px-5 py-3 text-[16px] w-full bg-gray-100 outline-none rounded-full bg-opacity-10 placeholder:text-gray-200"
              autoComplete="username"
            />
          </div>

          <div className="flex justify-between items-center">
            <NavLink to="/login" className={`text-md`}>
              -Login-
            </NavLink>
            <NavLink to="/" className={`text-md`}>
              -Home-
            </NavLink>
          </div>

          <button
            type="submit"
            className="p-1 pb-2 bg-teal-700 hover:bg-teal-400 transition-all duration-300 text-lg rounded-full outline-none mt-3"
          >
            Submit
          </button>
        </form>
      </div>
    </div>
  );
};

export default ForgotPassword;
