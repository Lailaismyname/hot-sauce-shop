import { IoLogoEuro } from "react-icons/io5";
import { ItemImage } from "./ItemImage";

export const ItemDisplay: React.FC<ItemDisplayProps> = ({
  name,
  description,
  price,
}) => {
  return (
    <li className="gap-2 p-10 h-96 rounded-md shadow-custom-shadow-jalapeno grid grid-rows-6 hover:bg-jalapeno-light cursor-pointer">
      <div id="top-row" className="row-span-4 grid grid-cols-6">
        <div
          className="items-center flex p-2
 col-span-2"
        >
          <ItemImage />
        </div>
        <div className="col-span-4 p-2 flex justify-between flex-col text-center">
          <h6 className="text-xl font-bold">{name}</h6>
          <p className="mt-2">{description}</p>
        </div>
      </div>
      <div
        id="bottom-row"
        className="row-span-2 flex items-center justify-between"
      >
        <div className="flex content-center items-center">
          <IoLogoEuro />
          <p>{price}</p>
        </div>
        <button className="border-2 border-jalapeno text-jalapeno bg-white rounded-md flex place-content-between h-fit px-4 py-2 font-bold hover:bg-jalapeno hover:text-white">
          Add to Cart
        </button>
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
