import { useDispatch, useSelector } from "react-redux";
import { selectjwtToken } from "../../../redux/reducers/AuthSlice/authSlice";
import { useState } from "react";
import {
  selectUsers,
  SET_FILTER_USER,
  SET_USERS,
} from "../../../redux/reducers/userSlice/userSlice";

const SearchBar = (params) => {
  const [initval, setInitval] = useState("");
  const bearer = useSelector(selectjwtToken);
  const users = useSelector(selectUsers);
  const dispatch = useDispatch();

  const handleInput = async (e) => {
    const { value } = e.target;
    setInitval(() => value);
    if (value.length >= params.len) {
      const data = await params.url(initval, bearer);
      dispatch(SET_USERS(data));
    }
  };

  const handleClick = (id) => {
    dispatch(SET_FILTER_USER({ users, id }));
    setInitval("")
  };

  return (
    <form action="" className="relative w-[250px]">
      <input
        type="text"
        value={initval}
        className="px-2 py-1 w-full rounded-md border-2 border-gray-300 outline-none"
        placeholder={params?.placeholder}
        onChange={handleInput}
      />
      {initval.length >= params?.len ? (
        <div className="bg-gray-100 h-[200px] overflow-y-auto absolute w-full top-[40px]">
          {users &&
            users?.map((user) => {
              return (
                <p
                  key={"user" + user?.id}
                  className="px-3 py-2 cursor-pointer hover:bg-gray-200 text-sm"
                  onClick={() => handleClick(user.id)}
                >
                  {user?.firstName}
                </p>
              );
            })}
        </div>
      ) : (
        ""
      )}
    </form>
  );
};

export default SearchBar;
