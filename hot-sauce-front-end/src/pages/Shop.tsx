import axios from "axios";
import { useEffect, useState } from "react";
import { Navbar } from "../components/Navbar";
import { ItemDisplay } from "../components/ItemDisplay";
import { CategorySection } from "../components/CategorySection";
import { LeftPanel } from "../components/LeftPanel";
import { RightPanel } from "../components/RightPanel";
import { Sort } from "../components/Sort";
import { Pricefilter } from "../components/Pricefilter";

export const Shop = () => {
  const ingredientsUrl = "http://localhost:8080/hot-sauce-shop/ingredients";

  const [availableIngredients, setAvailableIngredients] = useState([]);
  const [items, setItems] = useState([]);

  const [params, setParams] = useState({});
  const [selectedIngredients, setSelectedIngredients] = useState([]);
  const [selectedHeatLevels, setSelectedHeatLevels] = useState([]);
  const [selectedMinPrice, setSelectedMinPrice] = useState(Number);
  const [selectedMaxPrice, setSelectedMaxPrice] = useState(Number);

  useEffect(() => {
    fetchItems();
    fetchIngredients();
  }, [params]);

  useEffect(() => {
    buildParams();
  }, [
    selectedHeatLevels,
    selectedIngredients,
    selectedMinPrice,
    selectedMaxPrice,
  ]);

  const fetchItems = () => {
    console.log("params: ", params);
    axios
      .get("http://localhost:8080/hot-sauce-shop/items", { params })
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
        setAvailableIngredients(response.data);
      })
      .catch((err) => console.error(err));
  };

  const buildParams = () => {
    const newParams = {};

    if (selectedIngredients.length > 0) {
      newParams.ingredients = selectedIngredients.join(",");
    }

    if (selectedHeatLevels.length > 0) {
      newParams.heatlevel = selectedHeatLevels.join(",");
    }

    if (selectedMinPrice > 0 && selectedMinPrice < 1000) {
      newParams.min = selectedMinPrice;
    }

    if (selectedMaxPrice > 0 && selectedMaxPrice < 1000) {
      newParams.max = selectedMaxPrice;
    }

    setParams(newParams);
  };

  const filterCategory = (category, name, isChecked) => {
    if (category === "ingredients") {
      setSelectedIngredients((prevIngredients) => {
        if (isChecked) {
          return [...prevIngredients, name];
        } else {
          return prevIngredients.filter((ingredient) => ingredient !== name);
        }
      });
    }

    if (category === "spice level") {
      setSelectedHeatLevels((prevHeatLevels) => {
        if (isChecked) {
          return [...prevHeatLevels, name.replace(" ", "_")];
        } else {
          return prevHeatLevels.filter(
            (level) => level !== name.replace("_", " "),
          );
        }
      });
    }
  };

  const addPriceFilter = (label, minmax) => {
    if (label == "min") {
      setSelectedMinPrice(minmax);
    } else if (label == "max") {
      setSelectedMaxPrice(minmax);
    } else {
      console.error(
        "Something went wrong, addPriceFilter did not receive a min or a max value.",
      );
    }
  };

  return (
    <div>
      <Navbar />
      <div className="grid grid-cols-6 gap-2 p-8">
        <LeftPanel>
          <h3 className="text-xl">Categories</h3>
          <CategorySection
            category={"ingredients"}
            categoryList={availableIngredients}
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
          <Pricefilter addPriceFilter={addPriceFilter} />
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
