import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { findAll } from "../services/recipeService";
import RecipeCard from "./RecipeCard";

export default function RecipeGrid() {
  const [recipes, setRecipes] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    findAll()
      .then(setRecipes)
      .catch(() => navigate("/500"));
  }, [navigate]);

  return (
    <div className="w-full mx-8 py-[2em] px-4 ">
      <div className="max-w-[1240px] mx-auto grid md:grid-cols-3 my-2 gap-8">
        {recipes.map((r) => (
          <RecipeCard key={r.recipeId} recipe={r} />
        ))}
      </div>
    </div>
  );
}
