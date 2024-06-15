import "./App.css";
import { Cart } from "./pages/Cart";
import { Catalogue } from "./pages/Catalogue";
import { Home } from "./pages/Home";
import { Routes, Route } from "react-router-dom";
import { UserAccount } from "./pages/UserAccount";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/catalogue" element={<Catalogue />} />
        {/* <Route path="/item/:id" element={<CatalogueItem />} /> */}
        <Route path="/cart" element={<Cart />} />
        <Route path="/account" element={<UserAccount />} />
      </Routes>
    </>
  );
}

export default App;
