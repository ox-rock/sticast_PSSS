<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
  <head>
    <title>StiCast! - ${question.get().text}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/questions.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <!--------------------- NAVBAR ------------------------->
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
    <div class="w3-container" align="center">
    
      <!--------------------- ADMIN PANEL --------------------->
      <c:choose>
        <c:when test="${sessionScope.isAdmin == true && question.get().isOpen == 1}">
          <form action="/StiCast/ServletCloseQuestion" method="POST"> <b>Hello admin! </b>Pick the winner and close the question: <br>
            <select name ="winnerType" id="selectWinner" style="margin-top:10px">
              <option value="yes">Yes</option>
              <option value="no">No</option>
            </select>
            <input type ="hidden" name="questionID" value="${question.get().id}"/>
            <button class="button" style="vertical-align:middle"><span>Close</span></button>
          </form>
        </c:when>    
      </c:choose>
      
      <h1 style="margin-bottom: 25px">${question.get().text}</h1>
      <div class="container" style="width:1050px">
        <div id="chartContainer" style="height: 370px; width: 45%; float:left "></div> 
        <c:choose>
          <c:when test="${question.get().isOpen == 1}">
            <div class="form_buysell">
              
              <div style="display: inline-block; margin-right: 10%"><p style="margin-top: 0%; font-size: 20px; ">Yes</p><p style="margin-top:-15px; font-size: 30px;" id="ys"><b>${yesQuantity}</b></p></div>
              <div style="display: inline-block; "><p style="margin-top: 0%; font-size: 20px;">No</p><p style="margin-top:-15px; font-size: 30px;" id="ns"><b>${noQuantity}</b></p></div>
              <ul class="tab-group">
                <li class="tab active"><a href="#buy">Buy</a></li>
                <li class="tab"><a href="#sell">Sell</a></li>
              </ul>      
              <div class="tab-content">
                <div id="buy">   
                
                  <form:form action="${pageContext.request.contextPath}/question/${question.get().id}" method="post" modelAttribute="forecast">
                    <table>
                      <tr>
                        <th>Type</th>
                        <th class="th2" id="th2"></th>
                        <th class="th3" style="display:none" id="th3"></th>
                        <th id="total_buy" style=" width: 120px">Total: 0$</th>
                      </tr>
                      <tr>
                        <td width="25%">
                          <form:select name="answer" id="select_buy" path="answer">
                            <form:option value="yes">Yes</form:option>
                            <form:option value="no">No</form:option>
                          </form:select>
                        </td>
                        <td class="td2" width="50%"><input id="in_qnt" type="number" name ="qnt1" min="1" max="" value="#" required /></td>
                        <td class="td3" style="display:none" width="50%"><input id ="in_qnt2" type="number" name ="qnt2" min="1" max="" value="#" disabled/></td>
   				        <td width="25%"><button class="button" style="vertical-align:middle"><span>Buy</span></button></td>
   				        <td>
  				          <input type ="hidden" name="buyOrSell" value="buy"/>
  				          <form:input path="quantity" id="boh" type ="hidden" value="#"/>
  				           <form:input path="question.id" type ="hidden" value="${question.get().id}"/>
  				            <form:input path="account.id" type ="hidden" value="${sessionScope.accountID}"/>
                        </td>
                      </tr>
                    </table>
                  </form:form>
                </div> 
                <div id="sell">  
                               
                  <form:form action="${pageContext.request.contextPath}/question/${question.get().id}" method="post" modelAttribute="forecast">
                    <table>
                      <tr>
                        <th>Type</th>
                        <th class="2th2" >Quantity (max ${yesQuantity})</th>
                        <th class="2th3" style="display:none">Quantity (max ${noQuantity})</th>
                        <th id="total_sell" style=" width: 120px">Total: 0$</th>
                      </tr>
                      <tr>
                        <td width="25%">
                          <form:select name="answer" id="select_sell" path="answer">
                            <form:option value="yes">Yes</form:option>
                            <form:option value="no">No</form:option>
                          </form:select>
                        </td>
                        <td class="2td2" width="50%"><input id="in_qnt3" type="number" name ="qnt1" min="1" max="${yesQuantity}" value="#" required /></td>
                        <td class="2td3" style="display:none" width="50%"><input id="in_qnt4" type="number" name ="qnt2" min="1" max="${noQuantity}" value="#" disabled /></td>
   		                <td width="25%"><button class="button" style="vertical-align:middle"><span>Sell</span></button></td>
   			            <td>
				          <input type ="hidden" name="buyOrSell" value="sell"/>
				          <form:input path="quantity" id="boh2" type ="hidden" value="#"/>
				          <form:input path="question.id" type ="hidden" value="${question.get().id}"/>
  				          <form:input path="account.id" type ="hidden" value="${sessionScope.accountID}"/>
                        </td>       
                      </tr>
                    </table>
                  </form:form>  
                </div> 
              </div> 
            </div>  
		  </c:when>
		  		     
          <c:otherwise>
            <p style="margin-top: 10%; font-size: 25px;">This question is</p><p style="margin-top:-20px; font-size: 40px;"><b>CLOSED!</b></p> 
          </c:otherwise>
        </c:choose>
        
        <!--------------------- COMMENTS --------------------->	
        <div class="container" style="margin: auto; width: 100%; margin-bottom: 65px;">
	      <div class="tab-pane" id="add-comment" style="margin-top: 15%; ">
            <form:form action="${pageContext.request.contextPath}/question/${question.get().id}/comments" method="post" class="form-horizontal" id="commentForm" role="form" modelAttribute="comment"> 
              <form:input type ="hidden" path="question.id" value="${question.get().id}"/>
              <form:input type ="hidden" path="account.id" value="${sessionScope.accountID}"/>
              <div class="form-group">
                <div class="col-sm-10" style="margin-left: 12%;">
                  <div style="float: left; width:79%">
                    <form:textarea class="form-control" path="text" name="addComment" id="addComment" placeholder="Write your comment here!" style="resize:none; float:left" maxlength="100"/>
                  </div>
                  <div style="float:right; height:12%">
                    <button class="btn btn-success btn-circle text-uppercase" type="submit" id="submitComment" style="height: 70px">Submit comment</button>
                  </div>
                </div>                          
              </div>         
            </form:form>
          </div>
          <div class="media-body">
            <c:forEach items="${commentsList}" var="data" varStatus="item"> 
              <div class="well" style="height: auto; ">
			    <h4 style="font-size:18px"><b>${data.account.username}</b> @ ${data.timestamp}</h4>
			    <p class="media-comment" style="font-size:15px;">${data.text}</p>
              </div>
	        </c:forEach>  
		  </div>                      
        </div>   
            
      </div> 
    </div> 
    <%@ include file="footbar.jsp" %>          	
  </body>
  <script type="text/javascript" src="<c:url value="/js/index.js" />"></script>	
  <script>
  
    <!-------------- PIE CHART ---------------->		
    window.onload = function () {
	  var options = {
        animationEnabled: true,
		backgroundColor: "#EAEBED", 
		data: [{
		  type: "doughnut",
		  innerRadius: "40%",
		  showInLegend: false,
		  legendText: "{label}",
		  indexLabel: "{label}: #percent%",
		  dataPoints: [
			{ label: "Yes", y: ${yesValue}},
			{ label: "No", y: ${noValue}}
		  ]
		}]};    
	  $("#chartContainer").CanvasJSChart(options);
    }

    <!-------------- Calculate Yes share payout (Buy) ---------------->
    $(document).ready(function(){
      $("#in_qnt").on('change keyup', function() {
        var a = $( "#in_qnt" ).val();
    	var yes_share = '${question.get().yesShareQuantity}';
    	var no_share = '${question.get().noShareQuantity}'; 
    	var payout = 100.0*Math.log((Math.exp((+yes_share + +a)/100.0)+Math.exp(no_share/100.0))) - 100.0*Math.log((Math.exp(yes_share/100.0)+Math.exp(no_share/100.0))); 
    	var payout_fixed = payout.toFixed(2); 		
    	$("#boh").attr('value',a);
    	$( "#total_buy" ).text("Total: " + payout_fixed + "$");
      });
    });

    <!-------------- Calculate No share payout (Buy) ---------------->
    $(document).ready(function(){
      $("#in_qnt2").on('change keyup', function() {
    	var a = $( "#in_qnt2" ).val();
		var yes_share = '${question.get().yesShareQuantity}';
		var no_share = '${question.get().noShareQuantity}';	
    	var payout = 100.0*Math.log((Math.exp(yes_share/100.0)+Math.exp((+no_share + +a)/100.0))) - 100.0*Math.log((Math.exp(yes_share/100.0)+Math.exp(no_share/100.0))); 
    	var payout_fixed = payout.toFixed(2);
    	$("#boh").attr('value',a);
    	$( "#total_buy" ).text("Total: " + payout_fixed + "$");
      });
    });

    <!-------------- Calculate Yes share payout (Sell) ---------------->
    $(document).ready(function(){
      $("#in_qnt3").on('change keyup', function() {
        var a = $( "#in_qnt3" ).val();
        var b = a*(-1);
		var yes_share = '${question.get().yesShareQuantity}';
		var no_share = '${question.get().noShareQuantity}';	
    	var payout = 100.0*Math.log((Math.exp((+yes_share - +a)/100.0)+Math.exp(no_share/100.0))) - 100.0*Math.log((Math.exp(yes_share/100.0)+Math.exp(no_share/100.0))); 
    	var t_payout_fixed = payout.toFixed(2);
    	$("#boh2").attr('value',b);
    	var payout_fixed = (-1)*t_payout_fixed;
    	$( "#total_sell" ).text("Total: " + payout_fixed + "$");
      });
    });

    <!-------------- Calculate No share payout (Sell) ---------------->
    $(document).ready(function(){
      $("#in_qnt4").on('change keyup', function() {
    	var a = $( "#in_qnt4" ).val();
    	var b = a*(-1);
		var yes_share = '${question.get().yesShareQuantity}';
		var no_share = '${question.get().noShareQuantity}';
		$("#boh2").attr('value',b);
    	var payout = 100.0*Math.log((Math.exp(yes_share/100.0)+Math.exp((+no_share - +a)/100.0))) - 100.0*Math.log((Math.exp(yes_share/100.0)+Math.exp(no_share/100.0))); 
    	var t_payout_fixed = payout.toFixed(2);
    	var payout_fixed = (-1)*t_payout_fixed;
    	$( "#total_sell" ).text("Total: " + payout_fixed + "$");
      });
    });
    	
    <!-------------- Calculate max yes/no share buyable ---------------->
    $(document).ready(function(){
	  var yes_share = '${question.get().yesShareQuantity}';
	  var no_share = '${question.get().noShareQuantity}';
	  var budget = '${sessionScope.userBudget}';
      var maxYes = Math.floor(100.0*Math.log((Math.exp((+budget + +yes_share)/100.0)+Math.exp((+budget + +no_share)/100.0)-Math.exp(+no_share/100.0))));
      var maxNo = Math.floor(100.0*Math.log((Math.exp((+budget + +yes_share)/100.0)+Math.exp((+budget + +no_share)/100.0)-Math.exp(+yes_share/100.0))));
      
      maxYes = maxYes - yes_share;
	  maxNo = maxNo - no_share;
    		
      $("#in_qnt").attr('max',maxYes);
      $("#th2").text("Quantity (max "+ maxYes +")");
      $("#th3").text("Quantity (max "+ maxNo +")");
      $("#in_qnt2").attr('max',maxNo);	
    });
    
  </script>
</html>