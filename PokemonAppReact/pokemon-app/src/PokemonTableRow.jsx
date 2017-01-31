import React from 'react';

class PokemonTableRow extends React.Component {

  constructor(props) {
    super(props);

  }

  render() {
    const {
      id,
      name,
      type,
      color,
      handlePokemonDetailsClick
    } = this.props;

    const detailsBtnHandler = handlePokemonDetailsClick.bind(this, { id, name, type, color });

    return (
      <tr key={id} className="pokemon-row">
        <td className="pokemonId">{id}</td>
        <td className="pokemonName">{name}</td>
        <td className="pokemonType">{type}</td>
        <td className="pokemonColor">
          <div style={ { backgroundColor: color }} />
          <div className="pokemonColorHolder">{color}</div>
        </td>
        <td><button onClick={detailsBtnHandler}>Details</button></td>
      </tr>);
  }
}

export default PokemonTableRow;
