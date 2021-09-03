let baseUrl = '/SPMS';
let auth = document.getElementById('author_login');
let edit = document.getElementById('editor_login');
let loggedAuthor = null;
let loggedEditor = null;
let loggedUser = null;
checkLogin();
//setNav();
function setNav() {
    if (loggedAuthor == null && loggedEditor == null) {
        auth.innerHTML += `
            <h3>Author Login</h3>
            <form>
                <label for="username"> Username: </label>
                <input id="username" name="user" type="text" />
 
                <label for="passwprd"> Password: </label>
                <input id="password" name="pass" type="password" />
               
                <button type="button" id="loginBtnA">Log In</button>
            </form>
        `;
        edit.innerHTML += `
       <h3>Editor Login</h3>
        <form>
            <label for="username">Username: </label>
            <input id="username" name="user" type="text" />
            <label for="passwprd"> Password: </label>
            <input id="password" name="pass" type="password" />
            <button type="button" id="loginBtnE">Log In</button>
        </form>
        `;
    } else {
        auth.innerHTML = `
            <h3>Click below to go to your dashboard</h3>
            <a href="AuthorDashboard.html">Author Dashboard</a>
            <span>
                <button type="button" id="loginBtn">Log Out</button>
            </span>
        `;
        edit.innerHTML = `
    
        `;
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
    url += 'username=' + document.getElementById('username').value + '&';
    url += 'password=' + document.getElementById('password').value;
    let response = await fetch(url, {method: 'POST'});
    
    switch (response.status) {
        case 200: // successful
            loggedAuthor = await response.json();
            setNav();
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('pass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            document.getElementById('username').value = '';
            document.getElementById('password').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function loginE() {
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/editor/login?';
    url += 'username=' + document.getElementById('username').value + '&';
    url += 'password=' + document.getElementById('password').value;
    let response = await fetch(url, {method: 'POST'});
    
    switch (response.status) {
        case 200: // successful
            loggedEditor = await response.json();
            setNav();
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('pass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            document.getElementById('username').value = '';
            document.getElementById('password').value = '';
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
    let url = baseUrl + '/author';
    let response = await fetch(url);
    if (response.status === 200) loggedAuthor = await response.json();
    
    url = baseUrl + '/editor';
     response = await fetch(url);
    if (response.status === 200) loggedEditor = await response.json();
    setNav();
}