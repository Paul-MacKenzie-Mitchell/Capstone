import { useState } from "react";
import { Combobox } from "@headlessui/react";

export default function MyCombobox({ array, id, name }) {
  const [selectedElement, setSelectedElement] = useState(array[0]);
  const [query, setQuery] = useState("");

  const filteredElements =
    query === ""
      ? array
      : array.filter((array) => {
          return array.toLowerCase().includes(query.toLowerCase());
        });

  return (
    <Combobox value={selectedElement} onChange={setSelectedElement}>
      <Combobox.Input onChange={(event) => setQuery(event.target.value)} />
      <Combobox.Options>
        {filteredElements.map((element) => (
          <Combobox.Option key={element} value={element}>
            {element}
          </Combobox.Option>
        ))}
      </Combobox.Options>
    </Combobox>
  );
}

// const people = [
//   "Wade Cooper",
//   "Arlene McCoy",
//   "Devon Webb",
//   "Tom Cook",
//   "Tanya Fox",
//   "Hellen Schmidt",
// ];

// export default function MyCombobox() {
//   const [selectedPerson, setSelectedPerson] = useState(people[0]);
//   const [query, setQuery] = useState("");

//   const filteredPeople =
//     query === ""
//       ? people
//       : people.filter((person) => {
//           return person.toLowerCase().includes(query.toLowerCase());
//         });

//   return (
//     <Combobox value={selectedPerson} onChange={setSelectedPerson}>
//       <Combobox.Input onChange={(event) => setQuery(event.target.value)} />
//       <Combobox.Options>
//         {filteredPeople.map((person) => (
//           <Combobox.Option key={person} value={person}>
//             {person}
//           </Combobox.Option>
//         ))}
//       </Combobox.Options>
//     </Combobox>
//   );
// }
