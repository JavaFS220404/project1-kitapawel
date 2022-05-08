let loginForm = document.getElementById("loginForm");
let nothing = document.getElementById("nothing");
let loginButton = document.getElementById("loginButton");
const element = document.getElementById("div1");


loginButton.addEventListener('click', login);
nothing.addEventListener('click', test);

function test(){
	console.log('test');
 	const para = document.createElement("p");
	const node = document.createTextNode("This is the result of the nothing button.");
	para.appendChild(node);
	element.appendChild(para);

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
		logInAlt(true);
        
    } else{
        console.log("Failure!");
        console.log(response);
		logInAlt(false);
    }
}

function logInAlt(boolValue){
	const loggedInDiv = document.createElement("button");
	const trueNode = document.createTextNode("You are now logged in.");
	
	if (true){
		loggedInDiv.appendChild(trueNode);
		element.appendChild(loggedInDiv);	
	}
}
