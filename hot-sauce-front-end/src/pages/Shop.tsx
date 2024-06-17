import axios from "axios";
import { useEffect, useState } from "react";
import { Navbar } from "../components/Navbar";
import { ItemDisplay } from "../components/ItemDisplay";
import { CategorySection } from "../components/CategorySection";
import { LeftPanel } from "../components/LeftPanel";
import { RightPanel } from "../components/RightPanel";
import { Sort } from "../components/Sort";

export const Shop = () => {
  const itemsBaseUrl = "http://localhost:8080/hot-sauce-shop/items";
  const [fetchItemUrl, setFetchItemUrl] = useState(itemsBaseUrl);
  const ingredientsUrl = "http://localhost:8080/hot-sauce-shop/ingredients";

  const [ingredients, setIngredients] = useState([]);
  const [items, setItems] = useState([]);
  const [appliedFilters, setAppliedFilters] = useState<string[]>([]);

  useEffect(() => {
    fetchItems();
    fetchIngredients();
  }, [fetchItemUrl]);

  const fetchItems = () => {
    axios
      .get(fetchItemUrl)
      .then((response) => {
        setItems(response.data);
        console.log("items: ", response.data);
      })
      .catch((err) => console.error(err));
  };
  const fetchIngredients = () => {
    axios
      .get(ingredientsUrl)
      .then((response) => {
        setIngredients(response.data);
      })
      .catch((err) => console.error(err));
  };

  const filterCategory: FilterCategory = (category, name, isChecked) => {
    if (isChecked) {
      setAppliedFilters((prevFilters) => {
        const newFilter = `${category}=${name}`;
        const updatedFilters = [...prevFilters, newFilter];
        createNewUrl(updatedFilters);
        return updatedFilters;
      });
    } else {
      const removedFilter = `${category}=${name}`;
      setAppliedFilters((prevFilters) => {
        const updatedFilters = prevFilters.filter(
          (filter) => filter !== removedFilter,
        );
        createNewUrl(updatedFilters);
        return updatedFilters;
      });
    }
  };

  const createNewUrl: CreateNewUrl = (filterList) => {
    let newUrl = filterList.length <= 0 ? itemsBaseUrl : itemsBaseUrl + "?";
    filterList.forEach((filter) => (newUrl += filter + "&"));
    setFetchItemUrl(newUrl);
  };

  return (
    <div>
      <Navbar />
      <div className="grid grid-cols-6 gap-2 p-8">
        <LeftPanel>
          <h3 className="text-xl">Categories</h3>
          <CategorySection
            category={"ingredients"}
            categoryList={ingredients}
            filterCategory={filterCategory}
          />
          <CategorySection
            category={"spice level"}
            categoryList={[
              { name: "ULTRA_HOT", id: 1 },
              { name: "EXTRA_HOT", id: 2 },
              { name: "HOT", id: 3 },
              { name: "MEDIUM", id: 4 },
              { name: "MILD", id: 5 },
            ]}
            filterCategory={filterCategory}
          />
        </LeftPanel>
        <RightPanel>
          <Sort style={"absolute right-20 top-20"} />
          {items.map((item: Item) => (
            <ItemDisplay
              key={item.id}
              name={item.name}
              description={item.description}
              price={item.price}
            />
          ))}
        </RightPanel>
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

interface CreateNewUrl {
  (filterList: string[]): void;
}
