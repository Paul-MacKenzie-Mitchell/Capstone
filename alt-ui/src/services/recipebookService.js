const url = "http://localhost:8080/api/recipebook";

export function getEmptyRecipeEntry() {
    return {
      appUserId: 0,
      recipeId: 0
    };
  }

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

export async function addRecipebookEntry(recipebookEntry) {
    const config = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("Recipe_JWT")}`,
        },
        body: JSON.stringify(recipebookEntry),
      };

      const response = await fetch(`${url}`, config);
      if (response.ok) {
        return response.json();
      }
    
      if (response.status === 400) {
        const errors = await response.json();
        return Promise.reject(errors);
      }
      return Promise.reject();
  }