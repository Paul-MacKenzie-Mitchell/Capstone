import * as base from "./baseService";
const model = "ingredients";

export function getEmptyIngredient() {
  return {
    ingredientId: 0,
    recipeId: "",
    amount: 0,
    measurementUnit: "",
  };
}

export async function saveIngredient(ingredient) {
  return base.save(model, ingredient, ingredient.ingredientId);
}
