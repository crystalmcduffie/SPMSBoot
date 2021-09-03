let baseUrl = '/SPMS';
let createPitch = document.getElementById('createPitch');
let authorDashboard = document.getElementById('authorDashboard');
let instructions = document.getElementById('instructions');
let form = document.getElementById('form');
let points = document.getElementById('points');
loggedAuthor=null;
reply = "";
checkLogin();
toCreatePitch();
toDashboard();
getOnHoldPitches();
//writeForm();
//writePoints();

function toCreatePitch(){
	createPitch.innerHTML = `
	<h3>Click here to create a new pitch</h3>
	<a href="createPitch.html">Create Pitch</a>
	`;
}

function toDashboard(){
    authorDashboard.innerHTML = `
        	<h3>Click here to go back to your dashboard</h3>
            <a href="authorDashboard.html">Author Dashboard</a>
			<br>
			<h3>Click here to log out</h3>
            <span>
                <button type="button" id="loginBtnA">Log Out</button>
            </span>
        `;
    let loginBtnA = document.getElementById('loginBtnA');
    loginBtnA.onclick = logoutA;

}

function writeForm(onHoldPitches){
	    if (onHoldPitches.length > 0) {
		instructions.innerHTML = "Select one of your pitches that is on hold and you would like to resubmit.";
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Title</th>
				<th>Genre</th>
                <th>Story Type</th>
				<th>Select</th>
            </tr>
        `;

        for (let pitch of onHoldPitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
  				<td>${pitch.storyTitle}</td>
				<td>${pitch.genre.name}</td>
				<td>${pitch.storyType.storyType}</td>

            `;
			
            let resubmitPitchBtn = document.createElement('button');
            resubmitPitchBtn.type = 'button';
            resubmitPitchBtn.id = pitch.id;
            resubmitPitchBtn.textContent = 'Select';
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(resubmitPitchBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);
            
            resubmitPitchBtn.addEventListener('click', resubmitPitch);
   
        }

        form.appendChild(table);

	}else {
        form.innerHTML = 'You don\'t have any pitches that are on hold.';
	}
}

function writePoints(){
	
	points.innerHTML = `
		<h3>You have ${loggedAuthor.points} points </h3>
	`;
}

function writeLog(reply){
	log.innerHTML = `<h1>${reply}</h1>`;

}


async function resubmitPitch(){
    let btnId = event.target.id;
    //let index = btnId.indexOf('_'); // find underscore (see line 46)
   // let id = btnId.slice(index+1); // get text after underscore
    //let name = btnId.replace('_', ''); // remove underscore
    if (confirm('Do want to resubmit pitch # ' + btnId + '?')) {
        let url = baseUrl + '/pitch/resubmit?';
		url += 'id=' + btnId;
        let response = await fetch(url, {method:'PUT'});
        switch (response.status) {
            case 201:
				reply = await response.json();
                alert('You resubmitted pitch # ' + btnId + '!');
				writeLog(reply);
                break;
            case 409:
                alert('That pitch doesn\'t seem to exist...');
                break;
            case 401:
                alert('Hold on, you\'re not logged in!');
                break;
            default:
                alert('Something went wrong.');
                break;
        }
	checkLogin();
	getOnHoldPitches();
	}
}

async function logoutA() {
    let url = baseUrl + '/author';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedAuthor = null;
}

async function checkLogin() {
    let url = baseUrl + '/author/getuser';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) loggedAuthor = await response.json();
    writePoints();
	
	
}
async function getOnHoldPitches(){
	let url = baseUrl + '/pitch/onHold';
	let response = await fetch(url, {method: 'GET'});
	if(response.status === 200) onHoldPitches = await response.json();
	writeForm(onHoldPitches);
}

