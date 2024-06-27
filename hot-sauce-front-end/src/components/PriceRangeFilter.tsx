import { useState, useEffect } from "react";

export const PriceRangeFilter = ({
  label,
  addPriceFilter,
}: {
  label: string;
  addPriceFilter: (label: string, minmax: number) => void;
}) => {
  const [amount, setAmount] = useState<number>(0);

  useEffect(() => {
    addPriceFilter(label, amount);
  }, [amount]);

  return (
    <div>
      <label htmlFor={label}>{label}</label>
      <input
        className="border-2"
        type="number"
        value={amount}
        onChange={(e) => {
          if (Number(e.target.value) < 0 || Number(e.target.value) > 1000) {
            console.error("amount must be between 0 and 1000");
          } else {
            setAmount(Number(e.target.value));
          }
        }}
      />
    </div>
  );
};
