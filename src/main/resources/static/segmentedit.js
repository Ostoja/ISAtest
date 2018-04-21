window.onload = function(){
	
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
				window.location.replace("index.html");		
		
			}
			},error:function(jqxhr,textStatus,errorThrown){
				//alert(errorThrown);
			}
	});
	var seatType;
	$.ajax({
		url:"/seggment",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data!=null){
				console.log(data);
				seatType=data.tipSedista;
				$("#nejm").empty();
				$("#nejm").append("<input style=\"margin-left:10px;margin-right:5px;width:98%;\" value=\""+data.naziv+"\" required type=\"text\" id=\"naziv\" name=\"naziv\">");
				if(data.jeZatvoreno){
					$("#klozd").empty();
					$("#klozd").append("<input style=\"margin-left:10px;margin-right:5px;width:98%;\" checked=checked required type=\"checkbox\" id=\"jeZatvoreno\" name=\"jeZatvoreno\" value=\"true\">");
				}
				else{
					$("#klozd").empty();
					$("#klozd").append("<input style=\"margin-left:10px;margin-right:5px;width:98%;\" required type=\"checkbox\" id=\"jeZatvoreno\" name=\"jeZatvoreno\" value=\"true\">");
				}
				$("#redov").empty();
				$("#redov").append("<input style=\"margin-left:10px;margin-right:5px;width:98%;\" value=\""+data.redovi+"\" required type=\"text\" id=\"redovi\" name=\"redovi\">");
				$("#kolon").empty();
				$("#kolon").append("<input style=\"margin-left:10px;margin-right:5px;width:98%;\" value=\""+data.kolone+"\" required type=\"text\" id=\"kolone\" name=\"kolone\">");
				$("#sediste").empty();
				$("#sediste").append("<select style=\"height:33px;margin-left:10px;margin-right:5px;width:60%;\" required id=\"tipSedista\" name=\"tipSedista\"><option></option>");
			}else{
						
			}
			},error:function(jqxhr,textStatus,errorThrown){
				//alert(errorThrown);
			}
	});
	
	$.ajax({
		url:"/tipoviSedista",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data!=null){
				console.log(data);
				console.log(seatType);
				$.each(data,function(index,value){
					if(value==seatType){
						$("#tipSedista").append("<option selected=\"selected\">"+value+"</option>");
					}
					else{
						$("#tipSedista").append("<option>"+value+"</option>");
						
					}
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
function getFormData($form){
	var unordered_array = $form.serializeArray();
	var ordered_array={};
	
	$.map(unordered_array,function(n,i){
		ordered_array[n['name']]=n['value'];
	});
	return ordered_array;
}

function dodajSegment(){
	$form = $("#noviSeg");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	console.log(s);
	$.ajax({
		url:"/segment/add",
		type:"PUT",
		data:s,
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data==false){
				toastr["error"]("Registration failed");
				
			}else{
				toastr["success"]("Registration successfull");
				}
			
		},error: function(jqxhr,textStatus,errorThrown){
			//alert(errorThrown);
		}
	});
	
}
