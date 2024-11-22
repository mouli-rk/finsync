import { Outlet } from "react-router-dom";
import SideNav from "../../components/DashboardComponents/sideNav/SideNav";
import { useSelector } from "react-redux";
import {
  selectRole,
  selectName,
} from "../../redux/reducers/AuthSlice/authSlice";

const Layout = () => {
  const userName = useSelector(selectName);
  const userRole = useSelector(selectRole);

  return (
    <div className="h-screen flex relative ">

      <div className="h-full sticky top-0 transition-all duration-500 z-50">
        <SideNav userName={userName} userRole={userRole} />
      </div>
      <div className="w-full relative z-10 overflow-y-auto">
        <Outlet />
      </div>
    </div>
  );
};

export default Layout;
