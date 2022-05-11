const url = "http://localhost:8080/ERS/app/"

let loginbtn = document.getElementById("loginButton");

loginbtn.addEventListener("click", login);

async function login(){
  let uName = document.getElementById("username").value;
  let pWord = document.getElementById("password").value;
  
  let user = {
    username:uName,
    password:pWord
  };

//let response = await fetch(url+"login", {
	let response = await fetch(url +"login", {
    method:"POST",
    body: JSON.stringify(user),
    credentials:"include"
  });

  if(response.status===200){
    let rows = document.getElementsByClassName("row");
    let h1 = document.createElement('h1');
    h1.className = "col-sm-12";
    h1.style.textAlign = "center";
    h1.innerText= uName+" has successfully logged in."
    rows[0].replaceChildren(h1);
    rows[1].innerHTML="";
    revealDivs();
  }else{
    console.log("could not log in");
    console.log(response);
  }
}

function revealDivs(){
  let divs = document.getElementsByTagName("div");

  for (let div of divs){
    div.hidden=false;
  }
}
