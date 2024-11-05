import { IoMdLogIn, IoMdInformationCircle } from "react-icons/io";
import { FaUserEdit, FaUser, FaUsers } from "react-icons/fa";
import { IoHomeSharp, IoWallet } from "react-icons/io5";
import {
  MdOutlinePhoneIphone,
  MdSpaceDashboard,
  MdOutlineSecurity,
} from "react-icons/md";
import { FaPeopleArrows, FaLink } from "react-icons/fa6";
import { GiReceiveMoney } from "react-icons/gi";
import { BsGraphUpArrow, BsClipboard2DataFill } from "react-icons/bs";
import { AiFillDashboard } from "react-icons/ai";

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
    icon: <IoHomeSharp />,
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

export const sideNavLinks = [
  {
    title: "Dashboard",
    dropDown: [],
    link: "/dashboard",
    icon: <AiFillDashboard />,
  },
  {
    title: "Users",
    dropDown: [],
    link: "/dashboard/users",
    icon: <FaUser />,
  },
  {
    title: "Roles",
    dropDown: [],
    link: "/dashboard/roles",
    icon: <FaUsers />,
  },
  {
    title: "Transactions",
    dropDown: [],
    link: "/dashboard/transactions",
    icon: <FaPeopleArrows />,
  },
  {
    title: "Loans",
    dropDown: [],
    link: "/dashboard/loans",
    icon: <GiReceiveMoney />,
  },
  {
    title: "Investments",
    dropDown: [],
    link: "/dashboard/investments",
    icon: <BsGraphUpArrow />,
  },
  {
    title: "Payments",
    dropDown: [],
    link: "/dashboard/payments",
    icon: <IoWallet />,
  },
  {
    title: "CRM",
    dropDown: [],
    link: "/dashboard/crm",
    icon: <MdSpaceDashboard />,
  },
  {
    title: "Reports",
    dropDown: [],
    link: "/dashboard/reports",
    icon: <BsClipboard2DataFill />,
  },
  {
    title: "Security",
    dropDown: [],
    link: "/dashboard/security ",
    icon: <MdOutlineSecurity />,
  },
  {
    title: "Integrations",
    dropDown: [],
    link: "/dashboard/integrations",
    icon: <FaLink />,
  },
];
