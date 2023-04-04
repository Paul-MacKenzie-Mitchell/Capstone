import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { findAll } from "../services/recipeService";
import RecipeCard from "./RecipeCard";

export default function RecipeGrid() {
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
    <div className="w-full mx-8 py-[2em] px-4 ">
      <div className="bg-white rounded-lg">
        <div className="mx-auto max-w-2xl px-4 py-16 sm:px-6 sm:py-24 lg:max-w-7xl lg:px-8">
          <h2 className="sr-only">Products</h2>

          <div className="grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 xl:gap-x-8">
            {recipes.map((r) => (
              <RecipeCard key={r.recipeId} recipe={r} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
