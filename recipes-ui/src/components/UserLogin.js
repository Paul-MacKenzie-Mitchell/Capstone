// import { useState, useEffect, useContext } from 'react';
// import { baseUrl } from '../shared';
// import { useLocation, useNavigate } from 'react-router-dom';
// import { LoginContext } from '../App';

// export default function UserLogin() {
//     const [loggedIn, setLoggedIn] = useContext(LoginContext);
//     const [username, setUsername] = useState();
//     const [password, setPassword] = useState();

//     const location = useLocation();
//     const navigate = useNavigate();

//     function login(e) {
//         e.preventDefault();
//         const url = baseUrl + 'api/token/';
//         fetch(url, {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json',
//             },
//             body: JSON.stringify({
//                 username: username,
//                 password: password,
//             }),
//         })
//             .then((response) => {
//                 return response.json();
//             })
//             .then((data) => {
//                 localStorage.setItem('access', data.access);
//                 localStorage.setItem('refresh', data.refresh);
//                 setLoggedIn(true);
//                 navigate(
//                     location?.state?.previousUrl
//                         ? location.state.previousUrl
//                         : '/contact'
//                 );
//             });
//     }

//     return (
//         <form className="m-2 w-full max-w-sm" id="customer" onSubmit={login}>
//             <div className="md:flex md:items-center mb-6">
//                 <div className="md:w-1/4">
//                     <label for="username">Username</label>
//                 </div>

//                 <div className="md:w-3/4">
//                     <input
//                         className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
//                         id="username"
//                         type="text"
//                         value={username}
//                         onChange={(e) => {
//                             setUsername(e.target.value);
//                         }}
//                     />
//                 </div>
//             </div>

//             <div className="md:flex md:items-center mb-6">
//                 <div className="md:w-1/4">
//                     <label for="password">Password</label>
//                 </div>

//                 <div className="md:w-3/4">
//                     <input
//                         id="password"
//                         className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
//                         type="password"
//                         value={password}
//                         onChange={(e) => {
//                             setPassword(e.target.value);
//                         }}
//                     />
//                 </div>
//             </div>
//             <button className="bg-purple-600 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded">
//                 Login
//             </button>
//         </form>
//     );
// }



/*
  This example requires some changes to your config:
  
  ```
  // tailwind.config.js
  module.exports = {
    // ...
    plugins: [
      // ...
      require('@tailwindcss/forms'),
    ],
  }
  ```
*/
import { LockClosedIcon } from '@heroicons/react/outline'

export default function UserLogin() {
  return (
    <>
      {/*
        This example requires updating your template:

        ```
        <html class="h-full bg-gray-50">
        <body class="h-full">
        ```
      */}
      <div className="flex min-h-full items-center justify-center px-4 py-12 sm:px-6 lg:px-8">
        <div className="w-full max-w-md space-y-8">
          <div>
            <img
              className="mx-auto h-12 w-auto"
              src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
              alt="Your Company"
            />
            <h2 className="mt-6 text-center text-3xl font-bold tracking-tight text-gray-900">
              Sign in to your account
            </h2>
            <p className="mt-2 text-center text-sm text-gray-600">
              Or{' '}
              <a href="#" className="font-medium text-indigo-600 hover:text-indigo-500">
                start your 14-day free trial
              </a>
            </p>
          </div>
          <form className="mt-8 space-y-6" action="#" method="POST">
            <input type="hidden" name="remember" defaultValue="true" />
            <div className="-space-y-px rounded-md shadow-sm">
              <div>
                <label htmlFor="email-address" className="sr-only">
                  Email address
                </label>
                <input
                  id="email-address"
                  name="email"
                  type="email"
                  autoComplete="email"
                  required
                  className="relative block w-full rounded-t-md border-0 py-1.5 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                  placeholder="Email address"
                />
              </div>
              <div>
                <label htmlFor="password" className="sr-only">
                  Password
                </label>
                <input
                  id="password"
                  name="password"
                  type="password"
                  autoComplete="current-password"
                  required
                  className="relative block w-full rounded-b-md border-0 py-1.5 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                  placeholder="Password"
                />
              </div>
            </div>

            <div className="flex items-center justify-between">
              <div className="flex items-center">
                <input
                  id="remember-me"
                  name="remember-me"
                  type="checkbox"
                  className="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-600"
                />
                <label htmlFor="remember-me" className="ml-2 block text-sm text-gray-900">
                  Remember me
                </label>
              </div>

              <div className="text-sm">
                <a href="#" className="font-medium text-indigo-600 hover:text-indigo-500">
                  Forgot your password?
                </a>
              </div>
            </div>

            <div>
              <button
                type="submit"
                className="group relative flex w-full justify-center rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
              >
                <span className="absolute inset-y-0 left-0 flex items-center pl-3">
                  <LockClosedIcon className="h-5 w-5 text-indigo-500 group-hover:text-indigo-400" aria-hidden="true" />
                </span>
                Sign in
              </button>
            </div>
          </form>
        </div>
      </div>
    </>
  )
}
