import React, { Component } from 'react';

import './App.css';

import PokemonColorSelection from './PokemonColorSelection';
import PokemonTable from './PokemonTable';
import { ModalContainer, ModalDialog } from 'react-modal-dialog';

import http from './http/index';
import lodash from 'lodash';

class App extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      initialized: false,
      isShowingModal: false,
      pokemons: [],
      pokemonColors: [],
      selectedColor: 'NO_COLOR',
      selectedPokemonForPopup: null
    };

    this.colorChangeHandler = this.colorChangeHandler.bind(this);
    this.deletePokemonHandler = this.deletePokemonHandler.bind(this);
    this.handlePokemonDetailsClick = this.handlePokemonDetailsClick.bind(this);
  }

  componentDidMount() {
    this.getCompleteState();
  }

  getCompleteState() {

    http.getJson('http://localhost:8080/pokemon-app/pokemonList')
      .then((responseAsJson) => {

        const pokemons = responseAsJson.pokemons;

        const pokemonColors = pokemons.map(pokemon => {
          return pokemon.color;
        });

        const uniquePokemonColors = lodash.uniq(pokemonColors);

        this.setState(
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

  deletePokemonHandler() {
    http.deleteJson(`http://localhost:8080/pokemon-app/deletePokemon/${this.state.selectedPokemonForPopup.id}`)
      .then(() => {
        this.getCompleteState();
      });

    this.setState({ isShowingModal: false });
  }

  handlePokemonDetailsClick(pokemon) {
    this.setState({ isShowingModal: true, selectedPokemonForPopup: pokemon });
  }

  render() {

    const {
      pokemons,
      pokemonColors,
      selectedColor,
      initialized,
      isShowingModal,
      selectedPokemonForPopup
    } = this.state;

    if (!initialized) {
      return (<div>Loading...</div>);
    }

    let pokemonByColors = pokemons;
    if (selectedColor !== 'NO_COLOR') {
      pokemonByColors = pokemons.filter(pokemon => {
        return pokemon.color === selectedColor;
      });
    }

    let handleClick = () => this.setState({ isShowingModal: true });
    let handleClose = () => this.setState({ isShowingModal: false });

    return (
      <div className="App">
        <h1>Pokemon List</h1>

        <h2>Filter pokemons by color</h2>

        <PokemonColorSelection pokemonColors={pokemonColors} onChangeHandler={this.colorChangeHandler} />
        <PokemonTable pokemons={pokemonByColors} handlePokemonDetailsClick={this.handlePokemonDetailsClick} />

        <div onClick={handleClick}>
          {
            isShowingModal &&
            <ModalContainer onClose={handleClose}>
              <ModalDialog onClose={handleClose} dismissOnBackgroundClick={false}>
                <h1>Pokemon Details</h1>
                <table>
                  <tbody>
                  <tr>
                    <td>ID</td>
                    <td>{selectedPokemonForPopup.id}</td>
                  </tr>
                  <tr>
                    <td>Name</td>
                    <td>
                      <input type="text" value={selectedPokemonForPopup.name} />
                    </td>
                  </tr>
                  <tr>
                    <td>Type</td>
                    <td>
                      <input type="text" value={selectedPokemonForPopup.type} />
                    </td>
                  </tr>
                  <tr>
                    <td>Color</td>
                    <td>
                      <input type="color" value={selectedPokemonForPopup.color} />
                    </td>
                  </tr>
                  </tbody>
                </table>

                <div className="pokemon-detail">
                  <button onClick={handleClose}>OK</button>
                  <button>Update</button>
                  <button onClick={this.deletePokemonHandler}>Delete</button>
                </div>
              </ModalDialog>
            </ModalContainer>
          }
        </div>


      </div>
    );
  }
}

export default App;
