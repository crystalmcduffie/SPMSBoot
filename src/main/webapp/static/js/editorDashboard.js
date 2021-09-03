let baseUrl = '/SPMS';
let edit = document.getElementById('edit');
let pitchSection = document.getElementById('write_pitch');
let draftSection = document.getElementById('write_draft');
let EditPitch = document.getElementById('edit_pitch');
let CheckMessages = document.getElementById('check_messages');
let MakePitchReview = document.getElementById('make_pitch_review');
let RequestDraftChange = document.getElementById('request_draft_change');
let drafts = null;
let loggedEditor = null;
	
checkLogin();
getDrafts();
//getPitches();
toEditPitch();
//toCheckMessages();
toMakePitchReview();
toRequestDraftChange();
//logout();

function toEditPitch(){
	EditPitch.innerHTML = `
	<h3>Click here to edit a pitch</h3>
	<a href="editPitch.html">Edit Pitch</a>
	`;
}

/*function toCheckMessages(){
	CheckMessages.innerHTML = `
	<h3>Click here to check your messages</h3>
	<a href="editorMessages.html">Check Messages</a>
	`;
}*/

function toMakePitchReview(){
	MakePitchReview.innerHTML = `
	<h3>Click here to make a pitch review </h3>
	<a href="makePitchReview.html">Make Pitch Review</a>
	`;
}

function toRequestDraftChange(){
	RequestDraftChange.innerHTML = `
	<h3>Click here to request a draft change </h3>
	<a href="makeDraftChange.html">Request Draft Message</a>
	`;
}

function logout(){
	let exit = document.getElementById('logout');
    exit.innerHTML = `
            <h3>Click here to log out</h3>
            <span>
                <button type="button" id="loginBtnE">Log Out</button>
            </span>
        `;
    let loginBtnE = document.getElementById('loginBtnE');
    loginBtnE.onclick = logoutE;
}

function writePitches(){
   let pitches = loggedEditor.pitches;

    if (pitches.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Title</th>
				<th>Tag Line</th>
				<th>Genre</th>
                <th>Story Type</th>
                <th>On Hold</th>
				<th>Priority</th>
                <th>Stage</th>
				<th>Approve</th>
				<th>Reject</th>
            </tr>
        `;

		let flag = 0;
		for(let pitch of pitches){
			if(pitch.priority == "high"){
				flag = 1;
			}
		}
		

        for (let pitch of pitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
  				<td>${pitch.storyTitle}</td>
				<td>${pitch.tagLine}</td>
				<td>${pitch.genre.name}</td>
				<td>${pitch.storyType.storyType}</td>
                <td>${pitch.onHold}</td>
				<td>${pitch.priority}</td>
				<td>${pitch.stage.name}</td>

            `;
			
            let approvePitchBtn = document.createElement('button');
            approvePitchBtn.type = 'button';
            approvePitchBtn.id = pitch.id;
            approvePitchBtn.textContent = 'Approve';
			if(pitch.stage.name != "senior_editor"){
				if(pitch.priority == "low"){
					if(flag == 1){
						approvePitchBtn.disabled = true;
					}
				}
			}
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(approvePitchBtn);
            tr.appendChild(btnTd);

			let rejectPitchBtn = document.createElement('button');
            rejectPitchBtn.type = 'button';
            rejectPitchBtn.id = pitch.id;
            rejectPitchBtn.textContent = 'Reject';
			if(pitch.stage.name != "senior_editor"){
				if(pitch.priority == "low"){
					if(flag == 1){
						rejectPitchBtn.disabled = true;
					}
				}
			}
            
            let btnTd2 = document.createElement('td');
            btnTd2.appendChild(rejectPitchBtn);
            tr.appendChild(btnTd2);
			
            table.appendChild(tr);
            
            approvePitchBtn.addEventListener('click', approvePitch);
			rejectPitchBtn.addEventListener('click', rejectPitch);
   
        }

        pitchSection.appendChild(table);

	}else {
        pitchSection.innerHTML = 'You don\'t have any pitches assigned to you.';
	}
	
}

function writeDrafts(drafts){
	
//reset draft table
	draftSection.innerHTML = "";
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
			
            let approveDraftBtn = document.createElement('button');
            approveDraftBtn.type = 'button';
            approveDraftBtn.id = draft.id;
            approveDraftBtn.textContent = 'Approve';
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(approveDraftBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);
            
            approveDraftBtn.addEventListener('click', approveDraft);
           
        }

        draftSection.appendChild(table);

	}else{
		draftSection.innerHTML = "There aren't any pending drafts that you haven\'t approved.";
	}
	
}

async function approvePitch() {
    let btnId = event.target.id;
    //let index = btnId.indexOf('_'); // find underscore (see line 46)
   // let id = btnId.slice(index+1); // get text after underscore
    //let name = btnId.replace('_', ''); // remove underscore
    if (confirm('Do want to approve pitch # ' + btnId + '?')) {
        let url = baseUrl + '/pitch/approve?';
		url += 'id=' + btnId;
        let response = await fetch(url, {method:'PUT'});
        switch (response.status) {
            case 202:
                alert('You approved pitch # ' + btnId + '!');
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
    }
}

async function rejectPitch() {
    let btnId = event.target.id;
    //let index = btnId.indexOf('_'); // find underscore (see line 46)
   // let id = btnId.slice(index+1); // get text after underscore
    //let name = btnId.replace('_', ''); // remove underscore
    if (confirm('Do want to reject pitch # ' + btnId + '?')) {
        let url = baseUrl + '/pitch/reject?';
		url += 'id=' + btnId;
        let response = await fetch(url, {method:'PUT'});
        switch (response.status) {
            case 202:
                alert('You rejected pitch # ' + btnId + '!');
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
    }
}

async function approveDraft() {
    let btnId = event.target.id;
    //let index = btnId.indexOf('_'); // find underscore (see line 46)
   // let id = btnId.slice(index+1); // get text after underscore
    //let name = btnId.replace('_', ''); // remove underscore
    if (confirm('Do want to approve draft # ' + btnId + '?')) {
        let url = baseUrl + '/draft/approve?';
		url += 'id=' + btnId;
        let response = await fetch(url, {method:'PUT'});
        switch (response.status) {
            case 202:
                alert('You approved draft # ' + btnId + '!');
                break;
            case 409:
                alert('That draft doesn\'t seem to exist...');
                break;
            case 401:
                alert('Hold on, you\'re not logged in!');
                break;
            default:
                alert('Something went wrong.');
                break;
        }
		getDrafts();
    }
}

async function checkLogin() {
    let url = baseUrl + '/editor/getuser';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) loggedEditor = await response.json();
	//getPitches();
    writePitches();

}

/*async function getPitches(){
	let url = baseUrl + '/pitch/getEditorPitches';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) {
		pitches = await response.json();
		writePitches(pitches);
	}
}*/

async function getDrafts(){
	let url = baseUrl + '/draft';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) {
		drafts = await response.json();
		writeDrafts(drafts);
	}
}


async function logoutE() {
    let url = baseUrl + '/editor';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedAuthor = null;
}