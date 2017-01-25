import React, { Component } from 'react';
import './App.css';


import http from 'http';

import mockModel from './mockModel';
import PokemonTable from './PokemonTable';

class App extends Component {

  componentDidMount() {
    http.get('http://localhost:8080/pokemon-app/pokemonList', function(response) {
      console.log(response);
    });
  }

  render() {
    return (
      <div className="App">
        <h1>Pokemon List</h1>

        <h2>Filter pokemons by color</h2>

        <div id="pokemonSelectionHolder">
          <select name="pokemon-color-selection" id="pokemon-color-selection">
            <option value="NO_COLOR" data-class="pokemon-select-color-icon">Select Color</option>
          </select>
        </div>

        <PokemonTable pokemons={mockModel.pokemons} />

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
