<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<spring:url value="/css/main.css" var="mainCss" />
<spring:url value="/css/jquery-ui.min.css" var="jqueryCss" />
<spring:url value="/js/jquery-3.1.0.min.js" var="jqueryJs" />
<spring:url value="/js/jquery-ui.min.js" var="jqueryUiJs" />
<spring:url value="/js//home/homeView.js" var="homeView" />
<spring:url value="/js/home/homeModel.js" var="homeModel" />
<spring:url value="/js/home/homeController.js" var="homeController" />

<html>
<head>

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <link href="${mainCss}" rel="stylesheet" />
  <link href="${jqueryCss}" rel="stylesheet" />
  <script src="${jqueryJs}" type="application/javascript"></script>
  <script src="${jqueryUiJs}" type="application/javascript"></script>
  <script src="${homeView}" type="application/javascript"></script>
  <script src="${homeModel}" type="application/javascript"></script>
  <script src="${homeController}" type="application/javascript"></script>
<body>

<script>
  pokemonApp = {
    conf : {
      contextPath: '${SERVLET_CONTEXT_PATH}'
    }
  }
</script>

<sec:authorize access="hasRole('ROLE_USER')">
  <!-- For login user -->
  <c:url value="/logout" var="logoutUrl"/>
  <form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
  </form>
  <script>
    function formSubmit() {
      document.getElementById("logoutForm").submit();
    }
  </script>

  <c:if test="${pageContext.request.userPrincipal.name != null}">
    <div>
      User: ${pageContext.request.userPrincipal.name} |
      <a href="javascript:formSubmit()">
        Logout
      </a>
    </div>
  </c:if>


</sec:authorize>

<h1>Pokemon List</h1>

<h2>Filter pokemons by color</h2>

<div id="pokemonSelectionHolder">
  <select name="pokemon-color-selection" id="pokemon-color-selection">
    <option value="NO_COLOR" data-class="pokemon-select-color-icon" selected>Select Color</option>
  </select>
</div>

<div style="clear: both;" />

<div class="pokemon-list">

  <table class="pokemon-overview-table">

    <tr>
      <th>Pokemon ID</th>
      <th>Name</th>
      <th>Type</th>
      <th>Color</th>
    </tr>

    <!-- template row -->
    <tr style="display: none;" class="pokemon-row-template">
      <td class="pokemonId">{pokemon.id}</td>
      <td class="pokemonName">{pokemon.name}</td>
      <td class="pokemonType">{pokemon.type}</td>
      <td class="pokemonColor">
        <!-- <div style="background-color:{pokemon.color};"></div> -->
        <div class="pokemonColorHolder">{pokemon.color}</div>
      </td>
    </tr>

    <!-- end of template row -->

    <c:forEach items="${viewBean.pokemons}" var="pokemon">
      <tr class="pokemon-row" data-deletable="${pokemon.deletable}">
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


</body>
</html>