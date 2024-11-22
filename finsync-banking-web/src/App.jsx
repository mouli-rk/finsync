import {
  createBrowserRouter,
  RouterProvider,
  Route,
  createRoutesFromElements,
} from "react-router-dom";
import { Bounce, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import {
  About,
  Contact,
  Dashboard,
  DashboardLayout,
  ForgotPassword,
  Home,
  Layout,
  Login,
  Register,
  Reset,
  UserDetails,
} from "./pages/index";


const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="/About" element={<About />} />
        <Route path="/Contact" element={<Contact />} />
      </Route>
      <Route path="login" element={<Login />} />
      <Route path="register" element={<Register />} />
      <Route path="forgotpassword" element={<ForgotPassword />} />
      <Route path="reset" element={<Reset />} />
      <Route path="dashboard" element={<DashboardLayout />}>
        <Route index element={<Dashboard />} />
        <Route path="userDetails" element={ <UserDetails />} />
      </Route>
    </Route>
  )
);

function App() {
  return (
    <div className="overflow-x-hidden">
      <ToastContainer theme="colored" transition={Bounce} />

      <RouterProvider router={router} />
    </div>
  );
}

export default App;
