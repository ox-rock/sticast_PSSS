<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
  <head>
    <title>StiCast! - Questions</title>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/tables.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/questions.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
    <!----------------- TOP NAVBAR ----------------->
    <%@ include file="navbar.jsp" %>
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
  
    <!------------ SELECT CATEGORY FORM ------------>
    <div align="center" style="width:20%; margin:auto;"> 
      <form action="${pageContext.request.contextPath}/questions/all" method="POST" id="form1">  
        <select name ="category" id="selectCategory" style="height:27px; width: 150px">
          <option  value="all">All questions</option>
          <c:forEach items="${categoriesList}" var="data" varStatus="item"> 
            <option  value="${data.name}">${data.name}</option>
          </c:forEach> 
        </select>
        <button class="button-choose"><span>Choose</span></button>
      </form>
    </div>

    <!------------ QUESTIONS TABLE ------------>
    <div class="table-questions-title" align="center" style="font-family: Roboto; text-transform:uppercase; margin-bottom: 30px; margin-top: 40px"> <h3>- ${requestScope.category} -</h3> </div>
    <div style="padding-bottom: 100px;">
      <table class="table-questions" >
        <thead>
          <tr>
            <th class="text-left"><b>Questions</b></th>
            <th class="text-center"><b>Expiration date</b></th>
            <th class="text-center"><b>Status</b></th>  
          </tr>
        </thead>
        <tbody class="table-hover">
          <c:forEach items="${questionsList}" var="data" varStatus="item">
            <c:if test="${not item.first}">
            <tr>
              <td class="text-justify"><a href="${pageContext.request.contextPath}/question/${data.id}" id="${data.id}">${data.text} </a></td>
              <td class="text-center">${data.expirationDate}</td>
              <c:choose>
                <c:when test="${data.isOpen == 1}">
                  <td class="text-center">Open</td>
                </c:when>    
                <c:otherwise>
                  <td class="text-center">Closed</td>
                </c:otherwise>
              </c:choose>
            </tr>
          </c:if>  
          </c:forEach> 
        </tbody>
      </table>
    </div>
    
    <%@ include file="footbar.jsp" %>
    <script type="text/javascript" src="<c:url value="/js/index.js" />"></script>
    <script type="text/javascript">
      document.getElementById('selectCategory').onchange = function(){
        document.getElementById('form1').action = '${pageContext.request.contextPath}/questions/'+this.value;
      }
    </script>
  </body>
</html>