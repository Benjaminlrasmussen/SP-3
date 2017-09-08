var table = document.getElementById("table");
var refreshButton = document.getElementById("refresh_button");
var addButton = document.getElementById("add_button");
var firstnameInput = document.getElementById("firstname_input");
var lastnameInput = document.getElementById("lastname_input");
var phoneInput = document.getElementById("phone_input");

var url = "http://localhost:8080/REST_with_JAX_RS_Persons/api/person";

function fetchDataToTable()
{
    fetch(url, {method: "get"}).then(function (response)
    {
        return response.json();
    }).then(function (json) {
        table.innerHTML = "<tr><th>Firstname</th><th>Lastname</th><th>Phone</th><th>Options</th></tr>";
        for (var i = 0; i < json.length; i++)
        {
            table.innerHTML += "<tr><td>" + json[i].firstName + "</td><td>" + json[i].lastName + "</td><td>" + json[i].phone + "</td>" +
                    "<td><input type='submit' name='button' value='Delete' onclick='deleteUser(" + json[i].id + ")'></input><input type='submit' name='button' value='Edit' onclick='editUser(" + json[i].id + ")'></input></td>" + "</tr>";
        }
    });
}

function addUser()
{
    var headers = new Headers();
    headers.append("Content-Type", "application/json");

    fetch(url, {method: "POST", headers: headers, body: JSON.stringify({"firstName": firstnameInput.value, "lastName": lastnameInput.value, "phone": phoneInput.value})}).then(function (response)
    {
        return response.text();
    }).then(fetchDataToTable).then(clearInputFields);
}

function deleteUser(id)
{
    var headers = new Headers();
    headers.append("Content-Type", "application/json");

    fetch(url, {method: "DELETE", headers: headers, body: JSON.stringify({id: id})}).then(function (response)
    {
        return response.text();
    }).then(fetchDataToTable);
}

function editUser(id)
{
    var headers = new Headers();
    headers.append("Content-Type", "application/json");

    fetch(url, {method: "PUT", headers: headers, body: JSON.stringify({id: id, firstName: firstnameInput.value, lastName: lastnameInput.value, phone: phoneInput.value})}).then(function (response)
    {
        return response.text();
    }).then(fetchDataToTable).then(clearInputFields);
}

function clearInputFields()
{
    firstnameInput.value = "";
    lastnameInput.value = "";
    phoneInput.value = "";
}

fetchDataToTable();
refreshButton.onclick = fetchDataToTable;
addButton.onclick = addUser;

