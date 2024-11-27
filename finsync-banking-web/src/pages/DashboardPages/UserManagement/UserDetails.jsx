import { useDispatch, useSelector } from "react-redux";
import SearchBar from "../../../components/DashboardComponents/search/SearchBar";
import {
  findByCode,
  findByName,
  findUserById,
  getAllUsers,
} from "../../../services/userServices";
import {
  selectFilterdUser,
  selectUsers,
  SET_FILTER_USER,
  SET_USERS,
} from "../../../redux/reducers/userSlice/userSlice";
import RadioButton from "../../../components/DashboardComponents/RadioButton/RadioButton";
import { useEffect, useState } from "react";
import { selectjwtToken } from "../../../redux/reducers/AuthSlice/authSlice";

const initialData = {
  id: "",
  code: "",
  name: "",
  status: true,
};

const initialDropMenu = {
  code: false,
  name: false,
};

const UserDetails = () => {
  const [initval, setInitval] = useState(initialData);
  const [dropDown, setDropDown] = useState(initialDropMenu);
  const [checked, setChecked] = useState("Active");
  const filteredUser = useSelector(selectFilterdUser);
  const bearer = useSelector(selectjwtToken);
  const users = useSelector(selectUsers);
  const dispatch = useDispatch();

  const handleCheck = (e) => {
    const { value } = e.target;
    setChecked(() => value);
    setInitval({ ...initval, status: checked === "Active" ? false : true });
  };

  const handleClick = (userID, value, type) => {
    setInitval({ ...initval, [type]: value, id: userID });
    setDropDown({ ...dropDown, [type]: false });
  };

  const handleInput = async (e, url, len) => {
    const { value, name } = e.target;
    setInitval({ ...initval, [name]: value });
    if (value.length >= len) {
      setDropDown({ ...dropDown, [name]: true });
      const data = await url(value, bearer);
      dispatch(SET_USERS(data));
    } else {
      setDropDown({ ...dropDown, [name]: false });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = await findUserById(initval.status, initval.id, bearer);
    console.log(data);
    dispatch(SET_FILTER_USER(data));
    setInitval(initialData);
  };

  useEffect(() => {
    const getUsers = async () => {
      const userData = await getAllUsers(bearer);
      console.log(userData);
      dispatch(SET_FILTER_USER(userData));
    };
    getUsers();
  }, [bearer, dispatch]);

  return (
    <div className="px-3 py-3 flex flex-col gap-5">
      <div>
        <h1 className="text-2xl font-bold">User Details</h1>
      </div>
      <form
        className="flex flex-wrap rounded-xl justify-between gap-8 px-5 py-8 bg-gray-100"
        onSubmit={handleSubmit}
      >
        <div className="flex gap-5 flex-wrap flex-1">
          <div className="relative">
            <SearchBar
              name="code"
              placeholder="User code"
              len={2}
              url={findByCode}
              value={initval.code}
              setDropDown={setDropDown}
              handleInput={handleInput}
            />
            {dropDown.code ? (
              <div className="bg-gray-100 max-h-[200px] overflow-y-auto absolute w-full top-[40px]">
                {users &&
                  users?.map((user) => {
                    return (
                      <p
                        key={"user" + user?.id}
                        className="px-3 py-2 cursor-pointer hover:bg-gray-200 text-sm"
                        onClick={() => handleClick(user.id, user.code, "code")}
                      >
                        {user?.firstName}
                      </p>
                    );
                  })}
              </div>
            ) : (
              ""
            )}
          </div>

          <div className="relative">
            <SearchBar
              name="name"
              placeholder="Name"
              len={3}
              url={findByName}
              value={initval.name}
              setDropDown={setDropDown}
              handleInput={handleInput}
            />
            {dropDown?.name && (
              <div className="bg-gray-100 max-h-[200px] overflow-y-auto absolute w-full top-[40px]">
                {users.length ? (
                  users?.map((user) => {
                    return (
                      <p
                        key={"user" + user?.id}
                        className="px-3 py-2 cursor-pointer hover:bg-gray-200 text-sm"
                        onClick={() =>
                          handleClick(user.id, user.firstName, "name")
                        }
                      >
                        {user?.firstName}
                      </p>
                    );
                  })
                ) : (
                  <p className="px-3 py-2 cursor-pointer hover:bg-gray-200 text-sm">
                    No User Found
                  </p>
                )}
              </div>
            )}
          </div>
          <RadioButton
            label="Active"
            select={checked}
            handleCheck={handleCheck}
          />
          <RadioButton
            label="Inactive"
            select={checked}
            handleCheck={handleCheck}
          />
        </div>
        <div className="flex justify-end items-center">
          <button className="w-[80px] rounded-md py-1 bg-green-400 text-white font-medium">
            Show
          </button>
        </div>
      </form>
      <div className="w-full overflow-x-auto shadow-md">
        <table className="w-full">
          <thead className="bg-gray-200">
            <tr>
              <th className="text-left px-4 py-2">SNo.</th>
              <th className="text-left px-4 py-2">Image</th>
              <th className="text-left px-4 py-2">Code</th>
              <th className="text-left px-4 py-2">Name</th>
              <th className="text-left px-4 py-2">Status</th>
              <th className="text-left px-4 py-2">Location</th>
            </tr>
          </thead>
          <tbody>
            {filteredUser && filteredUser.length > 0 ? (
              filteredUser.map((user, index) => (
                <tr
                  key={user.code}
                  className="hover:bg-gray-100 cursor-pointer"
                >
                  <td className=" px-4 py-1">{index + 1}</td>
                  <td className=" px-4 py-1">
                    <img
                      src={user.image || "default-image.png"}
                      alt={user.name || "User"}
                      className="w-12 h-12 border rounded-full"
                    />
                  </td>
                  <td className=" px-4 py-1">{ user.code}</td>
                  <td className=" px-4 py-1">{user.firstName}</td>
                  <td className=" px-4 py-1">
                    <button
                      className={`px-4 py-1 w-[100px] ${
                        user.status ? "bg-green-500" : "bg-red-500"
                      }  rounded-md text-white`}
                    >
                      {user.status ? "ACTIVE" : "INACTIVE"}
                    </button>
                  </td>
                  <td className=" px-3 py-1">location</td>
                </tr>
              ))
            ) : (
              <tr>
                <td
                  colSpan="5"
                  className="text-center py-4 text-gray-500 italic"
                >
                  No users found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default UserDetails;
