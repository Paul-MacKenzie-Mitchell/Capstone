import { useEffect, useState, useContext } from "react";
import { useParams } from "react-router-dom";
import { RecipeGrid } from "../components";
import { findById } from "../services/appUserService";

import AuthContext from "../contexts/AuthContext";

export default function Recipebook() {
  //   const [userRecipes, setUserRecipes] = useState([]);
  //   const [wait, setWait] = useState(true);
  //   const { user } = useContext(AuthContext);
  //   console.log(user.appUserId);

  //   useEffect(() => {
  //     findById(user.appUserId).then((result) => {
  //       setUserRecipes(result.recipes);
  //       setWait(false);
  //     });
  //   }, []);

  //   console.log(userRecipes);
  // }
  //   useEffect(() => {
  //     findById(user.appUserId).then((result) => {
  //       setUserRecipes(result.recipes);it
  //       setWait(false);
  //     });
  //   }, []);

  const { user } = useContext(AuthContext);
  const { appUserId } = useParams();
  const [currentUser, setCurrentUser] = useState(null);

  const [userRecipes, setUserRecipes] = useState([]);
  const [wait, setWait] = useState(true);

  useEffect(() => {
    findById(appUserId)
      .then(setCurrentUser)
      .then(setUserRecipes(currentUser.recipes));
  }, [appUserId]);

  return (
    <div>
      <RecipeGrid array={userRecipes} />
    </div>
  );
}
