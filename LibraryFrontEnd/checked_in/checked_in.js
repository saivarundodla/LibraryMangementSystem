returnBooksList = []

$(document).ready(function () {
    console.log("Checked In page ready!");  
    getCheckedInBooks();
    $("#request").prop("disabled", true)  
});

function availableBooks(){
    console.log("Available In Books");
    window.location.href = filepath+"/home/studenthome.html";
}

function booksCreation(checkinlist) {
    $("table.bookslist tbody").find("tr").remove()
    for (checkin in checkinlist) {
        console.log(checkinlist[checkin]);
        var returned_date = checkinlist[checkin].returnedDate;
        if (returned_date!=null){
            returned_date = checkinlist[checkin].returnedDate.slice(0, 10);
        }
        var bookhtml = '<tr>' +
            '<th scope="row">' +
            '<form>' +
            '<div class="form-check">' +
            '<input type="checkbox" onclick="addReturnBook(event)" class="form-check-input" id='+checkinlist[checkin].checkInId + '>' +
            '</div>' +
            '</form>' +
            '</th>' +
            '<td>' +
            '<p>' + checkinlist[checkin].book.bookName + '</p>' +
            '</td>' +
            '<td>' +
            '<p>' + checkinlist[checkin].book.author + '</p>' +
            '</td>' +
            '<td>' +
            '<p>' + checkinlist[checkin].book.publication + '</p>' +
            '</td>' +
            '<td>' +
            '<p>' + checkinlist[checkin].checkInDate.slice(0, 10) + '</p>' +
            '</td>' + 
            '<td>' +
            '<p>' + checkinlist[checkin].checkOutDate.slice(0, 10) + '</p>' +
            '</td>' + 
            '<td>' +
            '<p>' + checkinlist[checkin].penalty + '</p>' +
            '</td>' + 
            '<td>' +
            '<p>' + checkinlist[checkin].requestApproval + '</p>' +
            '</td>' +     
            '<td>' +
            '<p>' + returned_date + '</p>' +
            '</td>' +        
            '</tr>'
        console.log(bookhtml)
        $("table.bookslist tbody").append(bookhtml)
    }
}

function getCheckedInBooks(){
    var url = "http://localhost:8080/checkin/all/"+parseInt(localStorage.getItem("userid"))
    console.log(url)
    $.ajax({
        type: "get",
        url: url,
        success: function (data) {
            console.log("Checked In books " + JSON.stringify(data))
            booksCreation(data)
        }

    })
}

function addReturnBook(event) {
    console.log("return book")
    console.log(event.target.id)
    console.log($("#" + event.target.id).prop("checked"))
    var value = (event.target.id)   

    if ($("#" + event.target.id).prop("checked") == true) {
        console.log("checked")
        returnBooksList.push(parseInt(value))
    }
    else{
        console.log("unchecked")
        returnBooksList.splice(returnBooksList.indexOf(parseInt(value)), 1)
    }

    if (returnBooksList.length==0){
        $("#return").prop("disabled", true)
    }
    else{
        $("#return").prop("disabled", false)
    }

}

function returnBooks(){
    console.log("return book function");
    var url = "http://localhost:8080/checkin/return"

    var returnbooksdict = {   
        "userId":[parseInt(localStorage.getItem("userid"))],     
        "returnbooksList":returnBooksList
    }
    console.log(JSON.stringify(returnbooksdict))
    $.ajax({
        type: "post",
        contentType : "application/json",        
        data: JSON.stringify(returnbooksdict),
        dataType : "json",
        url: url,
        success: function (data) {
            console.log("request book " + JSON.stringify(data));                         
        },
        complete: function(data){
            getCheckedInBooks();
        }
    })
}