const url = "http://localhost:8080/ERS/app/"

let loginbtn = document.getElementById("loginButton");
let registerbtn = document.getElementById("registerUserBtn");

loginbtn.addEventListener("click", login);
registerbtn.addEventListener("click", registerUser);

async function login(){
  let uName = document.getElementById("username").value;
  let pWord = document.getElementById("password").value;
  
  let user = {
    username:uName,
    password:pWord
  };

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
    h1.innerText= uName + " has successfully logged in."
    rows[0].replaceChildren(h1);
    rows[1].innerHTML="";
    revealDivs();
  }else{
    console.log("could not log in");
    console.log(response);
    revealRegisterForm(true);
  }
}

function revealDivs(){
  let divs = document.getElementsByTagName("div");

  for (let div of divs){
    div.hidden=false;
  }
}

function revealRegisterForm(boolean){
  let regdivs = document.getElementsByClassName("registerDiv");
  if (boolean){
    for (let div of regdivs){
      div.hidden=false;
    }
  } else{
    for (let div of regdivs){
      div.hidden=true;
    }
  }
}

async function registerUser(){
  
  let uname = document.getElementById("newUsername").value;
  let pwd = document.getElementById("newPassword").value;
  let role = "EMPLOYEE"; /* document.getElementById("userRoleForm").elements["foo"] */
  let fn = document.getElementById("newUserFname").value;
  let ln = document.getElementById("newUserLname").value;
  let eml = document.getElementById("newUserEmail").value;
  let ph = document.getElementById("newUserPhone").value;
  let addr = document.getElementById("newUserAddress").value; 

  
  console.log("making user...");
  
  let userToRegister = {
    username: uname,
    password: pwd,
    role: role,
    firstName: fn,
    lastName: ln,
    eMail: eml,
    phoneNumber: ph,
    address: addr    
  }

  console.log(userToRegister);

  let response = await fetch(url+"register", {
    method:"POST",
    body:JSON.stringify(userToRegister),
    credentials:"include"
  });

  if(response.status===201){
    console.log("user registered succesfully");
    revealRegisterForm(false);
  }else{
    console.log("Could not register user");
    console.log(response);
  }
}


/* async function getTodos(){
  let response = await fetch(url+"todos", {
    credentials:"include"
  });

  if(response.status===200){
    let list = await response.json();

    populateTodoTable(list);
  }
}

function populateTodoTable(list){
  let tableBody = document.getElementById("todoBody");
  tableBody.innerHTML="";
  for(let todo of list){
    let row = document.createElement("tr");
    let id = document.createElement("td");
    let name = document.createElement("td");
    let task = document.createElement("td");
    let status = document.createElement("td");
    let user = document.createElement("td");

    id.innerText = todo.id;
    name.innerText = todo.name;
    task.innerText = todo.task;
    user.innerText = todo.creator.firstName+" "+todo.creator.lastName;
    status.innerText = todo.status;

    row.appendChild(id);
    row.appendChild(name);
    row.appendChild(task);
    row.appendChild(status);
    row.appendChild(user);
    tableBody.appendChild(row);
  }
}



async function updateTodo(){
  let todo = {
    id:document.getElementById("updateTodoId").value,
    name:"",
    task:"",
    status:document.getElementById("updateTodoStatus").value,
    creator:null
  }

  let response = await fetch(url+"todos", {
    method:"PUT",
    body:JSON.stringify(todo),
    credentials:"include"
  });

  if(response.status===200){
    getTodos();
  }else{
    console.log("Could not update Todo");
    console.log(response);
  }
} */