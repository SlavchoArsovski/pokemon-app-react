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

<h1>Pokemon List</h1>

<table class="pokemon-overview-table">
  <tr>
    <th>Pokemon ID</th>
    <th>Name</th>
    <th>Type</th>
    <th>Color</th>
  </tr>

  <c:forEach items="${viewBean.pokemons}" var="pokemon">
    <tr>
      <td>${pokemon.id}</td>
      <td>${pokemon.name}</td>
      <td>${pokemon.type}</td>
      <td class="pokemon-color">
        <div style="background-color: ${pokemon.color}"></div>
      </td>
    </tr>
  </c:forEach>

</table>


</body>
</html>