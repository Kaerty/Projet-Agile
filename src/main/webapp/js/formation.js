"use strict";

var showinterns = document.getElementById("oneintern");

showinterns.addEventListener("change", (e) => {
	e.preventDefault();
	console.log("yes");
	document.getElementById("showinterns").style.display = "flex";
})

var allinterns = document.getElementById("allinterns");

allinterns.addEventListener("change", (e) => {
	e.preventDefault();
	console.log("yes");
	document.getElementById("showinterns").style.display = "none";
})



document.getElementById("add_user").addEventListener("submit", (e) => {
	var emailsent = document.getElementById("emailsent");
	emailsent.style.display = "block";
})


