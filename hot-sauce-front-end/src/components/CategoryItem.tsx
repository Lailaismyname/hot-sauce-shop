export const CategoryItem: React.FC<CategoryItem> = ({
  category,
  name,
  filterCategory,
}) => {
  return (
    <dd className="mt-2">
      <input
        className="mr-2"
        type="checkbox"
        id={name}
        name={name}
        value={name}
        onChange={(e) => {
          filterCategory(category, name, e.target.checked);
        }}
      />
      <label className="capitalize" htmlFor={name}>
        {name}
      </label>
    </dd>
  );
};

interface CategoryItem {
  category: string;
  name: string;
  filterCategory: (category: string, name: string, checked: boolean) => void;
}
