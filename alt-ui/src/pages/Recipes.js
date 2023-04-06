import { useState, useContext } from "react";
import { NavLink } from "react-router-dom";
import { RecipeGrid } from "../components";

import AuthContext from "../contexts/AuthContext";

function Recipes() {
  const { user } = useContext(AuthContext);

  return (
    <>
      <div className="">
        <div className="">
          <div className="col d-flex justify-content-end align-items-center">
            <div className="mt-10 flex items-center justify-center gap-x-6">
              {user ? (
                <div className="mt-10 flex items-center justify-center gap-x-6">
                  <NavLink
                    to="add"
                    className="rounded-md bg-[#6a8f6b] px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-green-300 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-900"
                  >
                    Add A Recipe
                  </NavLink>
                </div>
              ) : (
                <div></div>
              )}
            </div>
          </div>
          <RecipeGrid />
        </div>
      </div>
    </>
  );
}

export default Recipes;
