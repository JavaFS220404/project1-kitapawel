const url = "http://localhost:8080/ERS/app/"

let loginbtn = document.getElementById("loginButton");
let registerbtn = document.getElementById("registerUserBtn");
let getReimbursements = document.getElementById("getReimbBtn");

loginbtn.addEventListener("click", login);
registerbtn.addEventListener("click", registerUser);
getReimbursements.addEventListener("click", getReimbursements)

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
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText=uName + " has successfully logged in.";
    //revealDivs();
  }else{
    console.log("could not log in");
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Wrong login credentials. Please try again or register.";
    console.log(response);
    revealRegisterForm(true);
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

  if (uname === "" || pwd === ""){
	  let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Username and password cannot be empty. Please try again.";
    return;
  }
  
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
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="User registered successfully. Please login using the provided credentials.";
    revealRegisterForm(false);
  }else{
    console.log("Could not register user");
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Could not register user. User already exists. Please try again.";
    console.log(response);
  }
}


async function getReimbursements(){
  let response = await fetch(url+"reimbursements", {
    credentials:"include"
  });

  if(response.status===200){
    let list = await response.json();

    populateReimbTable(list);
  }
}

function populateReimbTable(list){
  let tableBody = document.getElementById("reimbTableBody");
  tableBody.innerHTML="";
  for(let reimb of list){
    let row = document.createElement("tr");
    let id = document.createElement("td");
    let amount = document.createElement("td");
    let submitted = document.createElement("td");
    let resolved = document.createElement("td");
    let description = document.createElement("td");
    let author = document.createElement("td");
    let resolver = document.createElement("tr");
    let status = document.createElement("td");
    let type = document.createElement("td");

    id.innerText = reimb.id;
    amount.innerText = reimb.amount;
    submitted.innerText = reimb.submitted;
    resolved.innerText = reimb.resolved;
    description.innerText = reimb.description;
    author.innerText = reimb.author.firstName+" "+reimb.resolver.lastName;
    resolver.innerText = reimb.resolver.firstName+" "+reimb.resolver.lastName;
    status.innerText = reimb.status;
    type.innerText = reimb.type;

    row.appendChild(id);
    row.appendChild(amount);
    row.appendChild(submitted);
    row.appendChild(resolved);
    row.appendChild(description);
    row.appendChild(author);
    row.appendChild(resolver);
    row.appendChild(status);
    row.appendChild(type);
    tableBody.appendChild(row);
  }
}



async function updateReimbursements(){
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
}