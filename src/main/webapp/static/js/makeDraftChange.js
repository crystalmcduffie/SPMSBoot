let baseUrl = '/SPMS';
let instructions = document.getElementById('instructions');
let form = document.getElementById('form');
let editorDashboard = document.getElementById('editorDashboard');

loggedEditor = null;
draftId = null;

checkLogin();
getDrafts();
toDashboard();


function toDashboard(){
    editorDashboard.innerHTML = `
        	<h3>Click here to go back to your dashboard</h3>
            <a href="editorDashboard.html">Editor Dashboard</a>
			<br>
            <span>
                <button type="button" id="loginBtnA">Log Out</button>
            </span>
        `;
    let loginBtnA = document.getElementById('loginBtnA');
    loginBtnA.onclick = logoutA;

}

function writeDraftForm(drafts){
	
    if (drafts.length > 0) {
		instructions.innerHTML = "Select a draft that you would like to request a change for.";
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
				<th>Draft</th>
				<th>Story Type</th>
                <th>Genre</th>
				<th>Stage</th>
				<th>Select</th>
            </tr>
        `;

		
        for (let draft of drafts) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${draft.id}</td>
				<td>${draft.draft}</td>
				<td>${draft.storyType.storyType}</td>
				<td>${draft.genre.name}</td>
				<td>${pitch.stage.name}</td>

            `;
			
            let selectDraftBtn = document.createElement('button');
            selectDraftBtn.type = 'button';
            selectDraftBtn.id = draft.id;
            selectDraftBtn.textContent = 'Select';
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(selectDraftBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);
            
            selectDraftBtn.addEventListener('click', selectDraft);
   
        }

        form.appendChild(table);

	}else {
        form.innerHTML = 'There aren\'t any pending drafts in your genre committees.';
	}
	
}
	


function selectDraft(){
    let btnId = event.target.id;
    //let index = btnId.indexOf('_'); // find underscore (see line 46)
   // let id = btnId.slice(index+1); // get text after underscore
    //let name = btnId.replace('_', ''); // remove underscore
    if (confirm('Do want to select draft # ' + btnId + '?')) {
			draftId = btnId;
			writeMessageForm();
        }
}

function writeMessageForm(){
		instructions.innerHTML = "Write a message below. Please include your name.";
		form.innerHTML =  `
	<form>
	<textarea id="message" name="message" rows="4" cols="50">
				
	</textarea>
	<br>
	<button type="button" onclick="submitMessage()" id="submitForm" >Submit</button>
	</form>
	`;
	
	let submitFormBtn = document.getElementById('submitForm');
	submitFormBtn.onclick = submitMessage;
	
}

async function submitMessage(){
	let url = baseUrl + '/message/makeDraftChange';
	url += 'message=' + document.getElementById('submitForm').value + '&';
	url += 'draftId=' + draftId;
    let response = await fetch(url, {method:'POST'});
    if (response.status === 201) {
        alert('Submitted successfully.');
    } else {
        alert('Something went wrong.');
    }
	checkLogin();
}

async function logoutA() {
    let url = baseUrl + '/editor';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedEditor = null;
	
}

async function checkLogin() {
    let url = baseUrl + '/editor/getuser';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) loggedEditor = await response.json();
}

async function getDrafts() {
    let url = baseUrl + '/draft/getDraftsInGenre';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) drafts = await response.json();
	writeDraftForm(drafts);
}

