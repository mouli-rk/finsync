const SearchBar = (params) => {
  return (
    <div className="relative">
      <input
        type="text"
        name={params?.name}
        value={params?.value}
        className="px-2 py-1 w-full rounded-md border-2 border-gray-300 outline-none"
        placeholder={params?.placeholder}
        onChange={(e) => params.handleInput(e, params.url, params.len)}
      />
    </div>
  );
};

export default SearchBar;
