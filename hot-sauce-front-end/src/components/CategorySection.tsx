import { CategoryItem } from "./CategoryItem";

export const CategorySection: React.FC<CategoryProps> = ({
  category,
  categoryList,
  filterCategory,
}) => {
  return (
    <dl className="p-2">
      <dt>{category}</dt>
      {categoryList.map((categoryItem) => (
        <CategoryItem
          name={categoryItem.name}
          category={category}
          key={categoryItem.id}
          filterCategory={filterCategory}
        />
      ))}
    </dl>
  );
};

interface CategoryProps {
  category: string;
  filterCategory: (category: string, name: string, checked: boolean) => void;
  categoryList: { name: string; id: Number }[];
}
