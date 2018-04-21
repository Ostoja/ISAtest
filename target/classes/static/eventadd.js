window.onload = function(){
	//$("#izabraniModeraotri").empty();
	
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
			////alert(errorThrown);
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
				window.location.replace("index.html");		
	
			}
			},error:function(jqxhr,textStatus,errorThrown){
				////alert(errorThrown);
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
				////alert(errorThrown);
			}
	});
}
*/

function getFormData($form){
	var unordered_array = $form.serializeArray();
	var ordered_array={};
	
	$.map(unordered_array,function(n,i){
		ordered_array[n['name']]=n['value'];
	});
	return ordered_array;
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
			////alert(errorThrown);
		}
		
	});
	
}
function dodajFilm(){
	$form = $("#noviEvent");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	var objFile = $('#poster');
	var file = objFile[0].files[0];
	if(file!=undefined){
	$.ajax({
		url:"/fp/"+file.name,
		type:"POST",
		data:s,
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			
				toastr["success"]("Success");
					$.ajax({
					
					async : false,
					type : "POST",
					url : "/file/" + file.name,
			        contentType : false,
			        data: file,
			        processData: false,
			    
			        
			        
				});
				
			
		},error: function(jqxhr,textStatus,errorThrown){
			toastr["success"]("Success");
			$.ajax({
			
			async : false,
			type : "POST",
			url : "/file/" + file.name,
	        contentType : "multipart/form-data",
	        data: file,
	        processData: false,
	    
	        
	        
		});
		}
	});
	}else{
		toastr["error"]("Please choose icon");
		
		
	}
}

