'use strict';
const URL = "http://localhost:8080/";

let emplyeesection = document.getElementById("emplyeesection");

let loginButton = document.getElementById('loginButton');
let viewTicketBtn= document.getElementById("viewAllTicket");
loginButton.addEventListener("click",imageget);
window.onload=()=>{
  emplyeesection.style.display="block";
}
function imageget(){

  
}



loginButton.addEventListener("click",loginToApp);

//loginButton.onclick = loginToApp; 
viewTicketBtn.addEventListener("click", viewTicket);

async function loginToApp(){
  let user = {
    username:document.getElementById("username").value,
    password:document.getElementById("password").value
  }

  let response = await fetch(URL+"login", {
    method:"POST",
    body:JSON.stringify(user),
    credentials:"include" //This will save the cookie when we receive it. 
  });

  if(response.status===200){
    let data = await response.json();
    document.getElementById("login").innerHTML = '';
    //sessionStorage.setItem
    emplyeesection.style.display="block";
    console.log(data);
    
  }
  else{
    let para = document.createElement("p");
    para.setAttribute("style", "color:red")
    para.innerText = "LOGIN FAILED"
    document.getElementsByClassName("modal-body")[0].appendChild(para);
  }
}


// creating view tickets

async function viewTicket(){
  let response = await fetch(URL+"reim", {credentials:"include"});

  if(response.status === 200){
    let data2 = await response.json();
    populateReimTable(data2);
  }else{
    console.log("Need to find out the issue");
  }

}
function populateReimTable(data2){
  let tbody = document.getElementById("vt");

  tbody.innerHTML="";
console.log(data2);
  for(let reim of data2){
    let row = document.createElement("tr");
    console.log(reim);
    for(let cell in reim){
      let td = document.createElement("td");
     
      td.innerText = reim[cell];
     
      row.appendChild(td);
    
    tbody.appendChild(row);
  }
}
}