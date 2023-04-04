import { useEffect, useState } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";

import { findById } from "../services/recipeService";

function RecipeDetails() {
  const [recipe, setRecipe] = useState(null);

  const { recipeId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    findById(recipeId).then(setRecipe);
  }, [recipeId]);

  if (!recipe) {
    return null;
  }

  return (
    <>
      <section className="grid grid-cols-1 gap-0 bg-blue-100 bg-opacity-25 md:grid-cols-2">
        <div clasclassNames="flex flex-col items-start justify-center px-4 py-24 lg:px-20">
          <h1 className="mb-6 text-4xl font-bold leading-tight text-blue-900 md:text-4xl lg:text-5xl">
            Great customer relationships start here.
          </h1>
          <p className="pr-0 mb-4 text-sm text-blue-800 tracking-relaxed lg:pr-16">
            Get the #1 Business Messenger and start delivering personalized
            experiences at every stage of the customer journey.
          </p>
        </div>
        <div>
          <img
            src={recipe.imageUrl}
            className="object-cover w-full h-64 bg-gray-100 md:h-full"
            loading="lazy"
          />
        </div>
      </section>

      <div className="w-full mx-8 py-16 px-4">
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
            <h1 className="md:text-4xl sm:text-3xl text-2xl font-bold py-2 text-[#221c42]">
              {recipe.title}
            </h1>
            <ul className="mt-6">
              <li className="p-4 border-b text-center border-gray-600">
                Ingrediants List
              </li>
              {recipe.ingredients.map((i) => (
                <li className="p-4 border-b border-gray-600">
                  {i.amount} {i.measurementUnit}
                  {i.food.foodName}
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
    </>
  );
}

export default RecipeDetails;
