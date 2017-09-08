var tableBody = document.getElementById("table_body");

function fetchDataToTable()
{
    fetch("http://localhost:8080/exam-preparation_REST_JSON/api/data/50/0", {method: "get"}).then(function(response)
    {
        return response.json();
    }).then(function(json){
        var tableString = "<tr><th>Firstname</th><th>Lastname</th><th>ID</th><th>Age</th></tr>";
        json.forEach(function(element){
            tableString += "<tr><td>" + element.fname + "</td><td>" + element.lname + "</td><td>" + element.id + "</td><td>" + element.age + "</td></tr>";
        });
        tableBody.innerHTML = tableString;
    });
}

fetchDataToTable();