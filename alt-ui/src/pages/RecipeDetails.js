import { useEffect, useState, useContext } from "react";
import { useParams } from "react-router-dom";

import AuthContext from "../contexts/AuthContext";

import { findById as findByRecipeId, deleteById } from "../services/recipeService";
import { findById as findByUserId } from "../services/appUserService";
import { deleteRecipebookEntry, addRecipebookEntry } from "../services/recipebookService";
import { useNavigate } from "react-router-dom";

function RecipeDetails() {
  const [recipe, setRecipe] = useState(null);
  const [userRecipes, setUserRecipes] = useState([]);
  const navigate = useNavigate();

  const { recipeId } = useParams();

  const auth = useContext(AuthContext);

  const hasAdminPrivileges = auth.user && auth.user.authorities.includes("ADMIN");
  const hasUserPrivileges = auth.user && auth.user.authorities.includes("USER");
  let recipeEntry = false;


  useEffect(() => {
    findByRecipeId(recipeId).then(setRecipe);
    if (auth.user) {
      findByUserId(auth.user.appUserId)
      .then((result) => {
        setUserRecipes(result.recipes);
      })
    }
  }, [recipeId]);


  if (!recipe) {
    return null;
  }


  let userRecipeIds = [];
  if (userRecipes.length > 0) {
    userRecipes.map((recipe) => userRecipeIds.push(recipe.recipeId));
  }
  if (userRecipeIds.includes(Number(recipeId))) {
    recipeEntry = true;
  }


  const handleDatabaseDelete = (id) => {
    deleteById(id)
    .then(() => navigate("/recipes"));
  }
  
  const handleAdd = (userId, recipeId) => {
    const newRecipeEntry = { appUserId: userId, recipeId: recipeId  };
    addRecipebookEntry(newRecipeEntry)
    .then(() => navigate("/recipebook"));
  }

  const handleRecipebookDelete = (userId, recipeId) => {
    deleteRecipebookEntry(userId, recipeId)
    .then(() => navigate("/recipebook"));
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
      { ((hasAdminPrivileges || hasUserPrivileges) && recipeEntry) && (
        <div className="flex items-center justify-center mt-6 gap-x-6">
          <button onClick={() => handleRecipebookDelete(auth.user.appUserId,recipeId)} className="rounded-md bg-[#900603] justify-center px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-[#D0312D] focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-900">
            Delete This Recipe From My Recipebook
          </button>
        </div>
      ) }
      { ((hasAdminPrivileges || hasUserPrivileges) && !recipeEntry) && (
        <div className="flex items-center justify-center mt-6 gap-x-6">
           <button onClick={() => handleAdd(auth.user.appUserId,recipeId)} className="rounded-md bg-[#6a8f6b] px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-green-300 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-900">
            Add This Recipe to My Recipebook
          </button>
        </div>
      ) }
    </>
  );
}

export default RecipeDetails;

// original version
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



// working buttons version

// import { useEffect, useState, useContext } from "react";
// import { useParams } from "react-router-dom";

// import AuthContext from "../contexts/AuthContext";

// import { findById as findByRecipeId, deleteById } from "../services/recipeService";
// import { findById as findByUserId } from "../services/appUserService";
// import { deleteRecipebookEntry, addRecipebookEntry } from "../services/recipebookService";

// function RecipeDetails() {
//   const [recipe, setRecipe] = useState(null);
//   const [userRecipes, setUserRecipes] = useState([]);

//   const { recipeId } = useParams();

//   const auth = useContext(AuthContext);

//   const hasAdminPrivileges = auth.user && auth.user.authorities.includes("ADMIN");
//   const hasUserPrivileges = auth.user && auth.user.authorities.includes("USER");
//   let recipeEntry = false;


//   useEffect(() => {
//     findByRecipeId(recipeId).then(setRecipe);
//     if (auth.user) {
//       findByUserId(auth.user.appUserId)
//       .then((result) => {
//         setUserRecipes(result.recipes);
//       })
//     }
//   }, [recipeId]);


//   if (!recipe) {
//     return null;
//   }


//   let userRecipeIds = [];
//   if (userRecipes.length > 0) {
//     userRecipes.map((recipe) => userRecipeIds.push(recipe.recipeId));
//   }
//   if (userRecipeIds.includes(Number(recipeId))) {
//     recipeEntry = true;
//   }


//   const handleDatabaseDelete = (id) => {
//     deleteById(id);
//   }
  
//   const handleAdd = (userId, recipeId) => {
//     const newRecipeEntry = { appUserId: userId, recipeId: recipeId  };
//     addRecipebookEntry(newRecipeEntry);
//   }

//   const handleRecipebookDelete = (userId, recipeId) => {
//     deleteRecipebookEntry(userId, recipeId);
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
//       { hasAdminPrivileges && (
//         <div className="flex items-center justify-center mt-6 gap-x-6">
//           <button onClick={() => handleDatabaseDelete(recipeId)} className="rounded-md bg-[#900603] justify-center px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-[#D0312D] focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-900">
//             Delete This Recipe<br/><em><sub>Careful&mdash;once you hit this button, this recipe will be gone forever!</sub></em>
//           </button>
//         </div>
//       ) }
//       { ((hasAdminPrivileges || hasUserPrivileges) && recipeEntry) && (
//         <div className="flex items-center justify-center mt-6 gap-x-6">
//           <button onClick={() => handleRecipebookDelete(auth.user.appUserId,recipeId)} className="rounded-md bg-[#900603] justify-center px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-[#D0312D] focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-900">
//             Delete This Recipe From My Recipebook
//           </button>
//         </div>
//       ) }
//       { ((hasAdminPrivileges || hasUserPrivileges) && !recipeEntry) && (
//         <div className="flex items-center justify-center mt-6 gap-x-6">
//           <button onClick={() => handleAdd(auth.user.appUserId,recipeId)} className="rounded-md bg-[#6a8f6b] px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-green-300 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-900">
//             Add This Recipe to My Recipebook
//           </button>
//         </div>
//       ) }
//     </>
//   );
// }