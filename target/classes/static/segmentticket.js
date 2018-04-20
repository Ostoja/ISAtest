window.onload = function(){
	var isprazniRepertoar=$("#segment").empty();
	//alert('segment');
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
		url:"/segmenti2",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data!=null){
				$.each(data,function(index,repertoar){
						napraviRepertoar(index,repertoar);
				});
			}
		},error: function(jqxhr,textStatus,errorThrown){
			//alert(errorThrown);
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
function napraviRepertoar(index,repertoar){
	var divRepertoar=$("#segment")
	console.log(repertoar);
	divRepertoar.append("<div class=\"panel-heading\" style=\"background-color:lightsteelblue\"  id=\"film\"><label style=\"font-weight:bold;font-size: 17px;margin-right:5px;\">Name of segment: </label><a style=\"color:white\" onclick=\"otvoriBioskop("+repertoar+")\">"+repertoar.naziv+"</a><button style=\"float: right; margin-right:10px;\" onclick=\"otvoriSale("+repertoar+")\"  class=\"btn btn-info\" float=\"right\" ><span class=\"glyphicon glyphicon-ok-sign\"></span> Halls</button><button style=\"float: right; margin-right:10px;\" onclick=\"segmenti("+repertoar.id+")\"  class=\"btn btn-danger\" float=\"right\" ><span class=\"glyphicon glyphicon-remove\"></span> Segmenti</button><button class=\"btn btn-success\" style=\"margin-right:10px; margin-left:10px;\" onclick=\"editSegment("+repertoar.id+")\"float=\"right\"><span class=\"glyphicon glyphicon-eye-open\"></span> Edit</button><button style=\"margin-right:10px; \" class=\"btn btn-warning\" onclick=\"ticket("+repertoar.id+")\"float=\"right\"><span class=\"glyphicon glyphicon-eye-close\"></span> Ticket</button><button class=\"btn btn-info\"  onclick=\"napisiZalbu("+index+")\"><span class=\"glyphicon glyphicon-list-alt\"></span> Write complaint</button></div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"zatvoreno\"><label style=\"font-weight:bold;margin-right:5px;\">Closed: </label>"+repertoar.jeZatvoreno +"</div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"redovi\"><label style=\"font-weight:bold;margin-right:5px;\">Rows: </label>"+repertoar.redovi +"</div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"kolone\"><label style=\"font-weight:bold;margin-right:5px;\">Columns: </label>"+repertoar.kolone +"</div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"tip\"><label style=\"font-weight:bold;margin-right:5px;\">Type: </label>"+repertoar.tipSedista +"</div>");
	
}

function ticket(id){
	$.ajax({
		url:"/segm/"+id,
		type:"GET",
		success:function(data){
			if(data!=null){
				window.location.replace("seat.html");
			}
		},error: function(jqxhr,textStatus,errorThrown){
			//alert(errorThrown);
		}
		
	});
}
/*
function editSegment(id){
	$.ajax({
		url:"/segm/"+id,
		type:"GET",
		success:function(data){
			if(data!=null){
				window.location.replace("segmentedit.html");
			}
		},error: function(jqxhr,textStatus,errorThrown){
			//alert(errorThrown);
		}
		
	});
}*/