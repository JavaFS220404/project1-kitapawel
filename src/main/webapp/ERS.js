const url = "http://localhost:8080/ERS/app/"

let lguname = "";

let loginbtn = document.getElementById("loginButton");
let logoutbtn = document.getElementById("logoutButton");
let registerbtn = document.getElementById("registerUserBtn");
let getReimbursementsBtn = document.getElementById("getReimbBtn");
let getReimbursementsByStatusBtn = document.getElementById("getReimbByStatusBtn");
let createReimbBtn = document.getElementById("createReimbBtn");
let uppdateReimbBtn = document.getElementById("updateReimbBtn");

loginbtn.addEventListener("click", login);
logoutbtn.addEventListener("click", logout);
registerbtn.addEventListener("click", registerUser);
getReimbursementsBtn.addEventListener("click", getReimbursements);
getReimbursementsByStatusBtn.addEventListener("click", getReimbByStatus);
createReimbBtn.addEventListener("click", createReimbursement);
updateReimbBtn.addEventListener("click", updateReimbursement);

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
    let wlcmMsg = document.getElementById("welcomeMsg");
    lguname = uName;
    errMsg.innerText=uName + " has successfully logged in.";
    wlcmMsg.innerText="Welcome to the ERS app " + uName+".";
    revealLoginForm(false);
    revealRegisterForm(false);
    revealReimbTable(true);
    revealCreateReimbFrom(true);
    revealFinanceManagerFunctions(true);
  }else{
    console.log("could not log in");
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Wrong login credentials. Please try again or register.";
    console.log(response);
    revealRegisterForm(true);
  }
}

async function logout(){
  let response = await fetch(url+"logout", {
    credentials:"include"
  });
  if(response.status===200){
    location.href = "index.html";
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
  console.log(regdivs);
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

function revealFinanceManagerFunctions(boolean){
  let regdivs = document.getElementsByClassName("updateReimbDiv");
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
  let role = document.getElementById("newUserRole").value;
  let fn = document.getElementById("newUserFname").value;
  let ln = document.getElementById("newUserLname").value;
  let eml = document.getElementById("newUserEmail").value;
  let ph = document.getElementById("newUserPhone").value;
  let addr = document.getElementById("newUserAddress").value; 

  if (uname === "" || pwd === "" || fn === "" || ln === "" || eml === ""|| addr === ""){
	  let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Please fill in all fields and try again.";
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

async function getReimbByStatus(){
  let response = await fetch(url+"reimbursementsByStatus", {
    credentials:"include"
  });

  if(response.status===200){
    let list = await response.json();
    console.log(list);
    populateReimbTable(list);
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

async function updateReimbursement(){
  let reimb = {
    resolver: lguname,
    id: document.getElementById("reimbID").value,
    status: document.getElementById("updateReimbStatus").value
  }

  console.log(reimb);

  let response = await fetch(url+"reimbursements", {
    method:"PUT",
    body:JSON.stringify(reimb),
    credentials:"include"
  });

  if(response.status===200){
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Successfuly updated the reimbursement.";
    getReimbursements();
  }else if (response.status===401) {
    let errMsg = document.getElementById("registerMsg");
    errMsg.innerText="Current user is not a finance manager. Only finance managers may update reimbursements.";
    console.log("User is not a finance manager");
    console.log(response);
  } else {
    console.log("Could not update reimbursement");
    console.log(response);
  }
}