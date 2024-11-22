import { IoMdLogIn, IoMdInformationCircle } from "react-icons/io";
import { FaUserEdit, FaShieldAlt } from "react-icons/fa";
import { IoMdHome } from "react-icons/io";
import { MdOutlinePhoneIphone } from "react-icons/md";
import { FaPeopleArrows } from "react-icons/fa6";
import { BsBank2, BsCreditCard2BackFill } from "react-icons/bs";
import { AiFillDashboard } from "react-icons/ai";
import { PiPiggyBankFill } from "react-icons/pi";

export const authLinks = [
  {
    title: "Login",
    icon: <IoMdLogIn />,
    url: "/login",
  },
  {
    title: "SignUp",
    icon: <FaUserEdit />,
    url: "/register",
  },
];

export const pageLinks = [
  {
    title: "Home",
    icon: <IoMdHome />,
    url: "/",
  },
  {
    title: "About",
    icon: <IoMdInformationCircle />,
    url: "/About",
  },
  {
    title: "Contact",
    icon: <MdOutlinePhoneIphone />,
    url: "/Contact",
  },
];

export const sideNavIcons = {
  Dashboard: <AiFillDashboard />,
  Authentication: <FaShieldAlt />,
  Bank: <BsBank2 />,
  Transactions: <FaPeopleArrows />,
  Accounts: <PiPiggyBankFill />,
  Cards: <BsCreditCard2BackFill />,
};
