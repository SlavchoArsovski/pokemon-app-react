import React, { Component } from 'react';
import './App.css';

import PokemonTable from './PokemonTable';

import http from './http/index';
import lodash from 'lodash';

class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      initialized: false,
      pokemons: [],
      pokemonColors: [],
      selectedColor: 'NO_COLOR'
    };

    this.colorChangeHandler = this.colorChangeHandler.bind(this);
  }

  componentDidMount() {
    const me = this;

    http.getJson('http://localhost:8080/pokemon-app/pokemonList')
      .then((responseAsJson) => {

        const pokemons = responseAsJson.pokemons;

        const pokemonColors = pokemons.map(pokemon => {
          return pokemon.color;
        });

        const uniquePokemonColors = lodash.uniq(pokemonColors);

        me.setState(
          {
            initialized: true,
            pokemonColors: uniquePokemonColors,
            pokemons
          });
      });
  }

  colorChangeHandler(event) {
    this.setState({ selectedColor: event.target.value });
  }

  render() {

    const {
      pokemons,
      pokemonColors,
      selectedColor,
      initialized
    } = this.state;

    if (!initialized) {
      return (<div>Loading...</div>);
    }

    let pokemonByColors = pokemons;
    if (selectedColor !== 'NO_COLOR') {
      pokemonByColors = pokemons.filter(pokemon => { return pokemon.color === selectedColor});
    }

    return (
      <div className="App">
        <h1>Pokemon List</h1>

        <h2>Filter pokemons by color</h2>

        <div id="pokemonSelectionHolder">
          <select name="pokemon-color-selection" id="pokemon-color-selection" onChange={this.colorChangeHandler}>
            <option value="NO_COLOR">Select Color</option>
            {
              pokemonColors.map((pokemonColor, index) =>
                <option key={index} value={pokemonColor}>{pokemonColor}</option>
              )
            }
          </select>
        </div>

        <PokemonTable pokemons={pokemonByColors} />

        </div>
    );
  }
}

export default App;
