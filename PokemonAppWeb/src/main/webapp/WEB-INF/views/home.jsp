<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<spring:url value="/css/main.css" var="mainCss" />
<spring:url value="/js/jquery-3.1.0.min.js" var="jqueryJs" />
<spring:url value="/js//home/homeView.js" var="homeView" />
<spring:url value="/js/home/homeModel.js" var="homeModel" />
<spring:url value="/js/home/homeController.js" var="homeController" />

<html>
<head>

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <link href="${mainCss}" rel="stylesheet" />
  <script src="${jqueryJs}" type="application/javascript"></script>
  <script src="${homeView}" type="application/javascript"></script>
  <script src="${homeModel}" type="application/javascript"></script>
  <script src="${homeController}" type="application/javascript"></script>

<body>

<div class="pokemon-list">
  <h1>Pokemon List</h1>

  <table class="pokemon-overview-table">
    <tr>
      <th>Pokemon ID</th>
      <th>Name</th>
      <th>Type</th>
      <th>Color</th>
    </tr>

    <c:forEach items="${viewBean.pokemons}" var="pokemon">
      <tr id="pokemon-row-${pokemon.id}" class="pokemon-row">
        <td class="pokemonId">${pokemon.id}</td>
        <td class="pokemonName">${pokemon.name}</td>
        <td class="pokemonType">${pokemon.type}</td>
        <td class="pokemonColor">
          <div style="background-color: ${pokemon.color}"></div>
          <div class="pokemonColorHolder">${pokemon.color}</div>
        </td>
      </tr>
    </c:forEach>


  </table>

</div>

<div class="pokemon-detail">
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
      <td><input id="pokemonDetailColor" type="text" /></td>
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


</body>
</html>