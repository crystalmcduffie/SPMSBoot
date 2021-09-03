let baseUrl = '/SPMS';
let resubmitPitch = document.getElementById('resubmitPitch');
let authorDashboard = document.getElementById('authorDashboard');
let form = document.getElementById('form');
let points = document.getElementById('points');
let log = document.getElementById('log');
loggedAuthor = null;
reply = null;
checkLogin();
toResubmitPitch();
toDashboard();

//logout();
writeForm();
//writeForm2();
//writePoints();

function toResubmitPitch(){
	resubmitPitch.innerHTML = `
	<h3>Click here to resubmit a pitch that was placed on hold</h3>
	<a href="resubmitPitch.html">Resubmit Pitch</a>
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



function writeForm(){
	
	form.innerHTML =  `
	<form>
	<label for = "author_info">Author Info:</label>
	<input type = "text" id = "author_info" name = "author_info" required>
	<br>
	<label for = "story_title">Story Title:</label>
	<input type = "text" id = "story_title" name = "story_title" required>
	<br>
	<label for = "completion_date">Tentative Completion Date:</label>
	<input type = "text" id ="completion_date" name = "completion_date" required>
	<br>
	<label for = "story_type">Story Type:</label>
	<select name = "story_type" id = "story_type" required>
		<option value = "novel">Novel (50 points)</option>
		<option value = "novella">Novella (25 points)</option>
		<option value = "short_story">Short Story (20 points)</option>
		<option value = "article">Article (10 points)</option>
	</select>
	<br>
	<label for = "genre">Genre:</label>
	<select name = "genre" id = "genre" required>
		<option value = "romance">Romance</option>
		<option value = "horror">Horror</option>
		<option value = "sci-fi">Sci-Fi</option>
		<option value = "adventure">Adventure</option>
		<option value = "nonfiction">Nonfiction</option>
	</select>
	<br>
	<label for = "tag_line">Tag Line:</label>
	<input type = "text" id = "tag_line" name = "tag_line" required>
	<br>
	<label for = "description">Detailed Description:</label>
	<input type = "text" id = "description" name = "description" required>
	<br>
	<label for = "attachments">Attachments:</label>
	<input type = "form" id = "attachments" name = "attachments">
	<br>
	<button type="button" onclick="createPitch()" id="submitForm" >Submit</button>
	`;
	
	let submitFormBtn = document.getElementById('submitForm');
	submitFormBtn.onclick = createPitch;
}



function writePoints(){
	
	points.innerHTML = `
		<h3>You have ${loggedAuthor.points} points </h3>
	`;
}

function writeLog(reply){
	log.innerHTML = `<h1>${reply}</h1>`;

}
async function createPitch(){
	
	let url = baseUrl + '/pitch?';
	url += 'authorInfo=' + document.getElementById('author_info').value + '&';
    url += 'storyTitle=' + document.getElementById('story_title').value + '&';
    url += 'completionDate=' + document.getElementById('completion_date').value + '&';
    url += 'storyType=' + document.getElementById('story_type').value + '&';
    url += 'genre=' + document.getElementById('genre').value + '&';
    url += 'tagLine=' + document.getElementById('tag_line').value + '&';
	url += 'description=' + document.getElementById('description').value + '&';
    url += 'attachments=' + document.getElementById('attachments').value;
    let response = await fetch(url, {method:'POST'});
    if (response.status === 201) {
        alert('Added pitch successfully.');
		reply = await response.json();
		alert(reply);
    } else {
        alert('Something went wrong.');
    }
	checkLogin();
	//writePoints();
	
	writeForm();
	
	
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










