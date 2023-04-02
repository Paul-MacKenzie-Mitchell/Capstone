export default function RecipeCard({ recipe }) {
  //   const designer = game.gameRoles.find((gr) => gr.role.name === "Designer");

  return (
    <div>
      <h1 className="text-black">card {recipe.title}</h1>
    </div>
    // <div className="col">
    //   <div className="card">
    //     {/* {game.imageUrl && (
    //       <img src={game.imageUrl} className="card-img-top" alt={game.name} />
    //     )} */}
    //     <div className="card-body">
    //       <div className="row">
    //         <h5 className="card-title col">{recipe.title}</h5>
    //       </div>
    //       {/* {designer && (
    //         <p className="card-text">
    //           <strong>Designer: </strong>
    //           {designer.person.name} {designer.person.familyName}
    //         </p>
    //       )} */}
    //     </div>
    //   </div>
    // </div>
  );
}
