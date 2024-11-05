import { Outlet } from "react-router-dom";
import Navbar from "../../components/HomepageComponents/Navbar/Navbar";

const Layout = () => {
  return (
    <div className="w-full h-screen relative">
      <div className="w-[280px] h-[250px] lg:w-[500px] lg:h-[300px] bg-teal-500 absolute rounded-[50%] -bottom-[5rem] -right-20 md:top-1/2 lg:right-0 -translate-y-1/2 blur-[180px] lg:blur-[250px] z-0"></div>
      <div className="w-[200px] h-[100px] lg:w-[300px] lg:h-[200px] bg-teal-700 absolute rounded-[50%] top-0 left-0 blur-[100px] lg:blur-[200px] z-0"></div>

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
