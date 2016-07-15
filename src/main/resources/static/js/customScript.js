function validate(){
	var fname= document.getElementById("firstname").value;
	var lname= document.getElementById("lastname").value;
	var email= document.getElementById("email").value;
	var password= document.getElementById("password").value;
	var file= document.getElementById("upload-file-input").value;
	
//	if(fname==null||fname==""){
//		alert("firstname cannot be left blank");
//		return false;
//	}
	
	try{
		if(fname==null||fname=="") throw "Firstname is empty";
		if(lname==null||lname=="") throw "Lastname is empty";
		if(email==null||email=="") throw "Email cannot be left blank";
		if(password==null||password=="") throw "Password cannot be empty";
		if(file==null||file=="") throw "Please upload a file";
		
	} catch(err) {
		document.getElementById("errorMsg").innerHTML="*Invalid Data - "+err;
		return false;
	}
	
	
}