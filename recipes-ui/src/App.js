import './index.css';

import { createContext, useState, useEffect } from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";

import AboutUs from "./components/AboutUs";
import Contact from "./components/Contact";
import Home from "./components/Home";
import HttpStatus from "./components/HttpStatus";
import NavBar from "./components/NavBar";
import MeetUs from "./components/MeetUs";
import Profile from "./components/Profile";
import RecipeCard from "./components/RecipeCard";
import RecipeDelete from "./components/RecipeDelete";
import RecipeForm from "./components/RecipeForm"; 
import RecipeList from "./components/RecipeList";
import Search from "./components/Search";
import UserLogin from "./components/UserLogin";
import UserRegistrationForm from "./components/UserRegistrationForm";
import { baseUrl } from './shared';

export const LoginContext = createContext();

function App() {
  useEffect(() => {
      function refreshTokens() {
          if (localStorage.refresh) {
              const url = baseUrl + 'api/token/refresh/';
              fetch(url, {
                  method: 'POST',
                  headers: {
                      'Content-Type': 'application/json',
                  },
                  body: JSON.stringify({
                      refresh: localStorage.refresh,
                  }),
              })
                  .then((response) => {
                      return response.json();
                  })
                  .then((data) => {
                      localStorage.access = data.access;
                      localStorage.refresh = data.refresh;
                      setLoggedIn(true);
                  });
          }
      }

      const minute = 1000 * 60;
      refreshTokens();
      setInterval(refreshTokens, minute * 3);
  }, []);

  const [loggedIn, setLoggedIn] = useState(
      localStorage.access ? true : false
  );

  function changeLoggedIn(value) {
      setLoggedIn(value);
      if (value === false) {
          localStorage.clear();
      }
  }




  return (
    <LoginContext.Provider value={[loggedIn, changeLoggedIn]}>
    <Router>
      <NavBar />
      <div className="container mb-5 mt-2">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/aboutus" element={<AboutUs />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/meetus" element={<MeetUs />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/recipecard" element={<RecipeCard />} />
          <Route path="/recipe/delete/:id" element={<RecipeDelete />} />
          <Route path="/recipe/add" element={<RecipeForm />} />
          <Route path="/recipe/edit/:id" element={<RecipeForm />} />
          <Route path="/recipelist" element={<RecipeList />} />
          <Route path="/search" element={<Search />} />
          <Route path="/userlogin" element={<UserLogin />} />
          <Route path="/userregistration/add" element={<UserRegistrationForm />} />
          <Route path="/userregistration/edit/:id" element={<UserRegistrationForm />} />
          <Route path="/userregistration/delete/:id" element={<UserRegistrationForm />} />
          <Route path="/500" element={<HttpStatus message="Server Error" status={500} />} />
          <Route path="*" element={<HttpStatus message="Not Found" status={404} />} />
        </Routes>
      </div>
    </Router>
  </LoginContext.Provider>
  );
  
}

export default App;
