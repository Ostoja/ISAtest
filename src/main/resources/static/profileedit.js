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
		url:"returnUser",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			if(data!=null){
				$("#mejl").empty();
				$("#mejl").append("<input type=\"text\" class=\"form-control\" title=\"Enter your email address here\" pattern=\"/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/\" value=\""+data.email+"\" required id=\"email\" name=\"email\">");
				$("#usernejm").empty();
				$("#usernejm").append("<input class=\"form-control\" maxlength=\"30\" title=\"Create your username\" type=\"text\" value=\""+data.username+"\" required id=\"username\" name=\"username\">");
				$("#nejm").empty();
				$("#nejm").append("<input class=\"form-control\" title=\"First name must start with uppercase letter and must not contain digits\" type=\"text\" value=\""+data.ime+"\"  id=\"ime\" name=\"ime\">");
				$("#surnejm").empty();
				$("#surnejm").append("<input class=\"form-control\" title=\"Last name must start with uppercase letter and must not contain digits\" type=\"text\" value=\""+data.prezime+"\"  id=\"prezime\" name=\"prezime\">");
				$("#siti").empty();
				$("#siti").append("<input class=\"form-control\" type=\"text\" title=\"City\" value=\""+data.grad+"\" id=\"grad\" name=\"grad\" >");
				$("#foun").empty();
				$("#foun").append("<input class=\"form-control\" type=\"text\" title=\"Phone number must be entered in the form specified by the placeholder\" value=\""+data.brojTelefona+"\" id=\"brojTelefona\" name=\"brojTelefona\" >");
			}else{
						
			}
			},error:function(jqxhr,textStatus,errorThrown){
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
function editujga(){
	
	var x = document.forms["registracija"]["username"].value;
	//var y = document.forms["registracija"]["password"].value;
	var z = document.forms["registracija"]["email"].value;
	 var phone = document.forms["registracija"]["brojTelefona"].value;
	 var FN = document.forms["registracija"]["ime"].value;
	    var LN = document.forms["registracija"]["prezime"].value;
		var CI = document.forms["registracija"]["grad"].value;
	    
	if((x=="" || z=="" || phone=="" || FN=="" || LN=="" || CI=="") || (x=="" && z=="" && phone=="" && FN=="" && LN=="")){
		toastr["error"]("You have to fill out all input fields","Editing failed");
		
	}else{
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    var ok = re.test(z);
	    
	    var reUs = new RegExp("^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$");
	    var okUs = reUs.test(x);
	    
	    
	    var reFN = /^[A-Z][a-zA-Z]+[']*[0-9]{0}/;
	    var reLN=/^[A-Z][a-zA-Z]+[']*[0-9]{0}/;
	    var okFN = reFN.test(FN);
	    var okLN = reLN.test(LN);
	    
	    
	   
	    var rePh = new RegExp("[(]{1}[0-9]{3}[)]{1}[0-9]{3,4}[-]{1}[0-9]{3}");
	    var okPh = rePh.test(phone);
	    
	    if(ok==false || okUs==false || okFN==false || okLN==false || okPh==false){
		    if(ok==false){
		    	$("#spanEmail").html("<label style=\"color:red\">Invalid input for email field</label>");
		    }
		    if(okUs==false){
		      	$("#spanUsername").html("<label style=\"color:red\">Invalid input for username field</label>");
				  
		    }
		    if(okFN==false){
		      	$("#spanName").html("<label style=\"color:red\">Invalid input for first name field</label>");
				  
		    }
		    if(okLN==false){
		      	$("#spanSurname").html("<label style=\"color:red\">Invalid input for last name field</label>");
				  
		    }
		    
		    if(okPh==false){
		      	$("#spanPhone").html("<label style=\"color:red\">Invalid input for phone number field</label>");
				  
		    }
		    
		    
	    }else{
	    	$("#spanUsername").empty();
	    	$("#spanName").empty();
	    	$("#spanSurname").empty();
	    	$("#spanEmail").empty();
	    	$("#spanPhone").empty();
	    	$("#spanCity").empty();
	    	
	    	$form = $("#registracija");
	    	var data = getFormData($form);
	    	var s = JSON.stringify(data);
	    	$.ajax({
	    		url:"/edituser",
	    		type:"POST",
	    		data:s,
	    		contentType:"application/json",
	    		dataType:"json",
	    		success:function(data){
	    			if(data==false){
	    				toastr["error"]("Editing failed");
	    				
	    			}else{
	    				toastr["success"]("Editing successfull");
	    				}
	    			
	    		},error: function(jqxhr,textStatus,errorThrown){
	    			//alert(errorThrown);
	    		}
	    	});
	    	
	    	
	    }
		
	}
	
}