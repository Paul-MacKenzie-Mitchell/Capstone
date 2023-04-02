import { NavLink } from "react-router-dom";

export default function RecipeCard({ recipe }) {
  //   const designer = game.gameRoles.find((gr) => gr.role.name === "Designer");

  return (
    <div className="col">
      <div className="w-full h-[30rem] shadow-xl flex flex-col p-4 my-4 rounded-lg hover:scale-110 duration-300">
        <div className=" my-2 h-full bg-white shadow-md border border-gray-200 rounded-lg max-w-sm dark:bg-[#6a8f6b] dark:border-[#6a8f6b]">
          {recipe.imageUrl && (
            <img
              src={recipe.imageUrl}
              className="card-img-top"
              alt={recipe.name}
            />
          )}
          <div className="p-5">
            <NavLink to={`/recipes/${recipe.recipeId}`}>
              <h5 className=" font-bold text-2xl tracking-tight mb-2 text-white">
                {recipe.title}
              </h5>
            </NavLink>
          </div>
        </div>
      </div>
    </div>
  );
}
