import { CategoryItem } from "./CategoryItem";

export const CategorySection = ({ categoryName, categoryList }) => {
  return (
    <dl className="p-2">
      <dt>{categoryName}</dt>
      {categoryList.map((categoryItem) => (
        <CategoryItem name={categoryItem} />
      ))}
    </dl>
  );
};
