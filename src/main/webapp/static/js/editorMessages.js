let baseUrl = '/SPMS';
let senteprs = document.getElementById('sent_editor_pitch_reviews');
let receivedeprs = document.getElementById('received_editor_pitch_reviews');
let sentaprs = document.getElementById('sent_author_pitch_reviews');
let receivedaprs = document.getElementById('received_author_pitch_reviews');
let apitchMessage = document.getElementById('apitch_messages');
let epitchMessage = document.getElementById('epitch_messages');
let sentdcs = document.getElementById('sent_draft_changes');
let receiveddcs = document.getElementById('received_draft_changes');
let draftMessage = document.getElementById('draft_messages');
let Dashboard = document.getElementById('editor_dashboard');
let loggedEditor = null;
	
checkLogin();
toDashboard();

function toDashboard(){
	Dashboard.innerHTML = `
	<h3>Click here to go back to your dashboard</h3>
	<a href="editorDashboard.html">Editor Dashboard</a>
	`;
}

function writeSentEPRs(sentEPRs){

    if (sentEPRs.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
			
            <tr>
				<th colspan="3">Pitch Reviews Sent to Other Editors</th>
			</tr>
			<tr>
                <th>ID</th>
                <th>Pitch Id</th>
				<th>Editor</th>
            </tr>
        `;
		
        for (let pr of sentEPRs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pr.id}</td>
  				<td>${pr.pitch.id}</td>
				<td>${pr.editor.username}</td>
            `;
		}
			
        table.appendChild(tr);
        senteprs.appendChild(table);

	}else {
        senteprs.innerHTML = 'You haven\'t sent any current pitch reviews to an editor.';
	}
	
}

function writeReceivedEPRs(receivedEPRs){

    if (receivedEPRs.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
			
            <tr>
				<th colspan="3">Pitch Reviews Received From Other Editors</th>
			</tr>
			<tr>
                <th>ID</th>
                <th>Pitch Id</th>
				<th>Editor</th>
            </tr>
        `;
		
        for (let pr of receivedEPRs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pr.id}</td>
  				<td>${pr.pitch.id}</td>
				<td>${pr.editor.username}</td>
            `;
		}
			
        table.appendChild(tr);
        receivedeprs.appendChild(table);

	}else {
        receivedeprs.innerHTML = 'You don\'t have any current pitch reviews from an editor.';
	}
	
}

function writeSentAPRs(sentAPRs){

    if (sentAPRs.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
			
            <tr>
				<th colspan="3">Pitch Reviews Sent to Authors</th>
			</tr>
			<tr>
                <th>ID</th>
                <th>Pitch Id</th>
				<th>Editor</th>
            </tr>
        `;
		
        for (let pr of sentAPRs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pr.id}</td>
  				<td>${pr.pitch.id}</td>
				<td>${pr.editor.username}</td>
            `;
		}
			
        table.appendChild(tr);
        sentaprs.appendChild(table);

	}else {
        sentaprs.innerHTML = 'You haven\'t sent any current pitch reviews to an author.';
	}
	
}

function writeReceivedAPRs(receivedAPRs){

    if (receivedAPRs.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
			
            <tr>
				<th colspan="3">Pitch Reviews Responses Received From Authors</th>
			</tr>
			<tr>
                <th>ID</th>
                <th>Pitch Id</th>
				<th>Editor</th>
            </tr>
        `;
		
        for (let pr of receivedAPRs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pr.id}</td>
  				<td>${pr.pitch.id}</td>
				<td>${pr.editor.username}</td>
            `;
		}
			
        table.appendChild(tr);
        receivedaprs.appendChild(table);

	}else {
        receivedaprs.innerHTML = 'You don\'t have any current pitch review responses from an author.';
	}
	
}

function writeSentDCs(sentDCs){

    if (sentDCs.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
			
            <tr>
				<th colspan="3">Draft Change Requests Sent to Authors</th>
			</tr>
			<tr>
                <th>ID</th>
                <th>Pitch Id</th>
				<th>Editor</th>
            </tr>
        `;
		
        for (let pr of sentDCs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pr.id}</td>
  				<td>${pr.pitch.id}</td>
				<td>${pr.editor.username}</td>
            `;
		}
			
        table.appendChild(tr);
        sentdcs.appendChild(table);

	}else {
        sentdcss.innerHTML = 'You havn\'t sent any draft change requests to an author.';
	}
	
}

function writeReceivedDCs(receivedDCs){

    if (receivedDCs.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
			
            <tr>
				<th colspan="3">Draft Change Responses Received From Authors</th>
			</tr>
			<tr>
                <th>ID</th>
                <th>Pitch Id</th>
				<th>Editor</th>
            </tr>
        `;
		
        for (let pr of receivedDCs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pr.id}</td>
  				<td>${pr.pitch.id}</td>
				<td>${pr.editor.username}</td>
            `;
		}
			
        table.appendChild(tr);
        receiveddcs.appendChild(table);

	}else {
        receiveddcs.innerHTML = 'You havn\'t received any draft change responses from an author.';
	}
	
}

function writeAuthorPMs(authorPMs){

    if (authorPMs.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
			
            <tr>
				<th colspan="2">Your Pitch Messages</th>
			</tr>
        `;
		
        for (let pm of authorPMs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
			<tr>
                <th>ID</th>
                <th>Pitch Id</th>
            </tr>
                <td>${pm.id}</td>
  				<td>${pm.pitch.id}</td>
			<tr>
				<textarea id="pitchMessage" name="pitchMessage" rows="4" cols="50">
				${pm.message}
				</textarea>
			</tr>
            `;
			
		}
			
        table.appendChild(tr);
        apitchMessage.appendChild(table);

	}else {
        apitchMessage.innerHTML = 'You don\'t have pitch messages.';
	}
	
}

function writeEditorPMs(editorPMs){

    if (editorPMs.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
			
            <tr>
				<th colspan="2">Your Pitch Messages</th>
			</tr>
        `;
		
        for (let pm of editorPMs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
			<tr>
                <th>ID</th>
                <th>Pitch Id</th>
            </tr>
                <td>${pm.id}</td>
  				<td>${pm.pitch.id}</td>
			<tr>
				<textarea id="pitchMessage" name="pitchMessage" rows="4" cols="50">
				${pm.message}
				</textarea>
			</tr>
            `;
			
		}
			
        table.appendChild(tr);
        epitchMessage.appendChild(table);

	}else {
        epitchMessage.innerHTML = 'You don\'t have pitch messages.';
	}
	
}

function writeDraftMessages(draftMessages){

    if (draftMessages.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
			
            <tr>
				<th colspan="2">Your Pitch Messages</th>
			</tr>
        `;
		
        for (let pr of draftMessages) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
			<tr>
                <th>ID</th>
                <th>Pitch Id</th>
            </tr>
                <td>${dm.id}</td>
  				<td>${dm.draft.id}</td>
			<tr>
				<textarea id="pitchMessage" name="pitchMessage" rows="4" cols="50">
				${dm.message}
				</textarea>
			</tr>
            `;
			
		}
			
        table.appendChild(tr);
        draftMessage.appendChild(table);

	}else {
        draftMessage.innerHTML = 'You don\'t have pitch messages.';
	}
	
}

async function checkLogin() {
    let url = baseUrl + '/editor/getuser';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) loggedEditor = await response.json();
	getSentEPRs();
	getReceivedEPRs();
	getSentAPRs();
	getReceivedAPRs();
	getSentDCs();
	getReceivedDCs();
	getPitchMessages();
	getDraftMessages();

}
async function getSentEPRs(){
	    let url = baseUrl + '/message/getSentEditorPitchReviews';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200){
		let sentEPRs = await response.json();
		writeSentERPs(sentEPRs);
	}  
}

async function getReceivedEPRs(){
	    let url = baseUrl + '/message/getReceivedEditorPitchReviews';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200){
		let receivedEPRs = await response.json();
		writeReceivedEPRs(receivedEPRs);
	}  
}

async function getSentAPRs(){
	    let url = baseUrl + '/message/getSentAuthorPitchReviews';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200){
		let sentAPRs = await response.json();
		writeSentAPRs(sentAPRs);

	}  
}

async function getReceivedAPRs(){
	    let url = baseUrl + '/message/getReceivedAuthorPitchReviews';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200){
		let receivedAPRs = await response.json();
		writeReceivedAPRs(receivedAPRs);
	}  
}

async function getSentDCs(){
	    let url = baseUrl + '/message/getSentDraftChanges';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200){
		let sentDCs = await response.json();
		writeSentDCs(sentDCs);
	}  
}

async function getReceivedDCs(){
	    let url = baseUrl + '/message/getReceivedDraftChanges';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200){
		let receivedDCs = await response.json();
		writeReceivedDCs(receivedDCs);
	}  
}

async function getAuthorPitchMessages(){
	    let url = baseUrl + '/message/getAuthorPitchMessages';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200){
		let authorPMs = await response.json();
		writeAuthorPMs(authorPMs);
	}  
}

async function getEditorPitchMessages(){
	    let url = baseUrl + '/message/getEditorPitchMessages';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200){
		let editorPMs = await response.json();
		writeEditorPMs(editorPMs);
	}  
}

async function getDraftMessages(){
	    let url = baseUrl + '/message/getDraftMessages';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200){
		let draftMessages = await response.json();
		writeDraftMessages(draftMessages);
	}  
}