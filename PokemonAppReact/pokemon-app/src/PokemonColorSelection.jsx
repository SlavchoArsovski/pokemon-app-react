import React from 'react';

function PokemonColorSelection(props) {
  const {
    pokemonColors,
    onChangeHandler
  } = props;

  return (
    <div>
      <select onChange={onChangeHandler}>
        <option value="NO_COLOR">Select Color</option>
        {
          pokemonColors.map((pokemonColor, index) =>
            <option key={index} value={pokemonColor}>{pokemonColor}</option>
          )
        }
      </select>
    </div>
  );
}

export default PokemonColorSelection;
