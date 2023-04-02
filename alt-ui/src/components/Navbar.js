import React, { useState, useContext } from "react";
import { AiOutlineClose, AiOutlineMenu } from "react-icons/ai";
import { Link, NavLink, useLocation } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";

const Navbar = () => {
  const [nav, setNav] = useState(false);

  const handleNav = () => {
    setNav(!nav);
  };

  const location = useLocation();
  const { user, logout } = useContext(AuthContext);

  function handleLogout(evt) {
    evt.preventDefault();
    logout();
  }

  return (
    <nav className="flex justify-between items-center h-24 max-w-[1240px] mx-auto px-4 ">
      <NavLink
        to="/"
        className={({ isActive }) => (isActive ? "font-bold" : "")}
      >
        <h1 className="w-full text-3xl font-bold text-[#6a8f6b]">
          Fresh Feast
        </h1>
      </NavLink>

      <ul className="hidden md:flex text-[#6a8f6b]">
        <NavLink className="p-4" to="/">
          Home
        </NavLink>
        <NavLink className="p-4" to="/recipes">
          Recipes
        </NavLink>
        <NavLink className="p-4" to="/">
          RecipeBook
        </NavLink>
        <NavLink className="p-4" to="/">
          About
        </NavLink>
        {user ? (
          <>
            <a href="#logout" className="p-4" onClick={handleLogout}>
              Logout
            </a>
          </>
        ) : (
          <NavLink className="p-4" to="/login">
            Login
          </NavLink>
        )}
      </ul>
      <div onClick={handleNav} className="block md:hidden">
        {nav ? <AiOutlineClose size={20} /> : <AiOutlineMenu size={20} />}
      </div>
      <ul
        className={
          nav
            ? "fixed left-0 top-0 w-[60%] h-full border-r border-r-gray-900 bg-[#6a8f6b] ease-in-out duration-500"
            : "ease-in-out duration-500 fixed left-[-100%]"
        }
      >
        <h1 className="w-full text-3xl font-bold text-[#ffe0c9] m-4">
          Fresh Feast
        </h1>
        <li className="p-4 border-b text-[#ffe0c9] border-gray-600">Home</li>
        <li className="p-4 border-b text-[#ffe0c9] border-gray-600">Recipes</li>
        <li className="p-4 border-b text-[#ffe0c9] border-gray-600">
          RecipeBook
        </li>
        <li className="p-4 border-b text-[#ffe0c9] border-gray-600">Login</li>
        <li className="p-4 border-b text-[#ffe0c9] border-gray-600">About</li>
        <li className="p-4 text-[#ffe0c9]">Contact</li>
      </ul>
    </nav>
  );
};

export default Navbar;
