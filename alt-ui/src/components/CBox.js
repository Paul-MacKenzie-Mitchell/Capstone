import { useState } from "react";
import { Combobox } from "@headlessui/react";

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
      {/* {console.log(filteredElements)}
      {console.log(array)} */}

      <Combobox value={selectedElement} onChange={setSelectedElement}>
        <Combobox.Input
          type="text"
          name="meaurementUnit"
          id="meaurementUnit"
          className="form-control h-10 border mt-1 rounded px-4 w-full bg-gray-50"
          value={query}
          required
          placeholder=""
          onChange={(event) => setQuery(event.target.value)}
        />
        <Combobox.Options>
          {filteredElements.map((element) => (
            <Combobox.Option
              key={element[id]}
              value={element}
              className="ui-active:bg-blue-500 ui-active:text-white ui-not-active:bg-white ui-not-active:text-black"
            >
              {/* <CheckIcon className="hidden ui-selected:block" /> */}
              {element[name]}
            </Combobox.Option>
          ))}
        </Combobox.Options>
      </Combobox>
    </div>
  );
}
