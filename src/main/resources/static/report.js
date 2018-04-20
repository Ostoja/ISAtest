window.onload = function(){
	$("#izabraniEvent").empty();
	
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
	
	
}

/*
function checkAuthorize(){
	$.ajax({
		url:"rest/userService/returnModerOrAdmin",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data==true){
				top.location.href="noviPodforum.html";
			}else{
						top.location.href = "noviPodforum.html";
						
			}
			},error:function(jqxhr,textStatus,errorThrown){
				//alert(errorThrown);
			}
	});
}
*/
/*
function getFormData(){
	var niz=[];
	var index=0;
	
	$("#izabraniModeratori li").each(function(){
		niz[index] = $(this).text();
		index++;
		
	});
	
	var data = JSON.stringify({
		"naziv":$('#noviPodforum input[name=naziv]').val(),
		"opis":$('#noviPodforum textarea[name=opis]').val(),
		"pravila":$('#noviPodforum textarea[name=pravila]').val(),
		"ikonica":$('#noviPodforum input[name=ikonica]').val(),
		"moderatori":niz
	});
	return data;
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
function getFormData($form){
	var unordered_array = $form.serializeArray();
	var ordered_array={};
	
	$.map(unordered_array,function(n,i){
		ordered_array[n['name']]=n['value'];
	});
	return ordered_array;
}

function izvestajPrihod(){
	$form = $("#noviProj");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	console.log(s);
	$.ajax({
		url:"/izvestaj/prihod",
		type:"POST",
		data:s,
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			//window.location.replace("incomes.html");
			console.log(data);
			napraviPrihod(data);
		},error: function(jqxhr,textStatus,errorThrown){
			//alert(errorThrown);
		}
	});
	
}

function izvestajPoseta(){
	$form = $("#noviProj");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	var divRepertoar=$("#izvestaj")
	divRepertoar.empty();
	console.log(s);
	$.ajax({
		url:"/izvestaj/posecenost",
		type:"POST",
		data:s,
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			//window.location.replace("visits.html");
			console.log(data);
			$.each(data,function(index,repertoar){
				napraviPoseta(index,repertoar);
		});
			
		},error: function(jqxhr,textStatus,errorThrown){
			//alert(errorThrown);
		}
	});
	
}

function napraviPoseta(index, repertoar) {
	var divRepertoar=$("#izvestaj")
	
	console.log(repertoar);
	divRepertoar.append("<div class=\"panel-footer\" id=\"cena\"><label style=\"font-weight:bold;margin-right:5px;\">Date: </label>"+repertoar.datum +"</div>");
	divRepertoar.append("<div class=\"panel-footer\" id=\"cena\"><label style=\"font-weight:bold;margin-right:5px;\">Number of visits: </label>"+repertoar.poseta +"</div>");

}

function napraviPrihod(repertoar){
	var divRepertoar=$("#izvestaj")
	divRepertoar.empty();
	console.log(repertoar);
	divRepertoar.append("<div class=\"panel-footer\" id=\"cena\"><label style=\"font-weight:bold;margin-right:5px;\">Income: </label>"+repertoar +"</div>");
}

