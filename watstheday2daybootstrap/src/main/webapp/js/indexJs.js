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

$(document).ready(function(){
	
	//alert(1);
	loadTheDayToday();
});