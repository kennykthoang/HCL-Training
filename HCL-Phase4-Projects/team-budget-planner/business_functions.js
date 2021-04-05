
var myData = [
    {"dealId" : 0, "client_name" : "Microsoft", "project_name" : "Apollo Project", "project_manager" : "Mary", "project_cost" : 1000},
    {"dealId" : 1, "client_name" : "Intel", "project_name" : "Hermes Project", "project_manager" : "Bob", "project_cost" : 10000},
    {"dealId" : 2, "client_name" : "Apple", "project_name" : "Zeus Project", "project_manager" : "Jane", "project_cost" : 100000}
]


var currentDealId = myData.length;


// localstorage allows us to persist key value pairs in a way that would survive page refreshes, navigation, and user closing/reopening browser.
// localstorage has limits to the size of each object stored.   

localStorage.setItem("myData", "test")

var myDataTest = localStorage.getItem("myData")

function createTableFromJSON() {    
    
    // EXTRACT VALUE FOR HTML HEADER. 
    // ('Deal ID', 'Deal Name', 'Category' and 'Price')
    var col = [];
    for (var i = 0; i < myData.length; i++) {
        for (var key in myData[i]) {
            if (col.indexOf(key) === -1) {
                col.push(key);
            }
        }
    }

    // CREATE DYNAMIC TABLE.
    var table = document.createElement("table");

    // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

    var tr = table.insertRow(-1);                   // TABLE ROW.

    for (var i = 0; i < col.length; i++) {
        var th = document.createElement("th");      // TABLE HEADER.
        th.innerHTML = col[i];
        tr.appendChild(th);
    }

    // ADD JSON DATA TO THE TABLE AS ROWS.
    for (var i = 0; i < myData.length; i++) {

        tr = table.insertRow(-1);

        for (var j = 0; j < col.length; j++) {
            var tabCell = tr.insertCell(-1);
            tabCell.innerHTML = myData[i][col[j]];
        }
        // Insert Extra Cell for the Delete Icon
        //TODO: Complete this
        //var tabCell = tr.insertCell(-1);
        //tabCell.innerHTML = '<button onclick="DeleteRow(' + myData[i].dealId + ')"> <img src="trashcan.png"> </button>'
    }

    // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
    var divContainer = document.getElementById("showData");
    divContainer.innerHTML = "";
    divContainer.appendChild(table);
}

function addNewDeal() {
    var clientName = document.getElementById("clientNameInput").value;
    var projectName = document.getElementById("projectNameInput").value;
    var projectManager = document.getElementById("projectManagerInput").value;
    var projectCost = document.getElementById("projectCostInput").value;


    document.getElementById("clientNameInput").value = "";
    document.getElementById("projectNameInput").value = "";
    document.getElementById("projectManagerInput").value = "";
    document.getElementById("projectCostInput").value = "";


    insertRow(currentDealId, clientName, projectName, projectManager, projectCost);
}

function insertRow(dealId, clientName, projectName, projectManager, projectCost) {
    myData.push({"dealId": dealId, "client_name" : clientName, "project_name" : projectName, "project_manager" : projectManager, "project_cost" : projectCost})
    currentDealId++;
    createTableFromJSON();
}

function deleteRow(dealId) {
     
    for( var i = 0; i < myData.length; i++){ 
    
        if (myData[i].dealId === dealId) { 
    
            myData.splice(i, 1); 
        }
    }
    createTableFromJSON();
}

function editRow(dealId, newValue, fieldNumber)
{
    for(var i = 0; i < myData.length; i++)
    {
        if(myData[i].dealId === dealId)
        {
            switch(fieldNumber)
            {
                case 0:
                    myData[i].client_name = newValue
                    break;
                case 1:
                    myData[i].project_name = newValue
                    break;
                case 2:
                    myData[i].project_manager = newValue
                    break;
                case 3:
                    myData[i].project_cost = newValue;
                    break;
            }
        }
    }

    createTableFromJSON();
}

function showDealAdd()
{
    document.getElementById("addDealField").style.display = "block";
}

function showDealEdit()
{
    document.getElementById("editDealField").style.display = "block";
}

function editDeal()
{
    var dealId = parseInt(document.getElementById("clientIdInputEdit").value);
    var clientName = document.getElementById("clientNameInputEdit").value;
    var projectName = document.getElementById("projectNameInputEdit").value;
    var managerName = document.getElementById("projectManagerInputEdit").value;

    editRow(dealId, clientName, 0);
    editRow(dealId, projectName, 1);
    editRow(dealId, managerName, 2);
}

function showDealDelete()
{
    document.getElementById("deleteDealField").style.display = "block";
}

function showVendorDelete()
{
    document.getElementById("deleteVendorField").style.display = "block";
}

function deleteVendor()
{
    var vendorName = document.getElementById("clientNameInputDelete").value;
    for(var i = 0; i < myData.length; i++)
    {
        if(myData[i].client_name === vendorName)
        {
            deleteRow(i);
        }
    }
}

function showBudgetEdit()
{
    document.getElementById("editField").style.display = "block";
}

function editBudget()
{
    var dealId = parseInt(document.getElementById("clientIdInput").value);
    var newProjectCost = parseInt(document.getElementById("projectCostInput").value);

    editRow(dealId, newProjectCost, 3);
}
