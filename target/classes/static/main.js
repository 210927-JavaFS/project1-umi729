"use strict";
const URL = "http://localhost:8080/";

let emplyeesection = document.getElementById("emplyeesection");
let rmCheck = document.getElementById("rememberMe");
let loginButton = document.getElementById("loginButton");
let viewTicketBtn = document.getElementById("viewAllTicket");
let usr = document.getElementById("username");
let pass = document.getElementById("password");
loginButton.addEventListener("click", imageget);

window.onload = () => {
  if (isNaN(sessionStorage.getItem("role"))) {
    document.getElementById("login").innerHTML = "";
    emplyeesection.style.display = "block";
  } else {
    emplyeesection.style.display = "none";
  }
};

function imageget() {}

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

//loginButton.onclick = loginToApp;
viewTicketBtn.addEventListener("click", viewTicket);

async function loginToApp() {
  let user = {
    username: usr.value,
    password: pass.value,
  };
  lsRememberMe();
  let response = await fetch(URL + "login", {
    method: "POST",
    body: JSON.stringify(user),
    credentials: "include", //This will save the cookie when we receive it.
  });

  if (response.status === 200) {
    let data = await response.json();
    document.getElementById("login").innerHTML = "";
    emplyeesection.style.display = "block";

    //creating session to keep login
    sessionStorage.setItem("userId", data.userId);
    sessionStorage.setItem("username", data.username);
    sessionStorage.setItem("fname", data.fname);
    sessionStorage.setItem("lname", data.lname);
    sessionStorage.setItem("email", data.email);
    sessionStorage.setItem("role", JSON.stringify(data.role));
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
    th[5].innerText ="Status";
    th[6].innerText = "Image of receipt";
    for (let i = 0; i < th.length; i++) {
      row1.appendChild(th[i]);
    }
    thead.appendChild(row1);
  
    tbody.innerHTML = "";

  for (let reim of data2) {
    let row = document.createElement("tr");
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
    td[6].innerText = reim.fileimage;
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
