 $(document).ready(function(){
	 
	 
	    $("#SSHCall").click(function(){
	       

			 var xhr = new XMLHttpRequest()
			 xhr.open("GET", "/application/SGIE/START", true)
			 xhr.onprogress = function () {
				 
				 $("#console").text(xhr.responseText);
   					console.log("PROGRESS:", xhr.responseText)
 			}
 			xhr.send()
	    	
	    });
	    
	    
	    
	    
	});