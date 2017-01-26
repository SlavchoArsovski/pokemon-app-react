import React from 'react';

import PokemonTableRow from './PokemonTableRow';
import PokemonTableDetailRow from './PokemonTableDetailRow';

function PokemonTable(props) {
  const {
    pokemons,
    deletePokemonHandler
  } = props;

  return (
    <div className="pokemon-list">

      <table className="pokemon-overview-table">

        <thead>
        <tr>
          <th>Pokemon ID</th>
          <th>Name</th>
          <th>Type</th>
          <th>Color</th>
        </tr>
        </thead>

        <tbody>
        {
          pokemons.map((pokemon, index) =>
            <PokemonTableRow key={pokemon.id} {...pokemon} deletePokemonHandler={deletePokemonHandler} />
          )
        }

        </tbody>
      </table>

    </div>);
}

export default PokemonTable;
