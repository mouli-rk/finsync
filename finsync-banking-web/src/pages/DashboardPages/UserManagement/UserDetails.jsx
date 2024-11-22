// import { useState } from "react";
import SearchBar from "../../../components/DashboardComponents/search/SearchBar";
import { findByCode, findByName } from "../../../services/userServices";

const UserDetails = () => {
  return (
    <div className="px-3 py-3 flex flex-col gap-5">
      <div>
        <h1 className="text-2xl">Users Details</h1>
      </div>
      <div className="flex gap-4">
        <SearchBar placeholder="User code" len={2} url={findByCode} />
        <SearchBar placeholder="Name" len={3} url={findByName} />
      </div>
    </div>
  );
};

export default UserDetails;
