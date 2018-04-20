window.onload = function(){
	var isprazniBioskope=$("#bioskopi").empty();
	console.log("AA");
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
				$("#navigations").append("<li><a href = \"events.html\">Events</a></li>")
			}else{
						
			}
			},error:function(jqxhr,textStatus,errorThrown){
				//alert(errorThrown);
			}
	});
	$.ajax({
		url:"/pba",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data!=null){
				//alert(data);
				$.each(data,function(index,pbioskop){
						napraviPBioskop(index,pbioskop);
				});
			}
		},error: function(jqxhr,textStatus,errorThrown){
			//alert(errorThrown);
		}
	});
}

function napraviPBioskop(index,pbioskop){
	var divPBioskopi=$("#bioskopi");
	//alert(pbioskop);
	if(pbioskop.vrstaAmbijenta=="Pozoriste"){
		divPBioskopi.append("<div class=\"panel-heading\" style=\"background-color:lightsteelblue\"  id=\"naziv\"><label style=\"font-weight:bold;font-size: 17px;margin-right:5px;\">Name of theatre: </label><a style=\"color:white\" onclick=\"otvoriBioskop("+pbioskop+")\">"+pbioskop.naziv+"</a><button style=\"float: right; margin-right:10px;\" onclick=\"otvoriSale("+pbioskop.id+")\"  class=\"btn btn-info\" float=\"right\" ><span class=\"glyphicon glyphicon-ok-sign\"></span> Halls</button><button style=\"float: right; margin-right:10px;\" onclick=\"repertoar("+pbioskop.id+")\"  class=\"btn btn-danger\" float=\"right\" ><span class=\"glyphicon glyphicon-remove\"></span> Repertoire</button><button class=\"btn btn-success\" style=\"margin-right:10px; margin-left:10px;\" onclick=\"karta("+pbioskop.id+")\"float=\"right\"><span class=\"glyphicon glyphicon-eye-open\"></span> Tickets</button><button style=\"margin-right:10px; \" class=\"btn btn-warning\" onclick=\"izvestaj("+pbioskop.id+")\"float=\"right\"><span class=\"glyphicon glyphicon-eye-close\"></span> Reports</button><button class=\"btn btn-info\"  onclick=\"napisiZalbu("+index+")\"><span class=\"glyphicon glyphicon-list-alt\"></span> Write complaint</button></div>");

	}
	else {
		divPBioskopi.append("<div class=\"panel-heading\" style=\"background-color:lightsteelblue\"  id=\"naziv\"><label style=\"font-weight:bold;font-size: 17px;margin-right:5px;\">Name of cinema: </label><a style=\"color:white\" onclick=\"otvoriBioskop("+pbioskop+")\">"+pbioskop.naziv+"</a><button style=\"float: right; margin-right:10px;\" onclick=\"otvoriSale("+pbioskop.id+")\"  class=\"btn btn-info\" float=\"right\" ><span class=\"glyphicon glyphicon-ok-sign\"></span> Halls</button><button style=\"float: right; margin-right:10px;\" onclick=\"repertoar("+pbioskop.id+")\"  class=\"btn btn-danger\" float=\"right\" ><span class=\"glyphicon glyphicon-remove\"></span> Repertoire</button><button class=\"btn btn-success\" style=\"margin-right:10px; margin-left:10px;\" onclick=\"karta("+pbioskop.id+")\"float=\"right\"><span class=\"glyphicon glyphicon-eye-open\"></span> Tickets</button><button style=\"margin-right:10px; \" class=\"btn btn-warning\" onclick=\"izvestaj("+pbioskop.id+")\"float=\"right\"><span class=\"glyphicon glyphicon-eye-close\"></span> Reports</button><button class=\"btn btn-info\"  onclick=\"napisiZalbu("+index+")\"><span class=\"glyphicon glyphicon-list-alt\"></span> Write complaint</button></div>");

	}
	divPBioskopi.append("<div class=\"panel-footer\" id=\"adresa\"><label style=\"font-weight:bold;margin-right:5px;\">Address: </label>"+pbioskop.adresa +"</div>");
	
	divPBioskopi.append("<div class=\"panel-footer\" id=\"promotivniOpis\"><label style=\"font-weight:bold;margin-right:5px;\">Description: </label>"+pbioskop.promotivniOpis +"</div>");
	divPBioskopi.append("<div class=\"panel-footer\" id=\"promotivniOpis\"><label style=\"font-weight:bold;margin-right:5px;\">Rating: </label>"+pbioskop.prosecnaOcena +"</div>");

}

function izvestaj(id){
	//alert(id)
	$.ajax({
		url:"/projekcije/"+id,
		type:"GET",
		success:function(data){
			if(data!=null){
				window.location.replace("report.html");
			}
		},error: function(jqxhr,textStatus,errorThrown){
			//alert('Greska');
		}
		
	});
}

function repertoar(id){
	//alert(id)
	$.ajax({
		url:"/projekcije/"+id,
		type:"GET",
		success:function(data){
			if(data!=null){
				window.location.replace("repertoire.html");
			}
		},error: function(jqxhr,textStatus,errorThrown){
			//alert('Greska');
		}
		
	});
}

function karta(id){
	//alert(id)
	$.ajax({
		url:"/projekcije/"+id,
		type:"GET",
		success:function(data){
			if(data!=null){
				window.location.replace("ticket.html");
			}
		},error: function(jqxhr,textStatus,errorThrown){
			//alert('Greska');
		}
		
	});
}


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

function otvoriSale(id){
	//alert(id)
	$.ajax({
		url:"/projekcije/"+id,
		type:"GET",
		success:function(data){
			if(data!=null){
				window.location.replace("halls.html");
			}
		},error: function(jqxhr,textStatus,errorThrown){
			//alert('Greska');
		}
		
	});
}