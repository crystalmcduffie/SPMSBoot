let baseUrl = '/SPMS';

registerForm();
toLogin();

function registerForm(){
	let register = document.getElementById('register');
	register.innerHTML = `
	 <h3>Register Author</h3>
     <form>
     	<label for="user"> Username: </label>
        <input id="user" name="user" type="text" />
 		<br>
        <label for="pass"> Password: </label>
        <input id="pass" name="pass" type="password" />
         <br>
        <button type="button" id="registerBtn">Register</button>
    </form>
	`;
	
	let registerBtn= document.getElementById('registerBtn');
	registerBtn.onclick = registerUser();
}

function toLogin(){
	let login = document.getElementById('to_login');
	login.innerHTML = `
	<h3>Click here to go back to the log in page</h3>
	<a href="index.html">Back to Login</a>
	`;
}

function clearForm(){
	register.innerHTML = "";
}

async function registerUser() {
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/author/register?';
    url += 'username=' + document.getElementById('user').value + '&';
    url += 'password=' + document.getElementById('pass').value;
    let response = await fetch(url, {method: 'POST'});
    if(response.status == 200){
	alert('Succcess');
	clearForm();
	}
	else{
		alert('Something went wrong');
	}
}
