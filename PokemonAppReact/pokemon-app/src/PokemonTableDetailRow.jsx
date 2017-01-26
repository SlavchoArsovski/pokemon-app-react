import React from 'react';

function PokemonTableDetailRow(props) {
  const {
    id,
    name,
    type,
    color
  } = props;

  return (
    <tr className="pokemon-row">
      <td className="pokemonId">{id}</td>
      <td className="pokemonName">{name}</td>
      <td className="pokemonType">{type}</td>
      <td className="pokemonColor">
        <div style={ { backgroundColor: color }} />
        <div className="pokemonColorHolder">{color}</div>
      </td>
    </tr>);
}

export default PokemonTableDetailRow;
