import { useSelector } from "react-redux";
import SearchBar from "../../../components/DashboardComponents/search/SearchBar";
import { findByCode, findByName } from "../../../services/userServices";
import { selectFilterdUser } from "../../../redux/reducers/userSlice/userSlice";

const UserDetails = () => {
  const filteredUser = useSelector(selectFilterdUser);

  return (
    <div className="px-3 py-3 flex flex-col gap-5">
      <div>
        <h1 className="text-2xl font-bold">User Details</h1>
      </div>
      <div className="flex gap-4">
        <SearchBar placeholder="User code" len={2} url={findByCode} />
        <SearchBar placeholder="Name" len={3} url={findByName} />
      </div>
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
                <tr key={user.code} className="hover:bg-gray-100">
                  <td className=" px-3 py-1">
                    {index + 1}
                  </td>
                  <td className=" px-3 py-1">
                    <img
                      src={user.image || "default-image.png"}
                      alt={user.name || "User"}
                      className="w-12 h-12 border rounded-full"
                    />
                  </td>
                  <td className=" px-3 py-1">3401</td>
                  <td className=" px-3 py-1">{ user.firstName}</td>
                  <td className=" px-3 py-1">
                    <button className="px-5 py-1 bg-green-500 rounded-md text-white">ACTIVE</button>
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
