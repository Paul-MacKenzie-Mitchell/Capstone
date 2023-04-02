import { useContext, useState } from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { authenticate } from "../services/authService";
import AuthContext from "../contexts/AuthContext";
import MainImage from "../assets/ella-olsson-KPDbRyFOTnE-unsplash.jpg";

export default function Login() {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });

  const [hasError, setHasError] = useState(false);
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  function handleChange(evt) {
    const next = { ...credentials };
    next[evt.target.name] = evt.target.value;
    setCredentials(next);
  }

  function handleSubmit(evt) {
    evt.preventDefault();
    authenticate(credentials)
      .then((user) => {
        login(user);
        navigate("/");
      })
      .catch(() => setHasError(true));
  }

  return (
    <>
      <div className="w-full py-16 text-white px-4">
        <div className="max-w-[1240px] mx-auto grid lg:grid-cols-3">
          <div className="lg:col-span-2">
            <h1 className="ml-6 md:text-4xl sm:text-3xl text-2xl font-bold py-2">
              Login to access your account
            </h1>
            <p className="ml-6 mb-4">or create an account to get started</p>
            <img
              className="ml-6 py-8 w-[500px] mx-auto my-4 rounded-lg py-2"
              src={MainImage}
              alt="image of serving dish and food"
            ></img>
          </div>
          <div className="my-4 px-4">
            <div className="flex flex-col sm:flex-row items-center justify-between w-full">
              <form className=" text-black" onSubmit={handleSubmit}>
                <div className="mb-2">
                  <input
                    type="text"
                    className="form-control w-[320px] rounded-md font-medium ml-4 my-6 px-3 py-3"
                    id="username"
                    name="username"
                    value={credentials.username}
                    onChange={handleChange}
                    placeholder="User Name"
                  />
                </div>
                <div className="mb-2">
                  <input
                    type="password"
                    className="form-control w-[320px] rounded-md font-medium ml-4 my-6 px-3 py-3"
                    id="password"
                    name="password"
                    value={credentials.password}
                    onChange={handleChange}
                    placeholder="Password"
                  />
                </div>
                <div className="mb-2">
                  <button
                    type="submit"
                    className="bg-[#6a8f6b] w-[150px] rounded-md font-medium ml-4 my-6 px- py-3 text-white"
                  >
                    Login
                  </button>
                  <button className="bg-[#6a8f6b] w-[150px] rounded-md font-medium ml-4 my-6 px- py-3 text-white">
                    <NavLink to="/">Cancel</NavLink>
                  </button>
                </div>
                {hasError && (
                  <div className="mx-6 alert alert-danger">Bad Credentials</div>
                )}
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
