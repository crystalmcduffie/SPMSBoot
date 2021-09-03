let baseUrl = '/SPMS';
let auth = document.getElementById('author_login');
let edit = document.getElementById('editor_login');
let register = document.getElementById('register');
let loggedAuthor = null;
let loggedEditor = null;
checkLogin();
setNav();
function setNav() {
    if (loggedAuthor == null && loggedEditor == null) {
        auth.innerHTML += `
            <h3>Author Login</h3>
            <form>
                <label for="aUser"> Username: </label>
                <input id="aUser" name="aUser" type="text" />
 
                <label for="aPass"> Password: </label>
                <input id="aPass" name="aPass" type="password" />
               
                <button type="button" id="loginBtnA">Log In</button>
            </form>
        `;
        edit.innerHTML += `
       <h3>Editor Login</h3>
            <form>
                <label for="eUser"> Username: </label>
                <input id="eUser" name="eUser" type="text" />
 
                <label for="ePass"> Password: </label>
                <input id="ePass" name="ePass" type="password" />
               
                <button type="button" id="loginBtnE">Log In</button>
            </form>
        `;

		register.innerHTML = `
		<h3>Click here to register an account</h3>
		<a href="register.html" style = "align-content:center;">Register</a>
		`;
    }
	if (loggedAuthor != null){
        auth.innerHTML = `
            <h3>Click below to go to your dashboard</h3>
            <a href="authorDashboard.html">Author Dashboard</a>
            <span>
                <button type="button" id="loginBtnA">Log Out</button>
            </span>
        `;
		edit.innerHTML = "";
		register.innerHTML = "";
	}else if (loggedEditor != null){
        edit.innerHTML = `
            <h3>Click below to go to your dashboard</h3>
            <a href="editorDashboard.html">Editor Dashboard</a>
            <span>
                <button type="button" id="loginBtnE">Log Out</button>
            </span>
        `;
		auth.innerHTML = "";
		register.innerHTML = "";
    }


    let loginBtnA = document.getElementById('loginBtnA');
    let loginBtnE = document.getElementById('loginBtnE');
    if (loggedAuthor) loginBtnA.onclick = logoutA;
    else loginBtnA.onclick = loginA;
    if (loggedEditor) loginBtnE.onclick = logoutE;
    else loginBtnE.onclick = loginE;
}

async function loginA() {
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/author/login?';
    url += 'username=' + document.getElementById('aUser').value + '&';
    url += 'password=' + document.getElementById('aPass').value;
    let response = await fetch(url, {method: 'POST'});
    
    switch (response.status) {
        case 200: // successful
            loggedAuthor = await response.json();
            setNav();
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('aPass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            document.getElementById('aUser').value = '';
            document.getElementById('aPass').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function loginE() {
    // http://localhost:8080/editor/login?username=gabriel&password=pass
    let url = baseUrl + '/editor/login?';
    url += 'username=' + document.getElementById('eUser').value + '&';
    url += 'password=' + document.getElementById('ePass').value;
    let response = await fetch(url, {method: 'POST'});
    
    switch (response.status) {
        case 200: // successful
            loggedEditor = await response.json();
            setNav();
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('ePass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            document.getElementById('eUser').value = '';
            document.getElementById('ePass').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function logoutA() {
    let url = baseUrl + '/author';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedAuthor = null;
    setNav();
}

async function logoutE() {
    let url = baseUrl + '/editor';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedEditor = null;
    setNav();
}

async function checkLogin() {
	if(loggedAuther != null){
    let url = baseUrl + '/author/getuser';
    let response = await fetch(url);
    if (response.status === 200) loggedAuthor = await response.json();
    }
	else{
    url = baseUrl + '/editor/getuser';
     response = await fetch(url);
    if (response.status === 200) loggedEditor = await response.json();
    }
	setNav();
}