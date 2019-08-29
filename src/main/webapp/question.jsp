<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
  <head>
    <title>StiCast! - ${question}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/questions.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <!------------ NAVBAR ------------>
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
    
      <!------------ ADMIN PANEL ------------>
      <c:choose>
        <c:when test="${sessionScope.isAdmin == true && isOpen == true}">
        <form action="/StiCast/ServletCloseQuestion" method="POST"> <b>Hello admin! </b>Pick the winner and close the question: <br>
          <select name ="winnerType" id="selectWinner" style="margin-top:10px">
            <option value="yes">Yes</option>
            <option value="no">No</option>
          </select>
          <input type ="hidden" name="questionID" value="${questionID}"/>
          <button class="button" style="vertical-align:middle"><span>Close</span></button>
        </form>
        </c:when>    
      </c:choose>
      
      <h1 style="margin-bottom: 25px">${question}</h1>
      <div class="container" style="width:1050px">
        <div id="chartContainer" style="height: 370px; width: 45%; float:left "></div> 
        <c:choose>
          <c:when test="${isOpen == true}">
            <div class="form_buysell">
              <c:choose>
                <c:when test="${isFollowed == 0}">
                  <a href="#" id="follow"><img id="followIcon" alt="follow" src="<c:url value="/img/unfollow.png" />" width="8%" style="margin-bottom: 8px;"></a><br>
                </c:when>    
                <c:otherwise>
                  <a href="#" id="unfollow"><img id="followIcon" alt="unfollow" src="<c:url value="/img/follow.png" />" width="8%" style="margin-bottom: 8px;"></a><br>
                </c:otherwise>
              </c:choose>
              <div style="display: inline-block; margin-right: 10%"><p style="margin-top: 0%; font-size: 20px; ">Yes</p><p style="margin-top:-15px; font-size: 30px;" id="ys"><b>${yes_share_user}</b></p></div>
              <div style="display: inline-block; "><p style="margin-top: 0%; font-size: 20px;">No</p><p style="margin-top:-15px; font-size: 30px;" id="ns"><b>${no_share_user}</b></p></div>
              <ul class="tab-group">
                <li class="tab active"><a href="#buy">Buy</a></li>
                <li class="tab"><a href="#sell">Sell</a></li>
              </ul>      
              <div class="tab-content">
                <div id="buy">   
                
                  <form action="${pageContext.request.contextPath}/question/${questionID}" method="post" >
                    <table>
                      <tr>
                        <th>Type</th>
                        <th class="th2" id="th2"></th>
                        <th class="th3" style="display:none" id="th3"></th>
                        <th id="total_buy" style=" width: 120px">Total: 0$</th>
                      </tr>
                      <tr>
                        <td width="25%">
                          <select name="selectYesNo" id="select_buy">
                            <option value="yes">Yes</option>
                            <option value="no">No</option>
                          </select>
                        </td>
                        <td class="td2" width="50%"><input id="in_qnt" type="number" name ="qnt1" min="1" max="" value="#" required /></td>
                        <td class="td3" style="display:none" width="50%"><input id ="in_qnt2" type="number" name ="qnt2" min="1" max="" value="#" disabled/></td>
   				        <td width="25%"><button class="button" style="vertical-align:middle"><span>Buy</span></button></td>
   				        <td>
  				          <input type ="hidden" name="buyOrSell" value="buy"/>
  				          <input id="boh" type ="hidden" name="quantity" value="#"/>
                        </td>
                      </tr>
                    </table>
                  </form>
                </div> 
                <div id="sell">  
                               
                  <form action="${pageContext.request.contextPath}/question/${questionID}" method="post">
                    <table>
                      <tr>
                        <th>Type</th>
                        <th class="2th2" >Quantity (max ${yes_share_user})</th>
                        <th class="2th3" style="display:none">Quantity (max ${no_share_user})</th>
                        <th id="total_sell" style=" width: 120px">Total: 0$</th>
                      </tr>
                      <tr>
                        <td width="25%">
                          <select name="selectYesNo" id="select_sell">
                            <option value="yes">Yes</option>
                            <option value="no">No</option>
                          </select>
                        </td>
                        <td class="2td2" width="50%"><input id="in_qnt3" type="number" name ="qnt1" min="1" max="${yes_share_user}" value="#" required /></td>
                        <td class="2td3" style="display:none" width="50%"><input id="in_qnt4" type="number" name ="qnt2" min="1" max="${no_share_user}" value="#" disabled /></td>
   		                <td width="25%"><button class="button" style="vertical-align:middle"><span>Sell</span></button></td>
   			            <td>
				          <input type ="hidden" name="buyOrSell" value="sell"/>
				          <input id="boh2" type ="hidden" name="quantity" value="#"/>
                        </td>       
                      </tr>
                    </table>
                  </form>  
                </div> 
              </div> 
            </div>  
		  </c:when>
		  		     
          <c:otherwise>
            <p style="margin-top: 10%; font-size: 25px;">This question is</p><p style="margin-top:-20px; font-size: 40px;"><b>CLOSED!</b></p> 
          </c:otherwise>
        </c:choose>
        
        <!-------------- COMMENTS ---------------->	
        <div class="container" style="margin: auto; width: 100%; margin-bottom: 65px;">
	      <div class="tab-pane" id="add-comment" style="margin-top: 15%; ">
            <form action="${pageContext.request.contextPath}/question/${questionID}/comments" method="post" class="form-horizontal" id="commentForm" role="form"> 
              <input type ="hidden" name="questionID" value="${questionID}"/>
              <div class="form-group">
                <div class="col-sm-10" style="margin-left: 12%;">
                  <div style="float: left; width:79%">
                    <textarea class="form-control" name="addComment" id="addComment" placeholder="Write your comment here!" style="resize:none; float:left" maxlength="100" ></textarea>
                  </div>
                  <div style="float:right; height:12%">
                    <button class="btn btn-success btn-circle text-uppercase" type="submit" id="submitComment" style="height: 70px">Submit comment</button>
                  </div>
                </div>                          
              </div>         
            </form>
          </div>
          <div class="media-body">
            <c:forEach items="${comment}" var="data" varStatus="item"> 
              <div class="well" style="height: auto; ">
			    <h4 style="font-size:18px"><b>${data.name_account}</b> @ ${data.timestamp}</h4>
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
    	var yes_share = '${yesQuantity}';
    	var no_share = '${noQuantity}'; 
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
		var yes_share = '${yesQuantity}';
		var no_share = '${noQuantity}';	
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
		var yes_share = '${yesQuantity}';
		var no_share = '${noQuantity}';	
    	var payout = 100.0*Math.log((Math.exp((+yes_share - +a)/100.0)+Math.exp(no_share/100.0))) - 100.0*Math.log((Math.exp(yes_share/100.0)+Math.exp(no_share/100.0))); 
    	var t_payout_fixed = payout.toFixed(2);
    	$("#boh2").attr('value',a);
    	var payout_fixed = (-1)*t_payout_fixed;
    	$( "#total_sell" ).text("Total: " + payout_fixed + "$");
      });
    });

    <!-------------- Calculate No share payout (Sell) ---------------->
    $(document).ready(function(){
      $("#in_qnt4").on('change keyup', function() {
    	var a = $( "#in_qnt4" ).val();
		var yes_share = '${yesQuantity}';
		var no_share = '${noQuantity}';
		$("#boh2").attr('value',a);
    	var payout = 100.0*Math.log((Math.exp(yes_share/100.0)+Math.exp((+no_share - +a)/100.0))) - 100.0*Math.log((Math.exp(yes_share/100.0)+Math.exp(no_share/100.0))); 
    	var t_payout_fixed = payout.toFixed(2);
    	var payout_fixed = (-1)*t_payout_fixed;
    	$( "#total_sell" ).text("Total: " + payout_fixed + "$");
      });
    });
    	
    <!-------------- Calculate max yes/no share buyable ---------------->
    $(document).ready(function(){
	  var yes_share = '${yesQuantity}';
	  var no_share = '${noQuantity}';
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