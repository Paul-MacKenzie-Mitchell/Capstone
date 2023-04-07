const url = "http://localhost:8080/api/recipebook";


export async function deleteRecipebookEntry(userId, recipeId) {
    const config = {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("Recipe_JWT")}`,
      },
    };
  
    const response = await fetch(`${url}/${userId}/${recipeId}`, config);
    if (response.ok) {
      return;
    }
    return Promise.reject("Could not delete recipebook entry.");
  }