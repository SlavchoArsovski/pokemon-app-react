import React from 'react';

function PokemonTableRow(props) {
  const {
    id,
    name,
    type,
    color,
    deletePokemonHandler
  } = props;

  const deleteBtnClickHandler = deletePokemonHandler.bind(this, id);

  return (
    <tr key={id} className="pokemon-row">
      <td className="pokemonId">{id}</td>
      <td className="pokemonName">{name}</td>
      <td className="pokemonType">{type}</td>
      <td className="pokemonColor">
        <div style={ { backgroundColor: color }} />
        <div className="pokemonColorHolder">{color}</div>
      </td>
      <td>
        <button onClick={deleteBtnClickHandler}>Delete</button>
      </td>
    </tr>);
}

export default PokemonTableRow;
