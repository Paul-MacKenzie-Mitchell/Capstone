import { useState, useContext, useEffect } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { RecipeGrid } from "../components";
import { findAll } from "../services/recipeService";

import AuthContext from "../contexts/AuthContext";

function Recipes() {
  const { user } = useContext(AuthContext);
  const [recipes, setRecipes] = useState([]);
  const navigate = useNavigate();
  const [wait, setWait] = useState(true);

  useEffect(() => {
    findAll()
      .then((result) => {
        setRecipes(result);
        setWait(false);
      })
      .catch(() => navigate("/"));
  }, [navigate]);

  if (wait) {
    return (
      <div className="spinner-border" role="status">
        <span className="visually-hidden">Loading...</span>
      </div>
    );
  }

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
          <div className="w-full mx-8 py-[2em] px-4 ">
      <div className="bg-white rounded-lg">
        <div className="mx-auto max-w-2xl px-4 py-2 sm:px-6 sm:py-4 lg:max-w-7xl lg:px-8">
          <h2 className="py-4 text-4xl font-bold tracking-tight text-green-300 sm:text-6xl">
            Recipes
          </h2>
          <RecipeGrid array={recipes} />
          </div>
          </div>
          </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Recipes;

// return (
//   <>
//     <div className="">
//       <div className="">
//         <div className="col d-flex justify-content-end align-items-center">
//           <div className="mt-10 flex items-center justify-center gap-x-6">
//             {user ? (
//               <div className="mt-10 flex items-center justify-center gap-x-6">
//                 <NavLink
//                   to="add"
//                   className="rounded-md bg-[#6a8f6b] px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-green-300 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-900"
//                 >
//                   Add A Recipe
//                 </NavLink>
//               </div>
//             ) : (
//               <div></div>
//             )}
//           </div>
//         </div>
//         <RecipeGrid array={recipes} />
//       </div>
//     </div>
//   </>
// );
