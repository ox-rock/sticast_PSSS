<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
  <head>
    <title>StiCast! - Welcome</title>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- TOP NAVBAR -->
	<%@include file="navbar.jsp" %>
	<c:choose>
	  <c:when test="${not empty sessionScope.username}">
	    <script>$("#questionsList").toggleClass('show');</script>
	  </c:when>
	  <c:otherwise>
	    <script>
	      $("#nav_signin").toggleClass('show');
		  $("#nav_signup").toggleClass('show');
		  $("#user_menu").hide();
		</script>
	  </c:otherwise>
	</c:choose>
  </head>
  <body>
    <h2 align="center">Welcome on StiCast!</h2>
    <div class="container-fluid text-center">
      <p>StiCast is a forecasting platform where payoffs are tied to the outcomes of future events. Participants trade contracts associated to the occurrence of a given event. The market exchange of contracts determines their price: in general, the higher the price of a contract, the higher the confidence of the market in the future occurrence of the associated event. </p>
      <img style="margin-bottom: 5%; margin-left: 15%; width: 60%;"  src="<c:url value="/img/intro.png" />">
    </div>
	<%@ include file="/footbar.jsp" %>
	<script src="<c:url value="/js/index.js" />"></script>
  </body>
</html>