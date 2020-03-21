/* 
////////////////////
URLS ANDD EVENTLISTENERS START
///////////////////
*/
let GetAllPersonsUrl = "http://localhost:8080/CA2/api/person/all" ; 
let GetPersonByIdUrl = "http://localhost:8080/CA2/api/person/id/"; 
let GetAllPersonsByZipCodeUrl = "http://localhost:8080/CA2/api/zipcode/"; 
let GetPersonByPhoneNumberUrl = "https://casperprejler.xyz/CA1/api/groupmembers/members/"; 
let GetPersonByHobbyUrl = "https://casperprejler.xyz/CA1/api/groupmembers/members/"; 
let PostPersonUrl = "https://casperprejler.xyz/CA1/api/groupmembers/members/"; 
let PutPersonUrl = "https://casperprejler.xyz/CA1/api/groupmembers/members/"; 


document.getElementById('getAllPersons').addEventListener("click", getAllPersons);
document.getElementById('getPersonById').addEventListener("click", getPersonById);
document.getElementById('getPersonByZipCode').addEventListener("click", getAllPersonsByZipCode);
document.getElementById('getPersonByPhoneNumber').addEventListener("click", getPersonByPhoneNumber);
/* 
////////////////////
URLS ANDD EVENTLISTENERS END
///////////////////
*/


/* 
////////////////////
GET PERSON BY ID  START 
///////////////////
*/

function getPersonById () {
     
    id = document.getElementById('PersonId').value; 
    fetch(GetPersonByIdUrl+id)
.then(res => res.json()) //in flow1, just do it
.then(data => {
// Inside this callback, and only here, the response data is available
   console.log("data",data); 
   
   TableDiv.innerHTML = personTable(data);

})}


function personTable(person) {
    var tabledataId = "<table id=\"indextable\" class=\"table\">" +
            "<tr><th>Person ID</th>" +
            "<th>Email</th>" +
            "<th>First Name</th>" +
            "<th>Last Name</th></tr>" +
            "<tr><td>" + person.id + "</td><td>" + person.email + "</td><td>" + person.firstName + "</td>" +
            "<td>" + person.lastName + "</tr></table>";
    return tabledataId;

}
/* 
////////////////////
GET PERSON BY ID  END 
///////////////////
*/

/* 
////////////////////
GET ALL PERSONS START
///////////////////
*/

function getAllPersons () {
    fetch(GetAllPersonsUrl)
    .then(res => res.json()) //in flow1, just do it
    .then(data => {
    // Inside this callback, and only here, the response data is available
       console.log("data",data);
       
        TableDiv.innerHTML = AllPersonTable(data); 
    })}
 
    function AllPersonTable(person) {
        let JSONlength = Object.keys(person.all).length;
        let tabledataAll = "<table id=\"indextable\" class=\"table\">" +
        "<tr><th>Person ID</th>" +
        "<th>Email</th>" +
        "<th>First Name</th>" +
        "<th>Last Name</th></tr> "
        let tempdata
        for (let i = 0; i < JSONlength; i++) {
            tempdata = tempdata + "<tr><td>" + person.all[i].id + "</td><td>" + person.all[i].email + "</td><td>" + person.all[i].firstName + "</td>" +"<td>" +person.all[i].lastName + "</td>"      
          } 
tabledataAll = tabledataAll + tempdata
tabledataAll = tabledataAll+"</tr></table>";
return tabledataAll;
    }

/* 
////////////////////
GET ALL PERSONS END
///////////////////
*/

/* 
////////////////////
GET PERSONS BY ZIPCODE START 
///////////////////
*/


    function getAllPersonsByZipCode () {
     
        zipCode = document.getElementById('ZipCode').value; 
        fetch(GetAllPersonsByZipCodeUrl+zipCode)
    .then(res => res.json()) //in flow1, just do it
    .then(data => {
    // Inside this callback, and only here, the response data is available
       console.log("data",data);         
       TableDiv.innerHTML = GetPersonsByZipCodeTable(data);

    })}

    function GetPersonsByZipCodeTable(person) {
        let tempdata
        let JSONlength = Object.keys(person).length;
        let tabledataAll = "<table id=\"indextable\" class=\"table\">" +
        "<tr><th>Person ID</th>" +
        "<th>Email</th>" +
        "<th>First Name</th>" +
        "<th>Last Name</th></tr> "
        for (let i = 0; i < JSONlength; i++) {
            tempdata = tempdata + "<tr><td>" + person[i].id + "</td><td>" + person[i].email + "</td><td>" + person[i].firstName + "</td>" +"<td>" +person[i].lastName + "</td>"      
          } 
         
tabledataAll = tabledataAll + tempdata
tabledataAll = tabledataAll+"</tr></table>";
return tabledataAll;
    }

/* 
////////////////////
GET PERSONS BY ZIPCODE END 
///////////////////
*/



/* 
////////////////////
GET PERSONS BY Phone Number START 
///////////////////
*/


function getPersonByPhoneNumber () {
     
    phoneNumber = document.getElementById('phoneNumber').value; 
    fetch(GetAllPersonsByZipCodeUrl+phoneNumber)
.then(res => res.json()) //in flow1, just do it
.then(data => {
// Inside this callback, and only here, the response data is available
   console.log("data",data);         
   TableDiv.innerHTML = GetPersonsByPhoneNumberTable(data);

})}

function GetPersonsByPhoneNumberTable(person) {
    let tempdata
    let JSONlength = Object.keys(person).length;
    let tabledataAll = "<table id=\"indextable\" class=\"table\">" +
    "<tr><th>Person ID</th>" +
    "<th>Email</th>" +
    "<th>First Name</th>" +
    "<th>Last Name</th></tr> "
    for (let i = 0; i < JSONlength; i++) {
        tempdata = tempdata + "<tr><td>" + person[i].id + "</td><td>" + person[i].email + "</td><td>" + person[i].firstName + "</td>" +"<td>" +person[i].lastName + "</td>"      
      } 
     
tabledataAll = tabledataAll + tempdata
tabledataAll = tabledataAll+"</tr></table>";
return tabledataAll;
}

/* 
////////////////////
GET PERSONS BY Phone Number END 
///////////////////
*/

