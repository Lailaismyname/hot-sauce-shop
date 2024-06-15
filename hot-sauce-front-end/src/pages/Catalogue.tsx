import axios from "axios";
import { useEffect, useState } from "react";
import { Navbar } from "../components/Navbar";
import { ItemDisplay } from "../components/ItemDisplay";
import { CategorySection } from "../components/CategorySection";

export const Catalogue = () => {
  const itemsBaseUrl = "http://localhost:8080/hot-sauce-shop/items";
  const [ingredients, setIngredients] = useState([]);
  const [items, setItems] = useState([]);
  const [appliedFilters, setAppliedFilters] = useState<String[]>([]);

  const [fetchItemUrl, setFetchItemUrl] = useState(itemsBaseUrl);

  const fetchItems = () => {
    axios.get(fetchItemUrl).then((response) => {
      setItems(response.data);
      //console.log("Items: ", response.data);
    });
  };
  const fetchIngredients = () => {
    axios
      .get("http://localhost:8080/hot-sauce-shop/ingredients")
      .then((response) => {
        setIngredients(response.data);
        //console.log("Ingredients: ", response.data);
      });
  };

  const filterCategory: FilterCategory = (category, name, isChecked) => {
    if (isChecked) {
      const existingFilters = appliedFilters;
      const newFilter = `${category}=${name}`;
      appliedFilters.push(newFilter);
      setAppliedFilters(existingFilters);
      let newUrl = itemsBaseUrl + "/filter?";
      appliedFilters.forEach((filter) => (newUrl += filter + "&"));
      setFetchItemUrl(newUrl);
    } else {
      // bij het verwijderen loopt ie achter!
      const removedFilter = `${category}=${name}`;
      setAppliedFilters((prev) =>
        prev.filter((filter) => filter !== removedFilter),
      );
      let newUrl = itemsBaseUrl + "/filter?";
      appliedFilters.forEach((filter) => (newUrl += filter + "&"));
      setFetchItemUrl(newUrl);
    }
  };

  useEffect(() => {
    fetchItems();
    fetchIngredients();
    console.log("running");
  }, [fetchItemUrl]);

  return (
    <div>
      <Navbar />
      <div className="grid grid-cols-6 gap-2 p-8">
        <div className="grid-cols-subgrid col-span-1 p-2">
          <h3 className="text-xl">Categories</h3>
          <CategorySection
            category={"ingredients"}
            categoryList={ingredients}
            filterCategory={filterCategory}
          />
        </div>
        <ul className="col-span-5 grid grid-cols-3 gap-8">
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

interface FilterCategory {
  (category: string, name: string, isChecked: boolean): void;
}
