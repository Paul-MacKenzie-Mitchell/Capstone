import { useState, Fragment } from "react";
import { Combobox, Transition } from "@headlessui/react";
import { CheckIcon } from "@heroicons/react/solid";

export default function DropDownWithSearch({ array, name, id }) {
  const [selectedElement, setSelectedElement] = useState(array[0]);
  const [query, setQuery] = useState("");

  const filteredElements =
    query === ""
      ? array
      : array.filter((item) => {
          console.log(item);
          return item[name].toLowerCase().includes(query.toLowerCase());
        });

  return (
    <>
      <select
        id="countries"
        value={selectedElement}
        by="id"
        onChange={setSelectedElement}
        className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
      >
        <option selected>Choose a country</option>
        {filteredElements.map((element) => (
          <option key={element[id]} value={element}>
            {element[name]}
          </option>
        ))}
      </select>
    </>
  );
}
