import { ShopName } from "./ShopName";
import { useNavigate } from "react-router-dom";
import { FaShoppingBasket } from "react-icons/fa";
import { MdAccountCircle } from "react-icons/md";

export const Navbar = () => {
  const navigate = useNavigate();
  const baseStyle =
    "cursor-pointer text-right flex items-center font-bold hover:text-pepper hover:decoration-double hover:underline";
  return (
    <>
      <ul className="p-3 shadow-custom-shadow-pepper flex flex-row w-full">
        <li
          className={` basis-6/12`}
          onClick={() => {
            navigate("/");
          }}
        >
          <ShopName />
        </li>
        <li
          className={`${baseStyle} basis-2/12`}
          onClick={() => {
            navigate("/");
          }}
        >
          Home
        </li>
        <li
          className={`${baseStyle} basis-2/12`}
          onClick={() => {
            navigate("/catalogue");
          }}
        >
          Catalogue
        </li>
        <li
          className={`${baseStyle} basis-1/12 justify-end`}
          onClick={() => {
            navigate("/cart");
          }}
        >
          <FaShoppingBasket size={30} />
        </li>
        <li
          className={`${baseStyle} basis-1/12 px-2`}
          onClick={() => {
            navigate("/account");
          }}
        >
          <MdAccountCircle size={30} />
        </li>
      </ul>
    </>
  );
};
