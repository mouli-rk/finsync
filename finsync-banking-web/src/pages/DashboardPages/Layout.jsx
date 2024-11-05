import { Outlet } from "react-router-dom";
import SideNav from "../../components/DashboardComponents/sideNav/SideNav";
import { useSelector } from "react-redux";
import {
  selectEmail,
  selectName,
} from "../../redux/reducers/AuthSlice/authSlice";

const Layout = () => {
  const userName = useSelector(selectName);
  const userEmail = useSelector(selectEmail);

  return (
    <div className="h-screen flex relative">
      <div className="w-[280px] h-[250px] lg:w-[500px] lg:h-[300px] bg-teal-600 absolute rounded-[50%] -bottom-[5rem] -right-20 md:top-1/2 lg:right-0 -translate-y-1/2 blur-[180px] lg:blur-[250px] z-0"></div>
      <div className="w-[200px] h-[100px] lg:w-[300px] lg:h-[200px] bg-teal-700 absolute rounded-[50%] top-0 left-0 blur-[100px] lg:blur-[200px] z-0"></div>

      <div className="h-full sticky top-0 p-4 transition-all duration-500 z-50">
        <SideNav userName={userName} userEmail={userEmail} />
      </div>
      <div className="w-full relative z-10 overflow-y-auto">
        <Outlet />
      </div>
    </div>
  );
};

export default Layout;
