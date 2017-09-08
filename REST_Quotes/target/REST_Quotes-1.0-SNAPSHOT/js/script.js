var quoteDiv = document.getElementById("quote_div");
var newQuoteButton = document.getElementById("new_quote_button");
var quoteTableBody = document.getElementById("quote_table_body");

function fetchQuoteOfTheDay()
{
    fetch("http://localhost:8080/REST_Quotes/api/quote/random", {method: "get"}).then(function(response)
    {
        return response.json();
    }).then(function(json)
    {
        quoteDiv.innerText = json.quote;
    });
}

function fetchAllQuotesToTable()
{
    fetch("http://localhost:8080/REST_Quotes/api/quote", {method: "get"}).then(function(response)
    {
        return response.json();
    }).then(function(json)
    {
        var tableString = "<tr><th>Quote</th><th>Options</th></tr>";
        json.forEach(function(element){
            tableString += "<tr><td>" + element.quote + "</td><td><input type='submit' value='Delete' onclick=\"deleteUser(" + element.id + ", " + element.quote + ")\" /></td></tr>";
        });
        quoteTableBody.innerHTML = tableString;
    });
}

function deleteQuote(id, quote)
{
    var headers = new Headers();
    headers.append("Content-Type", "application/json");

    fetch("http://localhost:8080/REST_Quotes/api/quote", {method: "delete", header: headers, body: JSON.stringify({id: id, quote: quote})}).then(function(response)
    {
        return response.text();
    });
}

function deleteUser(id, quote)
{
    var headers = new Headers();
    headers.append("Content-Type", "application/json");

    fetch(url, {method: "DELETE", headers: headers, body: JSON.stringify({id: id, quote: quote})}).then(function (response)
    {
        return response.text();
    }).then(fetchAllQuotesToTable);
}

function editQuote(id, quote)
{
    fetchAllQuotesToTable();
}

newQuoteButton.onclick = fetchQuoteOfTheDay;
fetchQuoteOfTheDay();
fetchAllQuotesToTable();