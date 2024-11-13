import { useState } from "react";
import { NavLink } from "react-router-dom";
import { MdEmail } from "react-icons/md";
import { FaSyncAlt } from "react-icons/fa";
import { toast } from "react-toastify";
import loginImg from "../../../assets/forgot1.png";
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
      className="h-screen bg-gray-300 p-4 w-full flex justify-center items-center relative"
      id="login_screen"
    >
      {/* <div className="absolute top-[50%] left-[50%] bg-blue-950 translate-x-[-50%] translate-y-[-50%] w-[300px] h-[380px] md:w-[500px] md:h-[500px] rounded-[100%] blur-[90px]"></div> */}

      <div className="form-container bg-zinc-400 bg-opacity-25 backdrop-blur-sm shadow-[0px_10px_15px] shadow-gray-400  px-2 py-4 sm:px-0 sm:py-0 overflow-hidden rounded-xl flex items-center justify-center z-10">
        <div className="image-container bg-gray-200 image-container relative p-10">
          <div className="login_logo text-black">
            <h1 className="heading_font text-4xl flex justify-center items-center font-bold">
              <span className="text-cyan-600 text_shadow_black border-l-4 border-black pl-2">
                Fin
              </span>
              <span className="inline-block mx-2 text-black">
                <FaSyncAlt />
              </span>
              <span className="heading_font text-gray-800">Sync</span>
            </h1>
          </div>
          <div className="image_1 w-[400px] relative top-[2rem]">
            <img
              src={loginImg}
              alt="girl with mobile"
              className="w-full scale-90"
            />
          </div>
          <div className="image_1 w-[200px] absolute bottom-0 -right-[5rem]">
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
            <h1 className="heading_font text-3xl text-center text-cyan-600">
              Forgot Password
            </h1>
          </div>

          <div className="w-full relative">
            <span className="icon absolute top-[50%] -translate-y-[50%] cursor-pointer right-5 text-xl text-gray-600">
              <MdEmail />
            </span>
            <input
              type="email"
              name="email"
              placeholder="Email"
              id="email"
              onChange={handleInputChange}
              value={email}
              className="px-5 py-2 text-[16px] w-full bg-gray-600 outline-none rounded-full bg-opacity-10 placeholder:text-gray-600"
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
            className="p-1 bg-cyan-500 hover:bg-cyan-600 transition-all duration-300 text-lg rounded-full outline-none mt-3"
          >
            Submit
          </button>
        </form>
      </div>
    </div>
  );
};

export default ForgotPassword;
