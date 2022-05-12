const url = "http://localhost:8080/ERS/app/"

let loginbtn = document.getElementById("loginButton");
let registerbtn = document.getElementById("registerUserBtn");
let getReimbursementsBtn = document.getElementById("getReimbBtn");
let createReimbBtn = document.getElementById("createReimbBtn");

loginbtn.addEventListener("click", login);
registerbtn.addEventListener("click", registerUser);
getReimbursementsBtn.addEventListener("click", getReimbursements);
createReimbBtn.addEventListener("click", createReimbursement);

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
    revealLoginForm(false);
    revealRegisterForm(false);
    revealReimbTable(true);
    revealCreateReimbFrom(true);
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
function revealLoginForm(boolean){
  let regdivs = document.getElementsByClassName("loginDiv");
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

function revealReimbTable(boolean){
  let regdivs = document.getElementsByClassName("reimbDiv");
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

function revealCreateReimbFrom(boolean){
  let regdivs = document.getElementsByClassName("createReimbDiv");
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
    console.log(list);
    populateReimbTable(list);
  }
}

function populateReimbTable(list){
  let tableBody = document.getElementById("reimbTableBody");
  tableBody.innerHTML="";
  for(let reimb of list){
    console.log(reimb);
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
    try {
      resolved.innerText = reimb.resolved;
      } catch {
        
      } finally {
        resolved.innerText = "no resolved yet";
      }
    try {
      description.innerText = reimb.description;
      } catch {
        description.innerText = "no description provided";
      }    
    try {
      author.innerText = reimb.author.firstName+" "+reimb.author.lastName;
      } catch {
        author.innerText = "";
      }
    try {
      resolver.innerText = reimb.resolver.firstName+" "+reimb.resolver.lastName;
      } catch {        
      } finally {
        resolver.innerText = "no resolver yet";
      }
    status.innerText = reimb.status;
    type.innerText = reimb.reimbType;

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

async function createReimbursement(){
  
  let amt = document.getElementById("newAmount").value;
  let desc = document.getElementById("newDesc").value;
  let type = document.getElementById("newReimbType").value;

  if (amt === "" || desc === ""){
	  let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Please provide data in all fields.";
    return;
  }
  
  let reimbursementToCreate = {
    amount: amt,
    description: desc,
    reimbType: type
  }

  console.log(reimbursementToCreate);

  let response = await fetch(url+"reimbursements", {
    method:"POST",
    body:JSON.stringify(reimbursementToCreate),
    credentials:"include"
  });

  if(response.status===201){
    console.log("Reimbursement created successfuly.");
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Reimbursement created successfully.";
    getReimbursements();
  }else{
    console.log("Could not register reimbursement.");
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Could not register reimbursement. Please try again.";
    console.log(response);
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