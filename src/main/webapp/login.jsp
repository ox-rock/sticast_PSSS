<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html lang="en">
  <head>
    <title>StiCast! - Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/forms.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script  src="<c:url value="/js/index.js" />"></script>
    <!-- NAVBAR -->
    <%@include file="navbar.jsp" %>  
	<c:choose>
      <c:when test="${not empty sessionScope.username}">   
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
    <!-- LOGIN FORM -->
    <div class="user">
      <header class="user__header">
        <h1 class="user__title"><b>StiCast!</b></h1>
        <h1 class="user__title2">Login form</h1><br>
      </header>     
      <form:form class="form_login" action="${pageContext.request.contextPath}/login" method="post" modelAttribute="account">  
        <c:if test="${LoginResult == 'wrongPassword'}">
          <div class="form_error">Ops! Wrong password</div>
        </c:if>  
        <c:if test="${LoginResult == 'wrongUsername'}">
          <div class="form_error">Ops! Username not found</div>
        </c:if>  
        <div class="form__group"><input type="text" placeholder="Username" class="form__input" name="username" id="username" required/></div>
        <div class="form__group"><input type="password" placeholder="Password" class="form__input" name="password" id="password" required/></div>
        <button class="btn" type="submit" name="op" value ="login">Log in</button>
      </form:form>
    </div>
    <script src="/js/index.js"></script>
	<%@ include file="footbar.jsp" %>
  </body>
</html>