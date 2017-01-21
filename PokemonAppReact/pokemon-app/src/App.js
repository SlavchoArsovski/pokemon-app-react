import React, { Component } from 'react';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <h1>Pokemon List</h1>

        <h2>Filter pokemons by color</h2>

        <div id="pokemonSelectionHolder">
          <select name="pokemon-color-selection" id="pokemon-color-selection">
            <option value="NO_COLOR" data-class="pokemon-select-color-icon" selected>Select Color</option>
          </select>
        </div>

        <div className="pokemon-list">

          <table className="pokemon-overview-table">

            <tr>
              <th>Pokemon ID</th>
              <th>Name</th>
              <th>Type</th>
              <th>Color</th>
            </tr>

            <tr  className="pokemon-row">
              <td className="pokemonId">10000</td>
              <td className="pokemonName">Pikachu</td>
              <td className="pokemonType">Electric</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#ffff00' }}>></div>
                <div className="pokemonColorHolder">#ffff00</div>
              </td>
            </tr>
            <tr  className="pokemon-row">
              <td className="pokemonId">10001</td>
              <td className="pokemonName">Magby</td>
              <td className="pokemonType">Fire</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#ff0000' }}></div>
                <div className="pokemonColorHolder">#ff0000</div>
              </td>
            </tr>

            <tr  className="pokemon-row">
              <td className="pokemonId">10002</td>
              <td className="pokemonName">Buzzwole</td>
              <td className="pokemonType">Fighting</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#ff0000' }}></div>
                <div className="pokemonColorHolder">#ff0000</div>
              </td>
            </tr>
            <tr  className="pokemon-row">
              <td className="pokemonId">10003</td>
              <td className="pokemonName">Gloom</td>
              <td className="pokemonType">Poison</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#0000ff' }}></div>
                <div className="pokemonColorHolder">#0000ff</div>
              </td>
            </tr>
            <tr  className="pokemon-row">
              <td className="pokemonId">10004</td>
              <td className="pokemonName">Larvitar</td>
              <td className="pokemonType">Ground</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#ffff00' }}></div>
                <div className="pokemonColorHolder">#ffff00</div>
              </td>
            </tr>
            <tr  className="pokemon-row">
              <td className="pokemonId">10005</td>
              <td className="pokemonName">Yamask</td>
              <td className="pokemonType">Ghost</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#ffffff' }}></div>
                <div className="pokemonColorHolder">#ffffff</div>
              </td>
            </tr>
            <tr  className="pokemon-row">
              <td className="pokemonId">10006</td>
              <td className="pokemonName">Raichu</td>
              <td className="pokemonType">Electric</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#ffff00' }}></div>
                <div className="pokemonColorHolder">#ffff00</div>
              </td>
            </tr>
            <tr className="pokemon-row">
              <td className="pokemonId">10007</td>
              <td className="pokemonName">Venonat</td>
              <td className="pokemonType">Bug</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#800080' }}></div>
                <div className="pokemonColorHolder">#800080</div>
              </td>
            </tr>
            <tr  className="pokemon-row">
              <td className="pokemonId">10008</td>
              <td className="pokemonName">Jangmo-o</td>
              <td className="pokemonType">Dragon</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#808080' }}></div>
                <div className="pokemonColorHolder">#808080</div>
              </td>
            </tr>
            <tr  className="pokemon-row">
              <td className="pokemonId">10009</td>
              <td className="pokemonName">Volcarona</td>
              <td className="pokemonType">Fire</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#ffffff' }}></div>
                <div className="pokemonColorHolder">#ffffff</div>
              </td>
            </tr>
            <tr  className="pokemon-row">
              <td className="pokemonId">10010</td>
              <td className="pokemonName">Slowking</td>
              <td className="pokemonType">Fire</td>
              <td className="pokemonColor">
                <div style={ { backgroundColor: '#ffc0cb' }}></div>
                <div className="pokemonColorHolder">#ffc0cb</div>
              </td>
            </tr>


          </table>

        </div>

        <div className="pokemon-detail">
          <h1>Pokemon Detail</h1>

          <table>
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
              <td colspan="2">
                <div id="pokemonDetailId" />
              </td>
            </tr>

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
