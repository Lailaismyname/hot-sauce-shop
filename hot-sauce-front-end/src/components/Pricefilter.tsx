import { PriceRangeFilter } from "./PriceRangeFilter";

export const Pricefilter = ({ addPriceFilter }) => {
  return (
    <div>
      <p>Sort by:</p>
      <PriceRangeFilter label={"min"} addPriceFilter={addPriceFilter} />
      <PriceRangeFilter label={"max"} addPriceFilter={addPriceFilter} />
    </div>
  );
};
