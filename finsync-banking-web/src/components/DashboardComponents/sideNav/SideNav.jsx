/* eslint-disable react/prop-types */
import { FaCircleUser } from "react-icons/fa6";
// import { FaSyncAlt } from "react-icons/fa";
import { sideNavLinks } from "../../../data";
import styles from "./SideNav.module.css";
import { NavLink, useNavigate } from "react-router-dom";
import { BiSolidHelpCircle } from "react-icons/bi";
import { RiLogoutCircleLine } from "react-icons/ri";
import { MdKeyboardArrowLeft } from "react-icons/md";
import { useState } from "react";
import { toast } from "react-toastify";

const activeLink = ({ isActive }) => {
  return isActive ? `${styles.active} ${styles.link}` : `${styles.link}`;
};

const SideNav = ({ userName, userEmail }) => {
  const [show, setShow] = useState(false);
  const navigate = useNavigate();

  const handleLogout = async () => {
    localStorage.clear();
    toast.success("user Logged Out Successfully")
    navigate("/");
  };

  const emailshortener = (email) => {
    const shortedEmail =
      email.length < 20 ? email : email.slice(0, 20).concat("...");
    return shortedEmail;
  };

  return (
    <div
      className={`${
        show ? "w-[80px]" : "w-[230px]"
      } h-full flex flex-col justify-between relative bg-zinc-700 bg-opacity-30 backdrop-blur-sm py-3 rounded-xl border-2 border-zinc-700 transition-all duration-300`}
    >
      {/* <div className="logo py-2">
        <h1 className="flex items-center gap-1 text-2xl heading_font ">
          Fin
          <span className="text-teal-400">
            <FaSyncAlt />
          </span>
          Sync
        </h1>
      </div> */}

      <div
        className={`absolute cursor-pointer z-10 top-3 p-1 rounded-full -right-5 bg-zinc-700 text-zinc-200 text-[25px] ${
          show ? "rotate-180" : "rotate-0"
        }`}
        onClick={() => setShow(!show)}
      >
        <MdKeyboardArrowLeft />
      </div>

      <div className="top_section">
        <div
          className={`${styles.box_border} relative px-3 flex justify-start items-center gap-3 pt-5 pb-8 box_border transition-all duration-500`}
        >
          <div className="user_img z-30">
            <FaCircleUser size={52} className="text-teal-300" />
          </div>
          <div
            className={`${
              show ? "hidden" : "inline-block"
            } transition-all duration-500`}
          >
            <h1 className="heading_font text-[16px] text-teal-300 text-nowrap">
              {userName || "User Name"}
            </h1>
            <span className="text-[11px] font-normal">
              {emailshortener(userEmail || "User123@gmail.com")}
            </span>
          </div>
        </div>

        <div className="scroll_bar overflow-y-auto px-2 flex flex-col h-[300px] mt-5">
          {sideNavLinks.map((navlink, i) => {
            return (
              <NavLink key={i} to={navlink.link} className={activeLink}>
                <span className="text-[25px] text-teal-400">
                  {navlink.icon}
                </span>
                <div
                  className={`${
                    show
                      ? `absolute top-10 left-[80px] rounded-md px-4 py-2 bg-teal-500 hidden transition-all duration-500 ${styles.link_text} text-zinc-800 font-medium`
                      : "static"
                  } font-light flex`}
                >
                  {navlink.title}
                  {show && (
                    <div
                      className={`absolute top-1/2 -translate-y-1/2 left-0 w-7 h-7 bg-teal-500 rotate-45 -z-10`}
                    ></div>
                  )}
                </div>
              </NavLink>
            );
          })}
        </div>
      </div>

      <div className="px-2 flex flex-col h-[100px]">
        <div className="flex flex-col">
          <div className={`${styles.link} cursor-pointer`}>
            <span className="text-[25px] text-teal-400">
              <BiSolidHelpCircle />
            </span>
            <div
              className={`${
                show
                  ? `absolute top-10 left-[80px] rounded-md px-4 py-2 bg-teal-500 hidden transition-all duration-500 ${styles.link_text} text-zinc-800 font-medium`
                  : "static"
              } font-light flex`}
            >
              Help
              {show && (
                <div
                  className={`absolute top-1/2 -translate-y-1/2 left-0 w-7 h-7 bg-teal-500 rotate-45 -z-10`}
                ></div>
              )}
            </div>
          </div>

          <div
            className={`${styles.link} cursor-pointer`}
            onClick={handleLogout}
          >
            <span className="text-[25px] text-teal-400">
              <RiLogoutCircleLine />
            </span>
            <div
              className={`${
                show
                  ? `absolute top-10 left-[80px] rounded-md px-4 py-2 bg-teal-500 hidden transition-all duration-500 ${styles.link_text} text-zinc-800 font-medium`
                  : "static"
              } font-light flex`}
            >
              Logout
              {show && (
                <div
                  className={`absolute top-1/2 -translate-y-1/2 left-0 w-7 h-7 bg-teal-500 rotate-45 -z-10`}
                ></div>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SideNav;
