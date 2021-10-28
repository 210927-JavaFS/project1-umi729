"use strict";
const URL = "http://localhost:8080/";
// variable declariation 
let emplyeesection = document.getElementById("emplyeesection");
let rmCheck = document.getElementById("rememberMe");
let loginButton = document.getElementById("loginButton");
let addNewBtn = document.getElementById("btnAdd");
let viewTicketBtn = document.getElementById("viewAllTicket");
let usr = document.getElementById("username");
let pass = document.getElementById("password");

// DOM for form to add in database
let amountf=document.getElementById("amount");
//amountf=Number(amountf.value);
let descriptionf=document.getElementById("textarea1");
let reciptNof=document.getElementById("receiptNo");
//reciptNof=Number(reciptNof.value);
let rtypef=document.getElementById("typeofreim");
let blobURL;
let binaryImg;
let fileimagef=document.getElementById("inputGroupFile02");
fileimagef.addEventListener('change', readFile, false);

window.onload = () => {
  if (isNaN(sessionStorage.getItem("role"))) {
    document.getElementById("login").innerHTML = "";
    emplyeesection.style.display = "block";
  } else {
    emplyeesection.style.display = "block";
    //emplyeesection.style.display = "none";
  }
};
// check is local storage is set to save username and password
if (localStorage.checkbox && localStorage.checkbox !== "") {
  rmCheck.setAttribute("checked", "checked");
  usr.value = localStorage.username;
  pass.value = localStorage.password;
} else {
  rmCheck.removeAttribute("checked");
  usr.value = "";
  pass.value = "";
}

function lsRememberMe() {
  if (rmCheck.checked && usr.value !== "") {
    localStorage.username = usr.value;
    localStorage.password = pass.value;
    localStorage.checkbox = rmCheck.value;
  } else {
    localStorage.username = "";
    localStorage.password = "";
    localStorage.checkbox = "";
  }
}

loginButton.addEventListener("click", loginToApp);

viewTicketBtn.addEventListener("click", viewTicket);

async function loginToApp() {
  let user = {
    username: usr.value,
    password: pass.value
  };
  console.log(user);

  let response = await fetch(URL + "login", {
    method: "POST",
    body: JSON.stringify(user),
    credentials: "include", //This will save the cookie when we receive it.
  });
  lsRememberMe();
  if (response.status === 200) {
    let data = await response.json();
    console.log(data);
    document.getElementById("login").innerHTML = "";
    emplyeesection.style.display = "block";

    //creating session to keep login
    
    sessionStorage.setItem("userId", data.userId);
    sessionStorage.setItem("username", data.username);
    sessionStorage.setItem("fname", data.fname);
    sessionStorage.setItem("lname", data.lname);
    sessionStorage.setItem("email", data.email);
    sessionStorage.setItem("role", JSON.stringify(data.role));
    //console.log(JSON.parse(sessionStorage.getItem("role")))
  } else {
    let para = document.createElement("p");
    para.setAttribute("style", "color:red");
    para.innerText = "LOGIN FAILED";
    document.getElementsByClassName("modal-body")[0].appendChild(para);
  }
}

// creating view tickets

async function viewTicket() {
  let response = await fetch(URL + "reim", { credentials: "include" });

  if (response.status === 200) {
    let data2 = await response.json();
    populateReimTable(data2);
  } else {
    console.log("Need to find out the issue");
  }
}
function populateReimTable(data2) {
  let tbody = document.getElementById("vt");
  let thead = document.getElementById("vh");
  let row1 = document.createElement("tr");
  let th = [];
  for (let i = 0; i < 7; i++) {
    th[i] = document.createElement("th");
  }
  th[0].innerText = "Amount Requested";
  th[1].innerText = "Date of Submission";
  th[2].innerText = "Description";
  th[3].innerText = "Receipt No";
  th[4].innerText = "Type of Expense";
  th[5].innerText = "Status";
  th[6].innerText = "Image of receipt";
  for (let i = 0; i < th.length; i++) {
    row1.appendChild(th[i]);
  }
  thead.appendChild(row1);

  tbody.innerHTML = "";

  for (let reim of data2) {

    let row = document.createElement("tr");
    let img = document.createElement("img");
    //get either by name or by iterate
    const ds = new Date(reim.dateOfSubmit);
    let fdate =
      (ds.getMonth() > 8 ? ds.getMonth() + 1 : "0" + (ds.getMonth() + 1)) +
      "/" +
      (ds.getDate() > 9 ? ds.getDate() : "0" + ds.getDate()) +
      "/" +
      ds.getFullYear();
    let td = [];
    for (let i = 0; i < 7; i++) {
      td[i] = document.createElement("td");
    }
    td[0].innerText = reim.amount;
    td[1].innerText = fdate;
    td[2].innerText = reim.description;
    td[3].innerText = reim.reciptNo;
    td[4].innerText = reim.rtype;
    td[5].innerText = reim.rstatus;
    let blob = new Blob([reim.fileimage]);
    blobURL = window.URL.createObjectURL(blob);
    img.src=blobURL;
    td[6].appendChild(img);
      
    
    for (let i = 0; i < td.length; i++) {
      row.appendChild(td[i]);
    }
    tbody.appendChild(row);
  }
}
let logOutButton = document.getElementById("logout");
logOutButton.addEventListener("click", () => {
  sessionStorage.clear();
  window.location.reload();
});
addNewBtn.addEventListener("click", addReimFun);


// add reimburcement in table


async function addReimFun() {
  let addJson = {
    amount: amountf.value,
    description: descriptionf.value,
    reciptNo: reciptNof.value,
    rtype: rtypef.value,
    rstatus: "Pending",
    fileimage: blobURL,
    usr: { userId : sessionStorage.getItem("userId"),
    username : sessionStorage.getItem("username"),
  }
  };
  console.log(addJson);
  let response = await fetch(URL + "reim", {
    method: "POST",
    body: JSON.stringify(addJson),
    credentials: "include", //This will save the cookie when we receive it.
  });

  if (response.status === 200) {
    console.log("successful")
  } else {
    let para = document.createElement("p");
   // para.setAttribute("style", "color:red");
    //para.innerText = " FAILED";
    //document.getElementsByClassName("modal-body")[0].appendChild(para);
  }
}




// Convert a base64 string into a binary Uint8 Array 
// https://gist.github.com/borismus/1032746
function convertDataURIToBinary(dataURI) {
	let BASE64_MARKER = ';base64,';
	let base64Index = dataURI.indexOf(BASE64_MARKER) + BASE64_MARKER.length;
	let base64 = dataURI.substring(base64Index);
	let raw = window.atob(base64);
	let rawLength = raw.length;
	let array = new Uint8Array(new ArrayBuffer(rawLength));

	for(let i = 0; i < rawLength; i++) {
		array[i] = raw.charCodeAt(i);
	}
	return array;
}

// File Reader
// https://developer.mozilla.org/en-US/docs/Web/API/FileReader/readAsDataURL
function readFile(evt) {
    let f = evt.target.files[0]; 

    if (f) {
		if ( /(jpe?g|png|gif)$/i.test(f.type) ) {
			let r = new FileReader();
			r.onload = function(e) { 
				let base64Img = e.target.result;
			  binaryImg = convertDataURIToBinary(base64Img);
				let blob = new Blob([binaryImg], {type: f.type});
				blobURL = window.URL.createObjectURL(blob);
				
				
				//console.log(blobURL);
				
				
			//	document.getElementById('blobImg').src = blobURL;
			//	document.getElementById('binaryImg').innerHTML = JSON.stringify(binaryImg, null, 2);
			}
			r.readAsDataURL(f);
		} else { 
			alert("Failed file type");
		}
    } else { 
		alert("Failed to load file");
    }
}

