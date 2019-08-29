<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
  <head>
  <title>StiCast! - My Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script  src="<c:url value="/js/index.js" />"></script>

    <!------------ INCLUDE NAVBAR ------------>
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
  
    <div class="alert alert-success" role="alert" id="success" style="display:none; width: 100%; position:fixed; bottom: 0; text-align: center; z-index: 5"> Profile succesfully updated! </div>
      <div class="alert alert-danger" role="alert" id="error" style="display:none; width: 100%; position:fixed; bottom: 0; text-align: center; z-index: 5"> Username or Email already taken! </div>
     
      <div class="container">
        <div class="row">
          <div class="col-md-3">
            <div class="list-group">
              <a href="${pageContext.request.contextPath}/profile" class="list-group-item list-group-item-action active">Edit Profile</a>
              <a href="${pageContext.request.contextPath}/following" class="list-group-item list-group-item-action" id="following">Following Questions</a>
              <a href="${pageContext.request.contextPath}/forecasts" class="list-group-item list-group-item-action" id="forecasts">Forecasts History</a>
            </div>
          </div>
        <div class="col-md-9" style="padding-left: 10%;padding-right: 10%;">
          <div class="card">
            <div class="card-body">
              <div class="row">
                <div class="col-md-12">
                  <form>
                    <div class="form-group row">
                      <label for="username" class="col-4 col-form-label">Username</label>
                      <div class="col-8">
                        <input id="username" name="newUsername" value="${requestScope.username}" class="form-control here" type="text">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="newpass" class="col-4 col-form-label">Password</label>
                      <div class="col-8">
                        <input id="pass" name="newPassword" value="${requestScope.password}" class="form-control here" type="password" maxlength="45">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="name" class="col-4 col-form-label">Name</label>
                      <div class="col-8">
                        <input id="name" name="newName" value="${requestScope.name}" class="form-control" type="text">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="email" class="col-4 col-form-label">Email</label>
                      <div class="col-8">
                        <input id="email" name="newEmail" value="${requestScope.email}" class="form-control" type="email">
                      </div>
                    </div>
                    <div class="form-group row" style="margin-top: 25px;">
                      <div class="offset-4 col-8">
                        <button id="edit" class="btn btn-primary" type="submit">Update Profile</button>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <%@ include file="footbar.jsp" %>
  </body>
</html>