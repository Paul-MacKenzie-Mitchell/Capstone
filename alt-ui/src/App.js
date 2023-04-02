import { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { refresh } from "./services/authService";
import AuthContext from "./contexts/AuthContext";
import { Home, Login, RecipeDetails, Recipes } from "./pages";
import { Navbar } from "./components";

function App() {
  const [user, setUser] = useState();

  useEffect(() => {
    refresh().then(login).catch();
  }, []);

  function login(userArg) {
    setUser(userArg);
    localStorage.setItem("Recipe_JWT", userArg.jwt);
  }

  function logout() {
    setUser();
    localStorage.removeItem("Recipe_JWT");
  }

  const auth = {
    user,
    login,
    logout,
  };

  return (
    <AuthContext.Provider value={auth}>
      <Router>
        <Navbar />
        <div className="container mb-5 mt-2">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/recipes">
              <Route index element={<Recipes />} />
              <Route path=":recipeId">
                <Route index element={<RecipeDetails />} />
              </Route>
            </Route>
            <Route path="/login" element={<Login />} />
          </Routes>
        </div>
      </Router>
    </AuthContext.Provider>
  );
}

export default App;
