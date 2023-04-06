import { useEffect, useState, useContext } from "react";
import { NavLink, useParams } from "react-router-dom";
import { RecipeGrid } from "../components";
import { findById } from "../services/appUserService";

import AuthContext from "../contexts/AuthContext";

export default function Recipebook() {
    const [userRecipes, setUserRecipes] = useState([]);
    const [wait, setWait] = useState(true);
    const { user } = useContext(AuthContext);
    console.log(user.appUserId);
  
    useEffect(() => {
    if(user) {
      findById(user.appUserId)
        .then((result) => {
            setUserRecipes(result.recipes);
            setWait(false);
        })
    }}, []);

    console.log(userRecipes);
}

// const { user } = useContext(AuthContext);
// const { appUserId } = useParams();
// const [ currentUser, setCurrentUser] = useState(null);

// console.log(user);

// const [userRecipes, setUserRecipes] = useState([]);
// const [wait, setWait] = useState(true);


// useEffect(() => {
//   findById(appUserId)
//     .then(setCurrentUser);
// }, [appUserId]);

// console.log(currentUser);