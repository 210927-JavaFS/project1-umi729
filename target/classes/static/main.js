"use strict";
const URL = "http://13.58.5.112:7000/";
// variable declariation
let emplyeesection = document.getElementById("emplyeesection");
let rmCheck = document.getElementById("rememberMe");
let loginButton = document.getElementById("loginButton");
let addNewBtn = document.getElementById("btnAdd");
let viewticketsection = document.getElementById("viewtickets");
let viewTicketBtn = document.getElementById("viewAllTicket");
let submitMenuBtn = document.getElementById("newTicket");
let usr = document.getElementById("username");
let pass = document.getElementById("password");
let tbody = document.getElementById("vt");
let thead = document.getElementById("vh");
let formView = document.getElementById("addReim");
let viewSubmitSec = document.getElementById("submittickets");
// DOM for form to add in database
let amountf = document.getElementById("amount");
let descriptionf = document.getElementById("textarea1");
let reciptNof = document.getElementById("receiptNo");
let rtypef = document.getElementById("typeofreim");
let formApp = document.getElementById("forAdmin");

// approve / denied variables
let valu = document.getElementById("apText");
let approve = document.getElementById("Approve");
let deny = document.getElementById("Deny");

//filter
let viewFilter = document.getElementById("filt");
let filterby = document.getElementById("filterby");
let goFilterBtn = document.getElementById("btnGo");
//=============================== view toggle ======================
submitMenuBtn.onclick = () => {
  //console.log("in sec");
  viewSubmitSec.style.display = "block";
  formApp.style.display = "none";
  viewFilter.style.display = "none";
  tbody.innerHTML = "";
  thead.innerHTML = "";
};

// ================ on load====================
window.onload = () => {
  if (isNaN(sessionStorage.getItem("role"))) {
    if (sessionStorage.getItem("role") < 1) {
      document.getElementById("login").innerHTML = "";
      emplyeesection.style.display = "block";
      formApp.style.display = "none";
    } else {
      emplyeesection.style.display = "none";
      formApp.style.display = "none";
      viewFilter.style.display = "none";
    }
  } else {
    emplyeesection.style.display = "none";
    formApp.style.display = "none";
    viewFilter.style.display = "none";
  }
};

//========== check is local storage is set to save username and password==========
if (localStorage.checkbox && localStorage.checkbox !== "") {
  rmCheck.setAttribute("checked", "checked");
  usr.value = localStorage.username;
  pass.value = localStorage.password;
} else {
  rmCheck.removeAttribute("checked");
  usr.value = "";
  pass.value = "";
}
// =============== remember me =================================================
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
//===================== login button======================
loginButton.addEventListener("click", loginToApp);
async function loginToApp() {
  let user = {
    username: usr.value,
    password: pass.value,
  };
  //  console.log(user);

  let response = await fetch(URL + "login", {
    method: "POST",
    body: JSON.stringify(user),
    credentials: "include", //This will save the cookie when we receive it.
  });
  lsRememberMe();
  if (response.status === 200) {
    let data = await response.json();
    // console.log(data);
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

// ===================creating view tickets=====================================

goFilterBtn.onclick = () => {
  if (filterby.value == "All") {
    tbody.innerHTML = "";
    thead.innerHTML = "";
    viewTicket();
  } else {
    tbody.innerHTML = "";
    thead.innerHTML = "";
    viewTicketFilter(filterby.value);
  }
};

viewTicketBtn.onclick = () => {
  viewSubmitSec.style.display = "none";
  viewTicket();
  if (sessionStorage.getItem("userId") != 1) {
    formApp.style.display = "none";
    viewFilter.style.display = "flex";
  } else {
    formApp.style.display = "flex";
    viewFilter.style.display = "flex";
  }
};

async function viewTicket() {
  let response = await fetch(URL + "reim", { credentials: "include" });

  if (response.status === 200) {
    let data2 = await response.json();
    if (sessionStorage.getItem("userId") == 1) {
      populateReimTable(data2);
    } else {
      populateReimTableforEmplye(data2);
    }
  } else {
    console.log("Something went wrong!!!");
  }
}
//===========================filtered=========================
async function viewTicketFilter(filter) {
  let response = await fetch(URL + "status/" + filter, {
    credentials: "include",
  });

  if (response.status === 200) {
    let data2 = await response.json();
    if (sessionStorage.getItem("userId") == 1) {
      populateReimTable(data2);
    } else {
      populateReimTableforEmplye(data2);
    }
  } else {
    console.log("Something went wrong!!!");
  }
}

//============================= creating table to view reimburcements for Manager============
function populateReimTable(data2) {
  let row1 = document.createElement("tr");
  let th = [];
  for (let i = 0; i < 8; i++) {
    th[i] = document.createElement("th");
  }
  th[0].innerText = "Id No";
  th[1].innerText = "Amount Requested";
  th[2].innerText = "Date of Submission";
  th[3].innerText = "Description";
  th[4].innerText = "Receipt No";
  th[5].innerHTML = "Submitted by";
  th[6].innerText = "Type of Expense";
  th[7].innerText = "Status";

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
    for (let i = 0; i < 8; i++) {
      td[i] = document.createElement("td");
    }
    td[0].innerText = reim.rid;
    td[1].innerText = reim.amount;
    td[2].innerText = fdate;
    td[3].innerText = reim.description;
    td[4].innerText = reim.reciptNo;
    td[5].innerText = reim.usr.fname + " " + reim.usr.lname;
    td[6].innerText = reim.rtype;
    td[7].innerText = reim.rstatus;

    for (let i = 0; i < td.length; i++) {
      row.appendChild(td[i]);
    }
    tbody.appendChild(row);
  }
}

//============================= creating table to view reimburcements for Employee============
function populateReimTableforEmplye(data2) {
  let row1 = document.createElement("tr");
  let th = [];
  for (let i = 0; i < 8; i++) {
    th[i] = document.createElement("th");
  }
  th[0].innerText = "Amount Requested";
  th[1].innerText = "Date of Submission";
  th[2].innerText = "Date of Resolved";
  th[3].innerText = "Description";
  th[4].innerText = "Receipt No";
  th[5].innerText = "Type of Expense";
  th[6].innerText = "Status";
  th[7].innerText = "Submitted by";
  for (let i = 0; i < th.length; i++) {
    row1.appendChild(th[i]);
  }
  thead.appendChild(row1);
  tbody.innerHTML = "";
  for (let reim of data2) {
    //console.log(sessionStorage.getItem("userId"));
    if (reim.usr.userId == sessionStorage.getItem("userId")) {
      let row = document.createElement("tr");
      //get either by name or by iterate
      const ds = new Date(reim.dateOfSubmit);
      let fdate =
        (ds.getMonth() > 8 ? ds.getMonth() + 1 : "0" + (ds.getMonth() + 1)) +
        "/" +
        (ds.getDate() > 9 ? ds.getDate() : "0" + ds.getDate()) +
        "/" +
        ds.getFullYear();
      let rdate = null;
      if (reim.dateOfResolve != null) {
        const dsr = new Date(reim.dateOfResolve);
        rdate =
          (dsr.getMonth() > 8
            ? dsr.getMonth() + 1
            : "0" + (dsr.getMonth() + 1)) +
          "/" +
          (dsr.getDate() > 9 ? dsr.getDate() : "0" + dsr.getDate()) +
          "/" +
          dsr.getFullYear();
      }
      let td = [];
      for (let i = 0; i < 8; i++) {
        td[i] = document.createElement("td");
      }
      td[0].innerText = reim.amount;
      td[1].innerText = fdate;
      td[2].innerText = rdate;
      td[3].innerText = reim.description;
      td[4].innerText = reim.reciptNo;
      td[5].innerText = reim.rtype;
      td[6].innerText = reim.rstatus;
      td[7].innerText = reim.usr.fname + " " + reim.usr.lname;
      for (let i = 0; i < td.length; i++) {
        row.appendChild(td[i]);
      }
      tbody.appendChild(row);
    }
  }
}

//=============================== approve/deny=================================

//============================log out==================================
let logOutButton = document.getElementById("logout");
logOutButton.addEventListener("click", () => {
  sessionStorage.clear();
  window.location.reload();
});

//==================== add reimburcement in table=======================
addNewBtn.addEventListener("click", addReimFun);
async function addReimFun() {
  let addJson = {
    amount: amountf.value,
    description: descriptionf.value,
    reciptNo: reciptNof.value,
    rtype: rtypef.value,
    rstatus: "Pending",
    usr: {
      userId: sessionStorage.getItem("userId"),
      username: sessionStorage.getItem("username"),
    },
  };
  // console.log(addJson);
  let response = await fetch(URL + "reim", {
    method: "POST",
    body: JSON.stringify(addJson),
    credentials: "include", //This will save the cookie when we receive it.
  });

  if (response.status === 201) {
    //console.log("successful");
    viewSubmitSec.style.display = "none";
    viewTicket();
  } else {
    let para = document.createElement("p");
    para.setAttribute("style", "color:red");
    para.innerText = " FAILED";
    viewSubmitSec.appendChild(para);
  }
}

valu.value = "";

approve.onclick = () => {
  chStatus(valu.value, "Approved");
};
deny.onclick = () => {
  chStatus(valu.value, "Denied");
};

async function chStatus(rid, stat) {
  console.log(rid);
  let status1 = {
    id: rid,
    status: stat,
  };

  let response = await fetch(URL + "reim", {
    method: "PUT",
    body: JSON.stringify(status1),
    credentials: "include", //This will save the cookie when we receive it.
  });

  if (response.status === 204) {
    tbody.innerHTML = "";
    thead.innerHTML = "";
    viewTicket();
  } else {
    console.log("something went worng");
  }
}
