window.onload = function(){
	var isprazniRepertoar=$("#movie").empty();
	//alert('AbA');
	$.ajax({
		url:"/returnRoleUser",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data==true){
				$("#navigations").append("<li><a href = \"profile.html\">Profile</a></li>");
				
				$("#logout").append("<li  style=\"margin-right:20px;\"><a href=\"#\" onclick=\"logOutUser()\"><span class=\"glyphicon glyphicon-log-in\"></span> Logout</a></li>");
			}else{
				$("#logout").append("<li  style=\"margin-right:20px;\"><a href=\"login.html\" ><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>");
				
				toastr["info"]("You entered as a guest");
				
			}
		},error: function(jqxhr,textStatus,errorThrown){
			//alert(errorThrown);
		}
	});
	
	$.ajax({
		url:"returnAdmin",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data==true){
				$("#navigations").append("<li><a href = \"mainpage.html\">Admin page</a></li>");
			}else{
						
			}
			},error:function(jqxhr,textStatus,errorThrown){
				//alert(errorThrown);
			}
	});
	$.ajax({
		url:"/fpa",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			napraviRepertoar(data);
		},error: function(jqxhr,textStatus,errorThrown){
			//alert(errorThrown);
		}
	});
}

function napraviRepertoar(repertoar){
	var divRepertoar=$("#movie")
	console.log(repertoar);
	divRepertoar.append("<div class=\"panel-heading\" style=\"background-color:lightsteelblue\"  id=\"film\"><label style=\"font-weight:bold;font-size: 17px;margin-right:5px;\">Name of show: </label><a style=\"color:white\" onclick=\"otvoriBioskop("+repertoar+")\">"+repertoar.naziv+"</a><button style=\"float: right; margin-right:10px;\" onclick=\"otvoriSale("+repertoar+")\"  class=\"btn btn-info\" float=\"right\" ><span class=\"glyphicon glyphicon-ok-sign\"></span> Halls</button><button style=\"float: right; margin-right:10px;\" onclick=\"film("+repertoar.id+")\"  class=\"btn btn-danger\" float=\"right\" ><span class=\"glyphicon glyphicon-remove\"></span> Repertoire</button><button class=\"btn btn-success\" style=\"margin-right:10px; margin-left:10px;\" onclick=\"zapratiPodforum("+repertoar+")\"float=\"right\"><span class=\"glyphicon glyphicon-eye-open\"></span> FOLLOW</button><button style=\"margin-right:10px; \" class=\"btn btn-warning\" onclick=\"otpratiPodforum("+repertoar+")\"float=\"right\"><span class=\"glyphicon glyphicon-eye-close\"></span> UNFOLLOW</button><button class=\"btn btn-info\"  onclick=\"napisiZalbu("+repertoar+")\"><span class=\"glyphicon glyphicon-list-alt\"></span> Write complaint</button></div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"time\"><label style=\"font-weight:bold;margin-right:5px;\">Duration: </label>"+repertoar.trajanje +"</div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"sala\"><label style=\"font-weight:bold;margin-right:5px;\">Director: </label>"+repertoar.reditelj +"</div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"cena\"><label style=\"font-weight:bold;margin-right:5px;\">Actors: </label>"+repertoar.spisakGlumaca +"</div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"cena\"><label style=\"font-weight:bold;margin-right:5px;\">Genre: </label>"+repertoar.zanr +"</div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"cena\"><label style=\"font-weight:bold;margin-right:5px;\">Rating: </label>"+repertoar.prosecnaOcena +"</div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"cena\"><label style=\"font-weight:bold;margin-right:5px;\">Desciption: </label>"+repertoar.opis +"</div>");

}
/*
function film(id){
	$.ajax({
		url:"/projekcije/"+id,
		type:"GET",
		success:function(data){
			if(data!=null){
				window.location.replace("repertoire.html");
			}
		},error: function(jqxhr,textStatus,errorThrown){
			alert(errorThrown);
		}
		
	});
}
*/

function logOutUser(){
	$.ajax({
		async:"false",
		url:"/logout",
		type:"GET",
		success:function(data){
			if(data==true){
				top.location.href="login.html";
			}else{
				toastr["error"]("Failed to logout");
				
			}
		},error: function(jqxhr,textStatus,errorThrown){
			//alert(errorThrown);
		}
		
	});
	
}