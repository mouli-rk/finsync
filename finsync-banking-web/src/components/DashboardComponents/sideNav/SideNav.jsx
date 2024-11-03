import { FaCircleUser } from "react-icons/fa6";
// import { FaSyncAlt } from "react-icons/fa";
import { sideNavLinks } from "../../../data";
import styles from "./SideNav.module.css";
import { NavLink } from "react-router-dom";
import { BiSolidHelpCircle } from "react-icons/bi";
import { RiLogoutCircleLine } from "react-icons/ri";

const activeLink = ({ isActive }) => {
  return isActive ? `${styles.active} ${styles.link}` : `${styles.link}`;
};

const SideNav = () => {
  return (
    <div className="h-full flex flex-col justify-between sticky top-0 left-0 bg-teal-900 bg-opacity-20 backdrop-blur-md py-3 rounded-xl border border-teal-950 overflow-hidden">
      {/* <div className="logo py-2">
        <h1 className="flex items-center gap-1 text-2xl heading_font ">
          Fin
          <span className="text-teal-400">
            <FaSyncAlt />
          </span>
          Sync
        </h1>
      </div> */}

      <div className="top_section">
        <div className="relative px-4 flex justify-start items-center gap-3 pt-5 pb-8 box_border">
          <div className="user_img">
            <FaCircleUser size={52} className="text-teal-300" />
          </div>
          <div>
            <h1 className="heading_font text-[16px] text-teal-300 text-nowrap">
              Chandra Mouli
            </h1>
            <span className="text-[11px] font-normal">mouli@gmail.com</span>
          </div>
        </div>

        <div className="relative px-4 scroll_bar flex flex-col h-[300px] overflow-y-auto mt-5 gap-2">
          {sideNavLinks.map((navlink, i) => {
            return (
              <NavLink key={i} to={navlink.link} className={activeLink}>
                <span className="text-2xl text-teal-400">{navlink.icon}</span>
                <span className="font-light">{navlink.title}</span>
              </NavLink>
            );
          })}
        </div>
      </div>

      <div className="bottom_section py-3">
        <div className="px-4 scroll_bar flex flex-col overflow-y-auto">
          <NavLink to={"/"} className={styles.link} >
            <span className="text-2xl text-teal-400">
              <BiSolidHelpCircle />
            </span>
            <span className="font-light">Help</span>
          </NavLink>

          <NavLink to={"/"} className={styles.link}>
            <span className="text-2xl text-teal-400">
              <RiLogoutCircleLine />
            </span>
            <span className="font-light">Logout</span>
          </NavLink>
        </div>
      </div>
    </div>
  );
};

export default SideNav;
