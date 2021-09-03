let baseUrl = '/SPMS';
let editorDashboard = document.getElementById('editorDashboard');
let instructions = document.getElementById('instructions');
let form1 = document.getElementById('form1');
let form2 = document.getElementById('form2');
loggedEditor=null;
seniorEditorPitches = null;
pitchId = null;

checkLogin();
getSeniorEditorPitches();
toDashboard();


function toDashboard(){
    editorDashboard.innerHTML = `
        	<h3>Click here to go back to your dashboard</h3>
            <a href="editorDashboard.html">Editor Dashboard</a>
			<br>
			<h3>Click here to log out</h3>
            <span>
                <button type="button" id="loginBtnE">Log Out</button>
            </span>
        `;
    let loginBtnE = document.getElementById('loginBtnE');
    loginBtnE.onclick = logoutE;

}

function writePitchSelectForm(seniorEditorPitches){
		instructions.innerHTML = "";
		form1.innerHTML = "";
		form2.innerHTML = "";
	    if (seniorEditorPitches.length > 0) {
		instructions.innerHTML = "Select a pitch to edit.";
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Title</th>
				<th>Genre</th>
                <th>Story Type</th>
				<th>Tag Line</th>
				<th>Completion Date</th>
				<th>Select</th>
            </tr>
        `;

        for (let pitch of seniorEditorPitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
  				<td>${pitch.storyTitle}</td>
				<td>${pitch.genre}</td>
				<td>${pitch.storyType}</td>
				<td>${pitch.tagLine}</td>
				<td>${pitch.completionDate}

            `;
			
            let editPitchBtn = document.createElement('button');
            editPitchBtn.type = 'button';
            editPitchBtn.id = pitch.id;
            editPitchBtn.textContent = 'Edit';
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(editPitchBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);
            
            editPitchBtn.addEventListener('click', selectPitch);
   
        }

        form1.appendChild(table);

	}else {
        form1.innerHTML = 'You are not a senior editor for any pitches.';
	}
}

function writePitchEditForm(selectedPitch){
	instructions.innerHTML = "Make your edits below.";
	let table = document.createElement('table');
	let tr = document.createElement('tr');
		
	form1.innerHTML = "";
    table.innerHTML = `
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Genre</th>
			<th>Story Type</th>
			<th>Completion Date</th>
		</tr>
        `;
       
	tr.innerHTML = `
		<td>${selectedPitch.id}</td>
		<td>${selectedPitch.storyTitle}</td>
		<td>${selectedPitch.genre.name}</td>
		<td>${selectedPitch.storyType.storyType}</td>
		<td>${selectedPitch.tagLine}</td>
		<td>${selectedPitch.completionDate}</td>

            `;
		
	table.appendChild(tr);	
    form1.appendChild(table);
	
	form2.innerHTML = `
	<form>
	<label for = "title">Change Title:</label>
	<input type = "text" id = "title" name = "title">
	<br>
	<label for = "tag_line">Change Tag Line:</label>
	<input type = "text" id = "tag_line" name = "tag_line">
	<br>
	<label for = "completion_date">Change Completion Date:</label>
	<input type = "text" id = "completion_date" name = "completion_date">
	<br>
	<button type="button" onclick="submitEdit()" id="submitForm" >Submit</button>
	`;
	
	let submitFormBtn = document.getElementById('submitForm');
	submitFormBtn.onclick = submitEdit;
}


async function selectPitch(){
    let btnId = event.target.id;
    //let index = btnId.indexOf('_'); // find underscore (see line 46)
   // let id = btnId.slice(index+1); // get text after underscore
    //let name = btnId.replace('_', ''); // remove underscore
    if (confirm('Do want to edit pitch # ' + btnId + '?')) {
		pitchId = btnId;
		let url = baseUrl + '/pitch/update?';
		url += 'id=' +btnId;
		let response = await fetch(url, {method: 'GET'});
		if(response.status == 200){
			let selectedPitch = await response.json();
			writePitchEditForm(selectedPitch);
		}else{
			alert('Something went wrong.');
		}
		
	}
}

async function submitEdit(){
		let url = baseUrl + '/pitch/update?';
	url += 'title=' + document.getElementById('title').value + '&';
	url += 'tagline=' + document.getElementById('tag_line').value + '&';
	url += 'completionDate=' + document.getElementById('completion_date').value + '&';
	url += 'id=' + pitchId;
    let response = await fetch(url, {method:'PUT'});
    if (response.status === 202) {
        alert('Submitted successfully.');
    } else {
        alert('Something went wrong.');
    }
	checkLogin();
	getSeniorEditorPitches();
}



async function logoutE() {
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
async function getSeniorEditorPitches(){
	let url = baseUrl + '/pitch/seniorEditor';
	let response = await fetch(url, {method: 'GET'});
	if(response.status === 200) seniorEditorPitches = await response.json();
	writePitchSelectForm(seniorEditorPitches);
}


