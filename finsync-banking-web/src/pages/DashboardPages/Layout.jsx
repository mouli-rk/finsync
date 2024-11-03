import { Outlet } from "react-router-dom";
import SideNav from "../../components/DashboardComponents/sideNav/SideNav";

const Layout = () => {
 return (
   <div className="h-screen flex">
     <div className="w-full max-w-[300px] h-full sticky top-0 p-4">
       <SideNav />
     </div>
     <div className="w-full overflow-y-auto">
       <Outlet />
     </div>
   </div>
 );
};

export default Layout;
