// eslint-disable-next-line react/prop-types
const RadioButton = ({ label, select, handleCheck }) => {
  return (
    <>
      {/* {console.log(typeof select)} */}
      <label
        htmlFor={label}
        className="flex justify-center items-center gap-2 cursor-pointer"
      >
        <input
          type="radio"
          id={label}
          name="status"
          value={label}
          className="hidden"
          checked={select === label}
          onChange={handleCheck}
        />
        <span
          className={`${
            select === label ? "border-green-400" : "border-gray-400"
          } border-2 w-[20px] h-[20px] rounded-full flex justify-center items-center`}
        >
          <span
            className={`w-[12px] h-[12px] rounded-full ${
              select === label ? "bg-green-400" : "bg-gray-300"
            }`}
          ></span>
        </span>

        {label}
      </label>
    </>
  );
};

export default RadioButton;
