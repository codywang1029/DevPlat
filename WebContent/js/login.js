$(function(){
	$('#login-btn').click(function(e) {
		console.log("login");
		$("#login").delay(100).fadeIn(100);
 		$("#register").fadeOut(100);
		$('#register-btn').removeClass('active');
		$(this).addClass('active');
		
	});
	
	$('#register-btn').click(function(e) {
		console.log("register");
		$("#register").delay(100).fadeIn(100);
 		$("#login").fadeOut(100);
		$('#login-btn').removeClass('active');
		$(this).addClass('active');
		
	});
	$('input[name="register-username"]').blur(function(){
		$.ajax({
			type: "POST",
			url: "CheckUsername",
			data:  { 'username':$(this).val()},
			dataType: "text",
			success: function(response){
				if (response=="success"){
					$('#name-ok').show();
					$('#name-exist').hide();
				}
				else{
					$('#name-ok').hide();
					$('#name-exist').show();
				}
			}
		});
	});
	
});