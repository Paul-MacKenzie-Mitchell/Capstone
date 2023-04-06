import { useState, Fragment } from "react";
import { Combobox, Transition } from "@headlessui/react";
import { CheckIcon } from "@heroicons/react/solid";

export default function CBox({ array, name, id }) {
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
    <div className="md:col-span-5">
      <select
        id="countries"
        value={selectedElement}
        by="id"
        onChange={setSelectedElement}
        className="form-control h-10 border mt-1 rounded px-4 w-full bg-gray-50"
      >
        <option selected></option>
        {filteredElements.map((element) => (
          <option key={element[id]} value={element}>
            {element[name]}
          </option>
        ))}
      </select>

      <Combobox value={selectedElement} by="id" onChange={setSelectedElement}>
        <Combobox.Input
          type="text"
          name="meaurementUnit"
          id="meaurementUnit"
          className="form-control h-10 border mt-1 rounded px-4 w-full bg-gray-50"
          value={query}
          displayValue={selectedElement[name]}
          required
          placeholder=""
          onChange={(event) => setQuery(event.target.value)}
        />
        <Transition
          enter="transition duration-100 ease-out"
          enterFrom="transform scale-95 opacity-0"
          enterTo="transform scale-100 opacity-100"
          leave="transition duration-75 ease-out"
          leaveFrom="transform scale-100 opacity-100"
          leaveTo="transform scale-95 opacity-0"
        ></Transition>
        <Combobox.Options className="py-2 text-sm">
          {query.length > 0 && (
            <Combobox.Option value={{ id: null, name: query }}>
              Create "{query}"
            </Combobox.Option>
          )}
          {filteredElements.map((element) => (
            <Combobox.Option key={element[id]} value={element}>
              <CheckIcon className="hidden ui-selected:block" />
              {element[name]}
            </Combobox.Option>
          ))}
        </Combobox.Options>
      </Combobox>
    </div>
  );
}
