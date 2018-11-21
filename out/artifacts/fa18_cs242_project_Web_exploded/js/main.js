$(function(){
	$('#Requirement').click(function(){
		if ($('.dropdown-menu').is(":hidden")){
			$('.dropdown-menu').slideDown();
		}
		else{
			$('.dropdown-menu').slideUp();
		}
		
	});

});