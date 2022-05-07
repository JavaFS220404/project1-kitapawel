let loginForm = document.getElementById("loginForm");
let loginButton = document.getElementById("loginButton");


loginButton.addEventListener('click', test);

function test(){
	console.log("button working");
}

async function login(){
    let response = await fetch("http://localhost:8080/ERS/login");
	if(response.status === 200){
        let data = await response.json();
    } else{
        console.log(".");
        console.log(response);
    }

}
