import { Outlet } from "react-router-dom";
import Navbar from "../../components/HomepageComponents/Navbar/Navbar";

const Layout = () => {
  return (
    <div className="w-full h-screen relative">
      <div className="navbar w-full bg-transparent backdrop-blur-md sticky top-0 left-0 z-50">
        <Navbar />
      </div>
      <div className="relative z-10">
        <Outlet />
      </div>
    </div>
  );
};

export default Layout;
