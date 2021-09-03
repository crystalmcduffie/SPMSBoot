let baseUrl = '/SPMS';
let auth = document.getElementById('write_auth');
let message = document.getElementById('write_message');
let pitchSection = document.getElementById('write_pitch');
let draftSection = document.getElementById('write_draft');
let CheckMessages = document.getElementById('check_messages');

let loggedAuthor = null;
	
checkLogin();
toCreatePitch();
toSubmitDraft();
toResubmitPitch();
logout();
writePitches();
writeDrafts();

function toCreatePitch(){
	let createPitch = document.getElementById('createPitch');
    createPitch.innerHTML = `
            <h3>Click here to create a pitch</h3>
            <a href="createPitch.html">Create Pitch</a>
			<br>
        `;
}

function toSubmitDraft(){
	let submitDraft = document.getElementById('submitDraft');
    submitDraft.innerHTML = `
            <h3>Click here to submit a draft</h3>
            <a href="submitDraft.html">Create Draft</a>
			<br>
        `;
}

function toResubmitPitch(){
	let resubmitPitch = document.getElementById('resubmitPitch');
	resubmitPitch.innerHTML = `
	<h3>Click here to resubmit a pitch that was placed on hold</h3>
	<a href="resubmitPitch.html">Resubmit Pitch</a>
	`;
}

function toCheckMessages(){
	let CheckMessages = document.getElementById('check_messages');
	CheckMessages.innerHTML = `
	<h3>Click here to check your messages</h3>
	<a href="authorMessages.html">Check Messages</a>
	`;
}

function logout(){
	let exit = document.getElementById('logout');
    exit.innerHTML = `
            <h3>Click here to log out</h3>
            <span>
                <button type="button" id="loginBtnA">Log Out</button>
            </span>
        `;
    let loginBtnA = document.getElementById('loginBtnA');
    loginBtnA.onclick = logoutA;
}

function writePitches(){
	 //auth.innerHTML = `
		//<h3>${loggedAuthor.username}, ${loggedAuthor.points}, ${loggedAuthor.pitches}</h3>
		//`;
   let pitches = loggedAuthor.pitches;

    if (pitches.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Title</th>
				<th>Genre</th>
                <th>Story Type</th>
                <th>On Hold</th>
                <th>Stage</th>
            </tr>
        `;

        for (let pitch of pitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
  				<td>${pitch.storyTitle}</td>
				<td>${pitch.genre.name}</td>
				<td>${pitch.storyType.storyType}</td>
                <td>${pitch.onHold}</td>
				<td>${pitch.stage.name}</td>

            `;

            table.appendChild(tr);
        }

        pitchSection.appendChild(table);
	}else {
        pitchSection.innerHTML = 'You don\'t have any pitches. :(';
	}
	
}

function writeDrafts(){
	 //auth.innerHTML = `
		//<h3>${loggedAuthor.username}, ${loggedAuthor.points}, ${loggedAuthor.pitches}</h3>
		//`;
   let drafts = loggedAuthor.drafts;

    if (drafts.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>StoryType</th>
				<th>Genre</th>
                <th>Stage</th>
            </tr>
        `;

        for (let draft of drafts) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${draft.id}</td>
				<td>${draft.storyType.storyType}</td>
  				<td>${draft.genre.name}</td>
				<td>${draft.stage.name}</td>

            `;

            table.appendChild(tr);
        }

        draftSection.appendChild(table);
	}else {
        draftSection.innerHTML = 'You don\'t have any drafts. :(';
	}
	
}

async function checkLogin() {
    let url = baseUrl + '/author/getuser';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) loggedAuthor = await response.json();
    writePitches();
	//getPitches();
	writeDrafts();

}

/*async function getPitches(){
	let url = baseUrl + '/pitch/getAuthorPitches';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) {
		pitches = await response.json();
		writePitches(pitches);
	}
}*/

async function logoutA() {
    let url = baseUrl + '/author';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedAuthor = null;
}
