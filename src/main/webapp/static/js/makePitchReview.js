let baseUrl = '/SPMS';
let instructions = document.getElementById('instructions');
let form = document.getElementById('form');
let editorDashboard = document.getElementById('editorDashboard');

loggedEditor = null;
aprs = null;
eprs = null;
pitchId = null;

checkLogin();
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

function writePitchForm(){
	let pitches = loggedEditor.pitches;
	
    if (pitches.length > 0) {
		instructions.innerHTML = "Select a pitch that you would like to submit a pitch review for.";
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Title</th>
				<th>Tag Line</th>
				<th>Genre</th>
                <th>Story Type</th>
				<th>Stage</th>
				<th>Select</th>
            </tr>
        `;

		let flag = 0;
		/*for(let pitch of pitches){
			for(let apr of aprs){
				for(let epr of eprs){
					if(pitch.id == apr.id){
						flag = 1;
					}
					if(pitch.id == epr.id){
						flag = 1;
					}
				}
			
			}
		}*/
		
        for (let pitch of pitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
  				<td>${pitch.storyTitle}</td>
				<td>${pitch.tagLine}</td>
				<td>${pitch.genre.name}</td>
				<td>${pitch.storyType.storyType}</td>
				<td>${pitch.stage.name}</td>

            `;
			
            let selectPitchBtn = document.createElement('button');
            selectPitchBtn.type = 'button';
            selectPitchBtn.id = pitch.id;
            selectPitchBtn.textContent = 'Select';
			if(flag == 1){
				selectPitchBtn.disabled = true;
			}
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(selectPitchBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);
            
            selectPitchBtn.addEventListener('click', selectPitch);
   
        }

        form.appendChild(table);

	}else {
        form.innerHTML = 'You don\'t have any pitches assigned to you.';
	}
	
}
	


function selectPitch(){
    let btnId = event.target.id;
    //let index = btnId.indexOf('_'); // find underscore (see line 46)
   // let id = btnId.slice(index+1); // get text after underscore
    //let name = btnId.replace('_', ''); // remove underscore
    if (confirm('Do want to select pitch # ' + btnId + '?')) {
			pitchId = btnId;
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
	let url = baseUrl + '/message/makeE2APitchReview?';
	url += 'message=' + document.getElementById('submitForm').value + '&';
	url += 'pitchId=' + pitchId;
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
	writePitchForm();
}

async function getSentEditorPitchReviews(){
	let url = baseUrl + '/message/getSentEditorPitchReviews';
	let response = await fetch(url, {method: 'GET'});
	if(response.status === 200) eprs = await response.json();
}

async function getSentAuthorPitchReviews(){
	let url = baseUrl + '/message/getSentAuthorPitchReviews';
	let response = await fetch(url, {method: 'GET'});
	if(response.status === 200) aprs = await response.json();
}

