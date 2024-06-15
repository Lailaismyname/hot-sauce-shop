import { BsCartPlusFill } from "react-icons/bs";
import { IoLogoEuro } from "react-icons/io5";

export const ItemDisplay: React.FC<ItemDisplayProps> = ({
  name,
  description,
  price,
}) => {
  return (
    <li className=" p-10 h-96 bg-pepper-light rounded-xl grid grid-rows-3 gap-2 shadow-custom-shadow">
      <p className="text-xl font-bold">{name}</p>
      <p>{description}</p>
      <div className="grid grid-cols-2">
        <div className="flex content-center items-center">
          <IoLogoEuro />
          <p className="font-bold">{price}</p>
        </div>
        <div className="flex content-center items-center">
          <button className="border-2 text-white bg-pepper rounded-md flex place-content-between h-fit px-4 py-2 font-bold hover:bg-white hover:text-pepper hover:border-2 hover:border-pepper">
            <span>Add to Cart</span>
          </button>
        </div>
      </div>
    </li>
  );
};

// interfaces
interface ItemDisplayProps {
  name: String;
  description: String;
  price: number;
}
