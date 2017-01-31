import React from 'react';

import PokemonTableRow from './PokemonTableRow';

function PokemonTable(props) {
  const {
    pokemons,
    handlePokemonDetailsClick
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
            <PokemonTableRow key={pokemon.id} {...pokemon} handlePokemonDetailsClick={handlePokemonDetailsClick} />
          )
        }

        </tbody>
      </table>

    </div>);
}

export default PokemonTable;
