export const ItemDisplay: React.FC<ItemDisplayProps> = ({
  name,
  description,
  price,
}) => {
  return (
    <li className="border-2 p-2">
      <p>Name: {name}</p>
      <p>Description: {description}</p>
      <p>Price: {price}</p>
    </li>
  );
};

// interfaces
interface ItemDisplayProps {
  name: String;
  description: String;
  price: number;
}
