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
    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-2">
      {recipes.map((r) => (
        <RecipeCard key={r.recipeId} recipe={r} />
      ))}
    </div>
  );
}
