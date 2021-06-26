const signInButton = document.querySelector("#sign-in-btn");
const signUpButton = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

signUpButton.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

signInButton.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});

function login() {
	event.preventDefault();
	let username = document.querySelector("#usernameLogin").value;
	let password = document.querySelector("#passwordLogin").value;
	let url = "vegapp/v1/users/login";
	let loginDetails = {
		"username": username,
		"password": password
	};
	axios.post(url, loginDetails).then(res => {
		let data = res.data;
		localStorage.setItem("user", JSON.stringify(data));
		if (data.role == "C") {
			toastr.success("Login Successful");
			setTimeout(function(){ window.location.href = "BuyVegetables.html";}, 1000);
		}
		else if (data.role == "A") {
			toastr.success("Login Successful");
			setTimeout(function(){ window.location.href = "ListOfOrders.html";}, 1000);
		}
		else {
			toastr.success("Login Successful");
			setTimeout(function(){ window.location.href = "DeliverOrders.html";}, 1000);
		}
	}).catch((error) => {
		toastr.error(error.response.data.errorMessage);
	});
}

function register() {
	event.preventDefault();
	let name = document.querySelector("#name").value;
	let age = document.querySelector("#age").value;
	let mobileNumber = document.querySelector("#mobileNumber").value;
	let email = document.querySelector("#email").value;
	let username = document.querySelector("#usernameRegistration").value;
	let password = document.querySelector("#passwordRegistration").value;
	let url = "vegapp/v1/users/register";
	let userDetails = {
		"name": name,
		"age": age,
		"mobileNumber": mobileNumber,
		"email": email,
		"username": username,
		"password": password
	};

	axios.post(url, userDetails).then(res => {
		let data = res.data;
		if (data) {
			toastr.success("Registration Successful");
			container.classList.remove("sign-up-mode");
		}
		else {
			toastr.error("Registration failed");
		}
	}).catch((error) => {
		let data = error.response.data;
		if (data.errors == null) {
			let errorMessage = data.errorMessage;
			if (errorMessage == "E_ER01") {
				toastr.error("Email ID is already used");
			}
			else if (errorMessage == "E_MR01") {
				toastr.error("Mobile Number is already used");
			}
			else if (errorMessage == "E_UR01"){
				toastr.error("Username is already used");
			}
			else{
				toastr.error(errorMessage);
			}
		}
		else {
            let listOfErrors = data.errors;
			for(let error of listOfErrors){
                toastr.error(error);
            }
		}
	});

}