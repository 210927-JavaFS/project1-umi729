const URL = "http://localhost:8080/";

let buttonRow = document.getElementById("buttonRow");
let loginButton = document.getElementById('loginButton');

loginButton.addEventListener("click",loginToApp);

//loginButton.onclick = loginToApp; 


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
    document.getElementById("modal-dialog").innerHTML = '';
    console.log(data);
    
  }
  else{
    let para = document.createElement("p");
    para.setAttribute("style", "color:red")
    para.innerText = "LOGIN FAILED"
    document.getElementsByClassName("modal-body")[0].appendChild(para);
  }
}



