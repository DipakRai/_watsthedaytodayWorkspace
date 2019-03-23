/**
 * 
 */

var loadTheDayToday = function(){
	//alert(2);
	$.ajax({
		  type: "POST",
		  url: "/watstheday2daybootstrap/MainAppCtrl",
		  //data: myusername,
		  cache: false,
		  success: function(data){
		     $("#myCol").text(data);
		  },
		  error:function(xhr,response){
			  console.info(response);			  
		  }
		});
}


function showWhoWeAreDiv(){
	$('#myCol').hide();
	$('#contactDiv').hide();
	$('#whoWeAreDiv').show();
}

function showContactDiv(){
	$('#myCol').hide();
	$('#whoWeAreDiv').hide();
	$('#contactDiv').show();
}
$(document).ready(function(){
	
	//alert(1);
	loadTheDayToday();
});
