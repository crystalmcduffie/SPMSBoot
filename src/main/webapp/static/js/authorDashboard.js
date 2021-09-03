

let authorSection = document.getElementById('write_message');
let pitchSection = document.getElementById('write_user');

authorSection.innerHTML = "Please let something work";
pitchSection.innerHTML = <h3>${loggedAuthor.username}</h3>;

/* async function checkLogin() {
    let url = baseUrl + '/author';
    let response = await fetch(url);
    if (response.status === 200) loggedAuthor = await response.json();
    alert("Login Checked");
}

function writeUser() {
    alert("Write_User");
   // let pitches = loggedAuthor.pitches;
    //let authorSection = document.getElementById('write_message');
    authorSection.innerHTML = <h3>${loggedAuthor.username}</h3>;
} */
    /* let pitchSection = document.getElementById('write_user');

    if (pitches.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
            </tr>
        `;

        for (let pitch of pitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
            `;
            table.appendChild(tr);
        }

        pitchSection.appendChild(table);
    } else {
        pitchSection.innerHTML = 'You don\'t have any pitches. :(';
    } */
}
/* 
let pitchCount = 7;
var pitchTable = document.getElementById('pitch_table');
let tr;
let td1;
let td2;
let td3;
let td4;
let td5; */
    
 


/* let dataSection = document.getElementById('write_user');
dataSection.innerHTML = <h1>${loggedAuthor.username}&nbsp;</h1> */

/*  //Creating a new XMLHttpRequest object
 let xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = receiveData;

//Create a asynchronous GET request
xhttp.open("GET", "/getsession/author");

xhttp.send();

//When readyState is 4 then get the server output
function receiveData() {
    if (xhttp.readyState == 4) { 
        if (xhttp.status == 200) 
        {
            document.getElementById("write_message").innerHTML = "Here is our user:";
            let response = xhttp.responseText;
          	 response = JSON.parse(response);
          	 dataSection.innerHTML = "This is their id: " + response.id + "<br> and this is their name: " + response.username;
           // populateData(response);
            alert(xmlhttp.responseText);
        } 
        else
        {
            //alert('Something is wrong !!');
        }
    }
};



function populateData(response) {
    dataSection.innerHTML = response;

}   */



/*while(pitchCount>0){
    tr = document.createElement('tr');
    td1 = document.createElement('td');
    td2 = document.createElement('td');
    td3 = document.createElement('td');
    td4 = document.createElement('td');
    td5 = document.createElement('td');
    td1.innerHTML = "I am a pitch title";
    tr.appendChild(td1);
    td2.innerHTML = "I hold details for a pitch";
    tr.appendChild(td2);
    td3.innerHTML = "I specify the pitch's stage";
    tr.appendChild(td3);
    td4.innerHTML = "I specify a review request";
    tr.appendChild(td4);
    //this button will need to call a servlet
    td5.innerHTML = "<button type = \"button\">Check messages</button>";
    tr.appendChild(td5);
    pitchTable.appendChild(tr);
    pitchCount--;
}
  
let draftCount = 4;
var draftTable = document.getElementById('draft_table');
let td6;
let td7; 

 while(draftCount>0){
    tr = document.createElement('tr');
    td1 = document.createElement('td');
    td2 = document.createElement('td');
    td3 = document.createElement('td');
    td4 = document.createElement('td');
    td5 = document.createElement('td');
    td6 = document.createElement('td');
    td7 = document.createElement('td');
    td1.innerHTML = "I am a draft title";
    tr.appendChild(td1);
    //this button will need to call a servlet
    td2.innerHTML = "<button type = \"button\">View draft</button>";
    tr.appendChild(td2);
    td3.innerHTML = "I specify the pitch";
    tr.appendChild(td3);
    td4.innerHTML = "I specify a draft's stage";
    tr.appendChild(td4);
    td5.innerHTML = "I list the requirments for a draft to get approved";
    tr.appendChild(td5);
    td6.innerHTML = "I list any change requests";
    tr.appendChild(td6);
    //this button will need to call a servlet
    td7.innerHTML = "<button type = \"button\">Check messages</button>";
    tr.appendChild(td7);
    draftTable.appendChild(tr);
    draftCount--;
} */