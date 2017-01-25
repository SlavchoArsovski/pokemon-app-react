import React, { Component } from 'react';
import './App.css';

import PokemonTable from './PokemonTable';

import http from './http/index';
import lodash from 'lodash';

class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      pokemons: [],
      pokemonColors: []
    };
  }

  componentDidMount() {
    const me = this;

    http.getJson('http://localhost:8080/pokemon-app/pokemonList')
      .then((responseAsJson) => {

        const pokemons = responseAsJson.pokemons;

        me.setState({ pokemons });

        const pokemonColors = pokemons.map(pokemon => {
          return pokemon.color
        });
        const uniquePokemonColors = lodash.uniq(pokemonColors);

        me.setState({ pokemonColors: uniquePokemonColors });
      });
  }

  render() {

    const {
      pokemons,
      pokemonColors
    } = this.state;

    if (!pokemons) {
      return (<div>Loading...</div>);
    }

    return (
      <div className="App">
        <h1>Pokemon List</h1>

        <h2>Filter pokemons by color</h2>

        <div id="pokemonSelectionHolder">
          <select name="pokemon-color-selection" id="pokemon-color-selection">
            <option value="NO_COLOR" data-class="pokemon-select-color-icon">Select Color</option>
            {
              pokemonColors.map((pokemonColor, index) =>
                <option value={pokemonColor}>{pokemonColor}</option>
              )
            }
          </select>
        </div>

        <PokemonTable pokemons={pokemons} />

        <div className="pokemon-detail">
          <h1>Pokemon Detail</h1>

          <table>
            <tbody>
            <tr>
              <td>Name</td>
              <td><input id="pokemonDetailName" type="text" /></td>
            </tr>
            <tr>
              <td>Type</td>
              <td><input id="pokemonDetailType" type="text" /></td>
            </tr>
            <tr>
              <td>Color</td>
              <td><input id="pokemonDetailColor" type="color" /></td>
            </tr>
            <tr>
              <td style={{ colSpan: 2 }}>
                <div id="pokemonDetailId" />
              </td>
            </tr>
            </tbody>
          </table>

          <button id="addPokemonBtn">Add</button>
          <button id="updatePokemonBtn">Update</button>
          <button id="deletePokemonBtn">Delete</button>

        </div>

      </div>
    );
  }
}

export default App;
