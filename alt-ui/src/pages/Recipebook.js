import { useEffect, useState, useContext } from "react";
import { NavLink, useNavigate, useParams } from "react-router-dom";
import { RecipeCard, RecipeGrid } from "../components";
import { findById } from "../services/appUserService";

import AuthContext from "../contexts/AuthContext";

export default function Recipebook() {

    const [userRecipes, setUserRecipes] = useState([]);
    const [wait, setWait] = useState(true);
    const { user } = useContext(AuthContext);
    const navigate = useNavigate();

    useEffect(() => {
        if (user) {
            findById(user.appUserId)
                .then((result) => {
                    setUserRecipes(result.recipes);
                    setWait(false);
                })
        }
    }, []);

    if (user && wait) {
        return (
            <div className="spinner-border" role="status">
                <span className="visually-hidden">Loading...</span>
            </div>
        );
    }

    return (
        <div className="">
            <div className="">

                <div className="col d-flex justify-content-end align-items-center">
                    <div className="w-full mx-8 py-[2em] px-4 ">
                        {user ?
                            (<div className="bg-white rounded-lg">
                                <div className="mx-auto max-w-2xl px-4 py-2 sm:px-6 sm:py-4 lg:max-w-7xl lg:px-8">
                                    <h2 className="py-4 text-4xl font-bold tracking-tight text-green-300 sm:text-6xl">
                                        Your Recipebook
                                    </h2>
                                    <RecipeGrid array={userRecipes} />
                                </div>
                            </div>)
                            :
                            (<h2 className="py-4 text-xl text-center font-bold tracking-tight text-black-300 sm:text-4xl">
                                Oops! Looks like you're not logged in.
                            </h2>)
                        }
                    </div>
                </div>

            </div>
        </div>
    );

}

// return (
//     <div className="">
//         <div className="">
//             <div className="col d-flex justify-content-end align-items-center">
//                 { user ? 
//                 <RecipeGrid array={userRecipes}/> 
//                 : 
//                 navigate("/") }
//             </div>
//         </div>
//     </div>
// );


