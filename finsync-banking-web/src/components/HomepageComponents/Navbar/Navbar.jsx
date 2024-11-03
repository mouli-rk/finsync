import { Link, NavLink } from "react-router-dom";
import { authLinks, pageLinks } from "../../../data";
import { HiDotsVertical } from "react-icons/hi";
import { useState } from "react";
import styles from "./Navbar.module.css";

const activedLink = ({ isActive }) => {
  return isActive ? `${styles.active} ${styles.link}` : `${styles.link}`;
};

const Navbar = () => {
  const [showMenu, setShowMenu] = useState(false);

  const handleClick = () => {
    if (window.innerWidth < 1024) {
      setShowMenu(!showMenu);
      console.log(showMenu);
    }
  };

  return (
    <nav className="p-4 md:px-[2rem] md:py-5 lg:px-8 lg:py-4 xl:px-20 xl:py-6 flex  items-center justify-between">
      <div className="logo w-1/2">
        <Link to="/" className="text-2xl md:text-3xl font-bold heading_font">
          Fin<span className="text-teal-500 heading_font">S</span>ync
        </Link>
      </div>

      <div className="nav-container relative lg:w-full">
        <div className="menu_icon lg:hidden" onClick={handleClick}>
          <HiDotsVertical />
        </div>

        <div
          className={`${
            showMenu ? "w-[200px]" : "w-[0px]"
          } rounded-lg bg-teal-600 bg-opacity-60 absolute top-10 right-0 transition-all duration-500 overflow-hidden lg:static lg:bg-transparent lg:w-full lg:flex justify-between`}
        >
          {/* Page Links */}
          <div className="px-3 pt-3 gap-2 flex-col lg:p-0 flex lg:flex-row lg:gap-5">
            {pageLinks.map((link, i) => {
              return (
                <NavLink key={i} to={link.url} className={activedLink}>
                  <span className="text-2xl text-teal-400">{link.icon}</span>
                  {link.title}
                </NavLink>
              );
            })}
          </div>

          {/* Authentication pages links */}
          <div className="p-3 gap-2 flex-col lg:p-0 flex lg:flex-row lg:gap-5">
            {authLinks.map((link, i) => {
              return (
                <NavLink key={i} to={link.url} className={activedLink}>
                  <span className="text-2xl text-teal-400">{link.icon}</span>
                  {link.title}
                </NavLink>
              );
            })}
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
