function DropDown() {
  return (
    <>
      <form className="w-full max-w-sm" id="dynamic-form">
        <div className="md:flex md:items-center mb-6">
          <div className="md:w-1/3">
            <label
              className="block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4"
              for="first_name"
            >
              First Name
            </label>
          </div>
          <div className="md:w-2/3">
            <input
              className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
              id="first_name"
              type="text"
              placeholder="Jane"
            />
          </div>
        </div>

        <div className="md:flex md:items-center mb-6">
          <div className="md:w-1/3">
            <label
              className="block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4"
              for="last_name"
            >
              Last Name
            </label>
          </div>
          <div className="md:w-2/3">
            <input
              className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
              id="last_name"
              type="text"
              placeholder="Doe"
            />
          </div>
        </div>

        <div className="dynamic-fields"></div>

        <div className="md:flex md:items-center">
          <div className="md:w-1/3"></div>
          <div className="md:w-2/3">
            <button
              className="shadow bg-purple-500 hover:bg-purple-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded"
              type="button"
              onclick="addField()"
            >
              Add Field
            </button>
            <button
              className="shadow bg-purple-500 hover:bg-purple-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded ml-4"
              type="submit"
            >
              Submit
            </button>
          </div>
        </div>
      </form>
    </>
  );
}

export default DropDown;

{
  /* // <div classNameName="min-h-screen p-10 bg-gray-100">
    //   <div classNameName="max-w-md mx-auto">
    //     <label for="select" classNameName="font-semibold block py-2">
    //       Select Input:
    //     </label>

    //     <div classNameName="relative">
    //       <div classNameName="h-10 bg-white flex border border-gray-200 rounded items-center">
    //         <input
    //           value="Javascript"
    //           name="select"
    //           id="select"
    //           classNameName="px-4 appearance-none outline-none text-gray-800 w-full"
    //           checked
    //         />

    //         <button classNameName="cursor-pointer outline-none focus:outline-none transition-all text-gray-300 hover:text-gray-600">
    //           <svg
    //             classNameName="w-4 h-4 mx-2 fill-current"
    //             xmlns="http://www.w3.org/2000/svg"
    //             viewBox="0 0 24 24"
    //             stroke="currentColor"
    //             stroke-width="2"
    //             stroke-linecap="round"
    //             stroke-linejoin="round"
    //           >
    //             <line x1="18" y1="6" x2="6" y2="18"></line>
    //             <line x1="6" y1="6" x2="18" y2="18"></line>
    //           </svg>
    //         </button>
    //         <label
    //           for="show_more"
    //           classNameName="cursor-pointer outline-none focus:outline-none border-l border-gray-200 transition-all text-gray-300 hover:text-gray-600"
    //         >
    //           <svg
    //             classNameName="w-4 h-4 mx-2 fill-current"
    //             xmlns="http://www.w3.org/2000/svg"
    //             viewBox="0 0 24 24"
    //             stroke="currentColor"
    //             stroke-width="2"
    //             stroke-linecap="round"
    //             stroke-linejoin="round"
    //           >
    //             <polyline points="18 15 12 9 6 15"></polyline>
    //           </svg>
    //         </label>
    //       </div>

    //       <input
    //         type="checkbox"
    //         name="show_more"
    //         id="show_more"
    //         classNameName="hidden peer"
    //         checked
    //       />
    //       <div classNameName="absolute rounded shadow bg-white overflow-hidden hidden peer-checked:flex flex-col w-full mt-1 border border-gray-200">
    //         <div classNameName="cursor-pointer group">
    //           <a classNameName="block p-2 border-transparent border-l-4 group-hover:border-blue-600 group-hover:bg-gray-100">
    //             Python
    //           </a>
    //         </div>
    //         <div classNameName="cursor-pointer group border-t">
    //           <a classNameName="block p-2 border-transparent border-l-4 group-hover:border-blue-600 border-blue-600 group-hover:bg-gray-100">
    //             Javascript
    //           </a>
    //         </div>
    //         <div classNameName="cursor-pointer group border-t">
    //           <a classNameName="block p-2 border-transparent border-l-4 group-hover:border-blue-600 group-hover:bg-gray-100">
    //             Node
    //           </a>
    //         </div>
    //         <div classNameName="cursor-pointer group border-t">
    //           <a classNameName="block p-2 border-transparent border-l-4 group-hover:border-blue-600 group-hover:bg-gray-100">
    //             PHP
    //           </a>
    //         </div>
    //       </div>
    //     </div>
    //   </div>
    // </div> */
}
