import { Outlet } from "react-router-dom";
import Navbar from "../../components/HomepageComponents/Navbar/Navbar";

const Layout = () => {
  return (
    <div className="w-full flex flex-col justify-between relative">
      <div className="navbar w-full bg-transparent backdrop-blur-md sticky top-0 left-0 z-50">
        <Navbar />
      </div>
      <div className="">
        <Outlet />
      </div>
    </div>
  );
};

export default Layout;
