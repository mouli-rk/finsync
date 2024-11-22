import { useSelector } from "react-redux";
import { selectjwtToken } from "../../../redux/reducers/AuthSlice/authSlice";
import { useState } from "react";

const SearchBar = (params) => {
  const [userData, setUserdata] = useState([]);
  const [initval, setInitval] = useState("");
  const bearer = useSelector(selectjwtToken);

  const handleInput = async (e) => {
    const { value } = e.target;
    setInitval(() => value);
    if (value.length >= params.len) {
      const data = await params.url(initval, bearer);
      setUserdata(() => data);
    }
  };

  return (
    <form action="" className="relative">
      <input
        type="text"
        value={initval}
        className="px-2 py-1 rounded-md border-2 border-gray-300 outline-none"
        placeholder={params.placeholder}
        onChange={handleInput}
      />
      {initval.length >= params.len ? (
        <div className="bg-gray-100 max-h-[200px] overflow-y-auto absolute w-full top-[40px]">
          {userData &&
            userData.map((user) => {
              return (
                <p key={"user" + user.id} className="px-3 py-2 text-sm">
                  {user.firstName}
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
