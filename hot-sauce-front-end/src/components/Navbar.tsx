import { ShopName } from "./ShopName";
import { useNavigate } from "react-router-dom";
import { FaShoppingBasket } from "react-icons/fa";
import { MdAccountCircle } from "react-icons/md";
import { useEffect } from "react";

export const Navbar = () => {
  // variables
  const style = {
    li: "content-center flex justify-center items-center cursor-pointer ",
    icon: "flex justify-end items-center cursor-pointer",
    iconSize: 30,
  };
  const navigate = useNavigate();
  // functions
  // return element
  return (
    <>
      <ul className="bg-pepper p-3 shadow-custom-shadow grid grid-cols-8 gap-2">
        <li
          className="grid-cols-subgrid col-span-4"
          onClick={() => {
            navigate("/");
          }}
        >
          <ShopName />
        </li>
        <li
          className={style.li}
          onClick={() => {
            navigate("/");
          }}
        >
          Home
        </li>
        <li
          className={style.li}
          onClick={() => {
            navigate("/catalogue");
          }}
        >
          Catalogue
        </li>
        <li
          className={style.icon}
          onClick={() => {
            navigate("/cart");
          }}
        >
          <FaShoppingBasket size={style.iconSize} />
        </li>
        <li
          className={style.icon}
          onClick={() => {
            navigate("/account");
          }}
        >
          <MdAccountCircle size={style.iconSize} />
        </li>
      </ul>
    </>
  );
};
