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
          <p>{recipe.instructions}</p>
          <ul>
            <li>Ingrediants List</li>
            {recipe.foods.map((f) => (
              <li>{f.foodName}</li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default RecipeDetails;
