import { useEffect, useState, useContext } from "react";
import { useParams } from "react-router-dom";

import AuthContext from "../contexts/AuthContext";

import { findById, deleteById } from "../services/recipeService";
import { deleteRecipebookEntry } from "../services/recipebookService";

function RecipeDetails() {
  const [recipe, setRecipe] = useState(null);

  const { recipeId } = useParams();

  const auth = useContext(AuthContext);

  const hasAdminPrivileges = auth.user && auth.user.authorities.includes("ADMIN");
  const hasUserPrivileges = auth.user && auth.user.authorities.includes("USER");

  useEffect(() => {
    findById(recipeId).then(setRecipe);
  }, [recipeId]);

  if (!recipe) {
    return null;
  }

  const handleDatabaseDelete = (id) => {
    deleteById(id);
  }

  const handleRecipebookDelete = (userId, recipeId) => {
    deleteRecipebookEntry(userId, recipeId);
  }

  return (
    <>
      <div className="w-full mx-8 py-16 px-4">
        <h1 className="flex justify-end md:text-4xl sm:text-3xl text-2xl font-bold py-2 pe-16 text-[#221c42]">
          {recipe.title}
        </h1>
        <div className="max-w-[1240px] mx-auto grid md:grid-cols-2">
          <img
            className="w-[500px] mx-auto my-4"
            src={recipe.imageUrl}
            alt="image of laptop"
          />
          <div className="flex mx-8 flex-col justify-center">
            <p className="text-[#6a8f6b] font-bold">
              Prep Time: {recipe.prepTime}
            </p>
            <p className="text-[#6a8f6b] font-bold">
              Cook Time: {recipe.cookTime}
            </p>
            <ul className="mt-6">
              {recipe.ingredients.length > 0 ?
                (<li className="p-4 border-b text-center border-gray-600">
                  Ingredients List
                </li>) : <div></div>
              }
              {recipe.ingredients.map((i) => (
                <li className="p-4 border-b border-gray-600">
                  {i.amount} {i.measurementUnit} {i.food.foodName}
                </li>
              ))}
            </ul>
          </div>
        </div>
      </div>
      <div className="w-full flex mx-8 py-6 ps-8 bg-white ">
        <p className="leading-10">
          <pre className="font-sans ...">{recipe.instructions}</pre>
        </p>
      </div>
      { hasAdminPrivileges && (
        <div className="flex items-center justify-center mt-6 gap-x-6">
          <button onClick={() => handleDatabaseDelete(recipeId)} className="rounded-md bg-[#900603] justify-center px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-[#D0312D] focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-900">
            Delete This Recipe<br/><em><sub>Careful&mdash;once you hit this button, this recipe will be gone forever!</sub></em>
          </button>
        </div>
      ) }
      { (hasAdminPrivileges || hasUserPrivileges) && (
        <div className="flex items-center justify-center mt-6 gap-x-6">
          <button onClick={() => handleRecipebookDelete(auth.user.appUserId,recipeId)} className="rounded-md bg-[#900603] justify-center px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-[#D0312D] focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-900">
            Delete This Recipe From My Recipebook
          </button>
        </div>
      ) }
    </>
  );
}

export default RecipeDetails;

// import { useEffect, useState, useContext } from "react";
// import { useParams } from "react-router-dom";

// import AuthContext from "../contexts/AuthContext";

// import { findById } from "../services/recipeService";
// import { deleteById } from "../services/recipeService";

// function RecipeDetails() {
//   const [recipe, setRecipe] = useState(null);

//   const { recipeId } = useParams();

//   const auth = useContext(AuthContext);

//   const canDelete = auth.user && auth.user.authorities.includes("ADMIN");

//   useEffect(() => {
//     findById(recipeId).then(setRecipe);
//   }, [recipeId]);

//   if (!recipe) {
//     return null;
//   }

//   const handleDelete = (id) => {
//     deleteById(recipeId);
//   }

//   return (
//     <>
//       <div className="w-full mx-8 py-16 px-4">
//         <h1 className="flex justify-end md:text-4xl sm:text-3xl text-2xl font-bold py-2 pe-16 text-[#221c42]">
//           {recipe.title}
//         </h1>
//         <div className="max-w-[1240px] mx-auto grid md:grid-cols-2">
//           <img
//             className="w-[500px] mx-auto my-4"
//             src={recipe.imageUrl}
//             alt="image of laptop"
//           />
//           <div className="flex mx-8 flex-col justify-center">
//             <p className="text-[#6a8f6b] font-bold">
//               Prep Time: {recipe.prepTime}
//             </p>
//             <p className="text-[#6a8f6b] font-bold">
//               Cook Time: {recipe.cookTime}
//             </p>
//             <ul className="mt-6">
//               {recipe.ingredients.length > 0 ?
//                 (<li className="p-4 border-b text-center border-gray-600">
//                   Ingredients List
//                 </li>) : <div></div>
//               }
//               {recipe.ingredients.map((i) => (
//                 <li className="p-4 border-b border-gray-600">
//                   {i.amount} {i.measurementUnit} {i.food.foodName}
//                 </li>
//               ))}
//             </ul>
//           </div>
//         </div>
//       </div>
//       <div className="w-full flex mx-8 py-6 ps-8 bg-white ">
//         <p className="leading-10">
//           <pre className="font-sans ...">{recipe.instructions}</pre>
//         </p>
//       </div>
//       { canDelete && (
//         <div className="flex items-center justify-center mt-6 gap-x-6">
//           <button onClick={() => handleDelete(recipeId)} className="rounded-md bg-[#900603] justify-center px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-[#D0312D] focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-900">
//             Delete This Recipe<br/><em><sub>Careful&mdash;once you hit this button, this recipe will be gone forever!</sub></em>
//           </button>
//         </div>
//       ) }
//     </>
//   );
// }