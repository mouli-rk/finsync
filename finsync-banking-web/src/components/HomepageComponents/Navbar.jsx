import { NavLink } from "react-router-dom";
import { navbarLinks } from "../../data";
import { HiDotsVertical } from "react-icons/hi";
import { useState } from "react";

// const activedLink = (isActive) => {
//   return isActive ? "bg-blue-500" : "";
// };

const Navbar = () => {
  const [showMenu, setShowMenu] = useState(false);

  const handleClick = () => {
    if (window.innerWidth < 1024) {
      setShowMenu(!showMenu);
      console.log(showMenu);
    }
  };

  return (
    <nav className="p-4 md:px-[2rem] md:py-5 lg:px-8 lg:py-4 xl:px-20 xl:py-6 flex justify-between items-center relative">

      <div className="logo">
        <NavLink to="/" className="text-2xl md:text-3xl font-bold">
          Fin<span className="text-[#3B82F6]">S</span>ync
        </NavLink>
      </div>

      <div className="nav-container relative">
        <div className="menu_icon lg:hidden" onClick={handleClick}>
          <HiDotsVertical />
        </div>
        <div
          className={`${
            showMenu ? "w-[200px]" : "w-[0px]"
          } rounded-lg bg-blue-900 bg-opacity-30 absolute top-10 right-0 transition-all duration-500 overflow-hidden lg:static lg:bg-transparent lg:w-full `}
        >
          <div className="p-3 gap-3 flex-col lg:p-0 flex lg:flex-row lg:gap-5">
            {navbarLinks.map((link, i) => {
              return (
                <NavLink
                  key={i}
                  to={link.url}
                  className={`px-3 lg:px-5 py-1 pb-2 rounded-md text-[18px] hover:text-blue-500 transition-all duration-300 flex items-center gap-2`}
                >
                  <span className="text-2xl text-blue-500">{link.icon}</span>
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
