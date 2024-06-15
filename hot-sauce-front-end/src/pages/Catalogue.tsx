import axios from "axios";
import { useEffect, useState } from "react";
import { Navbar } from "../components/Navbar";
import { ItemDisplay } from "../components/ItemDisplay";
import { CategorySection } from "../components/CategorySection";

export const Catalogue = () => {
  const temporaryIngredientList = ["jalapeno", "chili", "habanero"];
  const [items, setItems] = useState([]);
  const fetchItems = () => {
    axios
      .get("http://localhost:8080/hot-sauce-shop/items")
      .then((res) => setItems(res.data));
  };

  useEffect(() => {
    fetchItems();
  }, []);

  return (
    <div>
      <Navbar />
      {/* this is main wrapper */}
      <div className="grid grid-cols-6 gap-2">
        <div className="grid-cols-subgrid col-span-2 p-2">
          <h3 className="text-xl">Categories</h3>
          <CategorySection
            categoryName={"ingredients"}
            categoryList={temporaryIngredientList}
          />
        </div>
        <ul className="grid-cols-subgrid col-span-4">
          {items.map((item: Item) => (
            <ItemDisplay
              key={item.id}
              name={item.name}
              description={item.description}
              price={item.price}
            />
          ))}
        </ul>
      </div>
    </div>
  );
};

//interfaces
interface Item {
  id: number;
  name: String;
  description: String;
  price: number;
}
