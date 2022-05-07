let loginForm = document.getElementById("loginForm");
let loginButton = document.getElementById("loginButton");


loginButton.addEventListener('click', login);

function test(){
	console.log("button working");
}

async function login(){
    
    let username = document.getElementById("username").value;
  	let password = document.getElementById("password").value;

	let credentials = {
		un: username,
		pwd: password
	}
  		
    let response = await fetch("http://localhost:8080/ERS/login",
    {
    	method:	"POST",  
    	body: JSON.stringify(credentials)
    })
      
	if(response.status === 201){
		console.log("Success!");
		console.log(response);
        
    } else{
        console.log("Failure!");
        console.log(response);
    }


}
