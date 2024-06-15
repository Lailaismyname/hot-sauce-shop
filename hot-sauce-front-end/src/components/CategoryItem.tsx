export const CategoryItem = ({ name }) => {
  return (
    <dd className="mt-2">
      <input
        className="mr-2"
        type="checkbox"
        id={name}
        name={name}
        value={name}
      />
      <label htmlFor={name}>{name}</label>
    </dd>
  );
};
