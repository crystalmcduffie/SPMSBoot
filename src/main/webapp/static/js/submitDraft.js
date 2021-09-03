let baseUrl = '/SPMS';
let instructions = document.getElementById('instructions');
let form = document.getElementById('form');
let authorDashboard = document.getElementById('authorDashboard');

loggedAuthor = null;
approvePitches = null;
pitchId = null;

checkLogin();
getApprovedPitches();
toDashboard();


function toDashboard(){
    authorDashboard.innerHTML = `
        	<h3>Click here to go back to your dashboard</h3>
            <a href="authorDashboard.html">Author Dashboard</a>
			<br>
            <span>
                <button type="button" id="loginBtnA">Log Out</button>
            </span>
        `;
    let loginBtnA = document.getElementById('loginBtnA');
    loginBtnA.onclick = logoutA;

}

function writePitchForm(approvedPitches){
	
    if (approvedPitches.length > 0) {
		instructions.innerHTML = "Select a pitch that you would like to submit a draft for.";
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

        for (let pitch of approvedPitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
  				<td>${pitch.storyTitle}</td>
				<td>${pitch.genre.name}</td>
				<td>${pitch.storyType.storyType}</td>

            `;
			
            let approvePitchBtn = document.createElement('button');
            approvePitchBtn.type = 'button';
            approvePitchBtn.id = pitch.id;
            approvePitchBtn.textContent = 'Select';
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(approvePitchBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);
            
            approvePitchBtn.addEventListener('click', selectPitch);
   
        }

        form.appendChild(table);

	}else {
        form.innerHTML = 'You don\'t have any pitches that have been approved.';
	}
	
}
	


function selectPitch(){
    let btnId = event.target.id;
    //let index = btnId.indexOf('_'); // find underscore (see line 46)
   // let id = btnId.slice(index+1); // get text after underscore
    //let name = btnId.replace('_', ''); // remove underscore
    if (confirm('Do want to select pitch # ' + btnId + '?')) {
			pitchId = btnId;
			writeDraftForm();
        }
    }


function writeDraftForm(){
		instructions.innerHTML = "Submit your draft below";
		form.innerHTML =  `
	<form>
	<label for = "draft">Draft:</label>
	<input type = "text" id = "draft" name = "draft" required>
	<br>
	<button type="button" onclick="submitDraft()" id="submitForm" >Submit</button>
	`;
	
	let submitFormBtn = document.getElementById('submitForm');
	submitFormBtn.onclick = submitDraft;
	
}

async function submitDraft(){
		let url = baseUrl + '/draft?';
	url += 'draft=' + document.getElementById('draft').value + '&';
	url += 'pitchId=' + pitchId;
    let response = await fetch(url, {method:'POST'});
    if (response.status === 201) {
        alert('Submitted successfully.');
    } else {
        alert('Something went wrong.');
    }
	checkLogin();
	getApprovedPitches();
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
}

async function getApprovedPitches(){
	let url = baseUrl + '/pitch/getApproved';
	let response = await fetch(url, {method: 'GET'});
	if(response.status === 200) approvedPitches = await response.json();
	writePitchForm(approvedPitches);
}

