import { RecipeGrid } from "../components";

function Recipes() {
  return (
    <>
      <div className="text-black">
        <div className="max-w-[800px] mt-[-96px] w-full h-screen mx-auto text-center flex flex-col justify-center">
          <RecipeGrid />
        </div>
      </div>
    </>
  );
}

export default Recipes;
