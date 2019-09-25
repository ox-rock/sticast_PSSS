$(document).ready(function(){
$('.tab a').on('click', function (e) {
  e.preventDefault();
  
  $(this).parent().addClass('active');
  $(this).parent().siblings().removeClass('active');
  
  target = $(this).attr('href');

  $('.tab-content > div').not(target).hide();
  
  $(target).fadeIn(600);  
});
});

$(document).ready(function(){
$( "#select_buy" ).change(function() {
	if($( "#select_buy" ).val() == 'no'){
	$(".th3").toggle();
 	$(".td3").toggle();
 	$(".th2").toggle();
 	$(".td2").toggle();
 	$('#in_qnt').removeAttr('required');
 	$('#in_qnt2').removeAttr('disabled');
 	$('#in_qnt').prop('disabled',true);
 	$('#in_qnt2').prop('required',true);
 	$('#in_qnt2').val('');
 	
	}
	else {
		$(".th3").toggle();
	 	$(".td3").toggle();
	 	$(".th2").toggle();
	 	$(".td2").toggle();
	 	
	 	$('#in_qnt').removeAttr('disabled');
	 	$('#in_qnt2').removeAttr('required');
	 	$('#in_qnt').prop('required',true);
	 	$('#in_qnt2').prop('disabled',true);
	 	$('#in_qnt').val('');
	 	
	}
	
});
});

$(document).ready(function(){
$( "#select_sell" ).change(function() {
	
	if($( "#select_sell" ).val() == 'no'){
		$(".2th3").toggle();
	 	$(".2td3").toggle();
	 	$(".2th2").toggle();
	 	$(".2td2").toggle();
	 	$('#in_qnt3').removeAttr('required');
	 	$('#in_qnt4').removeAttr('disabled');
	 	$('#in_qnt3').prop('disabled',true);
	 	$('#in_qnt4').prop('required',true);
	 	
		}
		else {
			$(".2th3").toggle();
		 	$(".2td3").toggle();
		 	$(".2th2").toggle();
		 	$(".2td2").toggle();
		 	
		 	$('#in_qnt3').removeAttr('disabled');
		 	$('#in_qnt4').removeAttr('required');
		 	$('#in_qnt3').prop('required',true);
		 	$('#in_qnt4').prop('disabled',true);
		 	
		}
});
});



