import {
  createBrowserRouter,
  RouterProvider,
  Route,
  createRoutesFromElements,
} from "react-router-dom";
import { Bounce, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { ForgotPassword, Home, Login, Register } from "./pages";
import Reset from "./pages/Reset";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      <Route path="/" element={<Home />} />
      <Route path="login" element={<Login />} />
      <Route path="register" element={<Register />} />
      <Route path="forgotpassword" element={<ForgotPassword />} />
      <Route path="reset" element={<Reset />} />
    </Route>
  )
);

function App() {
  return (
    <div className="bg-black min-h-screen relative text-white overflow-x-hidden">
      <div className="w-[280px] h-[250px] lg:w-[500px] lg:h-[300px] bg-blue-800 absolute rounded-[50%] -bottom-[5rem] -right-20 md:top-1/2 lg:right-0 -translate-y-1/2 blur-[180px] lg:blur-[250px] z-0"></div>

      <div className="w-[200px] h-[100px] lg:w-[300px] lg:h-[200px] bg-blue-800 absolute rounded-[50%] top-0 left-0 blur-[100px] lg:blur-[200px] z-0"></div>

      <ToastContainer theme="colored" transition={Bounce}/>

      <RouterProvider router={router} />
    </div>
  );
}

export default App;
