console.log('hello world');

// grab thet table element from the papge
// so we can mmodifi how it looks and add elements

// let const
let table = document.querySelector('table');
// this saves the table element to the variable


let button = document.getElementById("all-emps");
// WHEN the button is clicked, we make a call
// to the server, fetch the JSON data. And append
// it to the table

button.addEventListener('click', fetchEmps);

function buildTable(data) {

    console.log('buildTable method triggered');
    console.log(data);
    let header = document.createElement('thead')
    let headerRow = document.createElement('tr');
    // append the header to the table
    header.appendChild(headerRow);

    table.appendChild(header);

    // creeat a header column for firstName
    let th1 = document.createElement('th')
    th1.innerHTML = 'First Name';
    // create header column for lastName
    let th2 = document.createElement('th')
    th2.innerHTML = 'Last Name';
    // create a header column for username
    let th3 = document.createElement('th')
    th3.innerHTML = 'Username';
    
    // append the child nodes onto the header
    headerRow.appendChild(th1)
    headerRow.appendChild(th2)
    headerRow.appendChild(th3)
    
    data.forEach(e => {
        
        row = document.createElement('tr');
        th1 = document.createElement('td')
        th2 = document.createElement('td')
        th3 = document.createElement('td')
        
        th1.innerHTML = e.firstName;
        th2.innerHTML = e.lastName;
        th3.innerHTML = e.username;

        row.appendChild(th1);
        row.appendChild(th2);
        row.appendChild(th3);
        table.appendChild(row)
        
    })

}

function fetchEmps() {
    // Fetch API is modern interface that allos you to 
    // make HTTP requests to a server and process the results
    // you get asynchronously
    let hostname = window.location.hostname;    // this will grab the IP of where it's deployed

    fetch(`http://${hostname}:8080/employee-servlet-app-original/employees`)
        .then(response => response.json()) // this takes a json string and transforms 
                                            //  it to a JS object
        .then(buildTable);
}

let user = {
    firstName: 'first',
    lastName: 'last',
    username: 'bob',
    password: 'secretpass'
}

function sayHello () {
    console.log("Hello there!");

}