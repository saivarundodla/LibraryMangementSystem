requestedBooksList = []

$(document).ready(function () {
    console.log("Librarian Home page ready!");
    getUserDetails();
    getAvailableBooks();   
    $("#saveuser").hide()
    $("#canceluser").hide()
});



function booksCreation(bookslist) {
    $("table.bookslist tbody").find("tr").remove()
    for (book in bookslist) {
        console.log(bookslist[book]);
        var bookhtml = '<tr>' +
            '<th scope="row">' +
            '<form>' +
            '<div class="form-check">' +
            '<input type="checkbox" onclick="addRequestBook(event)" class="form-check-input" id=book' + bookslist[book].bookId + '>' +
            '</div>' +
            '</form>' +
            '</th>' +
            '<td>' +
            '<p>' + bookslist[book].bookName + '</p>' +
            '</td>' +
            '<td>' +
            '<p>' + bookslist[book].author + '</p>' +
            '</td>' +
            '<td>' +
            '<p>' + bookslist[book].publication + '</p>' +
            '</td>' +
            '</tr>'
        console.log(bookhtml)
        $("table.bookslist tbody").append(bookhtml)
    }
}

function getUserDetails() {
    var userid = localStorage.getItem("userid");
    var url = "http://localhost:8080/user/userDetails/lib/" + parseInt(localStorage.getItem("userid"))
    console.log(url)
    console.log(userid)
    $.ajax({
        type: "get",
        url: url,
        success: function (data) {
            console.log("user data " + data)
            userDetails = data.split(",")
            userDetailsDict = {
                "userid": userDetails[0],
                "name": userDetails[1],
                "email": userDetails[2],
                "contact": userDetails[3],
                "type": userDetails[4],
                "taken": userDetails[5],
                "approved": userDetails[6],                
            }
            $(".usereditfield").remove()              
            $("#user p").append('<input disabled type="text" id="name" class="usereditfield" value = '+userDetails[1]+'></input>');            
            $("#user div.userdetails tr").find("td").each(function (index) {
                if (index + 2 <= 3) {
                    $(this).append('<input type="text" class="usereditfield" value = ' + userDetails[index + 2] + ' disabled></input>');
                }
                else {
                    $(this).text(userDetails[index + 2])
                }
            }
            )
            $("#name").val(userDetails[1])
        }
    })
}

function getAvailableBooks() {
    var url = "http://localhost:8080/book/available"
    console.log(url)
    $.ajax({
        type: "get",
        url: url,
        success: function (data) {
            console.log("available books " + JSON.stringify(data))
            booksCreation(data)
        }

    })
}



function search(event) {
    console.log("search box clicked");
    console.log(event.type);
    console.log($("#search").val());
    var url = "http://localhost:8080/book/search/" + $("#search").val()
    $.ajax({
        type: "get",
        url: url,
        success: function (data) {
            console.log("search data " + JSON.stringify(data))
            booksCreation(data)
        }
    })
}


function addRequestBook(event) {
    console.log("request book")
    console.log(event.target.id)
    console.log($("#" + event.target.id).prop("checked"))
    var value = (event.target.id)
    value = value.slice(4, value.length)

    if ($("#" + event.target.id).prop("checked") == true) {
        console.log("checked")
        requestedBooksList.push(value)
    }
    else {
        console.log("unchecked")
        requestedBooksList.splice(requestedBooksList.indexOf(value), 1)
    }

    if (requestedBooksList.length == 0) {
        $("#request").prop("disabled", true)
    }
    else {
        $("#request").prop("disabled", false)
    }

}


function requestBook() {
    console.log("request book function");
    var url = "http://localhost:8080/checkin/request"
    var bookslist = {
        "userId": [parseInt(localStorage.getItem("userid"))],
        "bookslist": requestedBooksList
    }
    $.ajax({
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(bookslist),
        dataType: "json",
        url: url,
        success: function (data) {
            console.log("request book " + JSON.stringify(data));
        },
        complete: function (data) {
            getAvailableBooks();
        }
    })
}


function requestApproval() {
    console.log("Request Approval Books");
    window.location.href = filepath + "/requestapproval/requestapproval.html";
}

function editUserDetails() {
    $("#saveuser").show()
    $("#canceluser").show()
    $(".usereditfield").prop("disabled", false) 
    console.log("Edit User Details");
}

function saveUserDetails() {
    $("#saveuser").hide()
    $("#canceluser").hide()
    $(".usereditfield").prop("disabled", true)

    var userDetails = {
        "userId":localStorage.getItem("userid"),
        "name": $(".usereditfield:eq(0)").val(),
        "email": $(".usereditfield:eq(1)").val(),
        "contact": $(".usereditfield:eq(2)").val()

    } 
    var url = "http://localhost:8080/user/userDetails"
    $.ajax({
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(userDetails),
        dataType: "json",
        url: url,
        success: function (data) {
            console.log("User Details updated Successfully " + JSON.stringify(data));
        }
    })

    console.log("Save User Details"+JSON.stringify(userDetails));
}

function cancelUserDetails() {
    $("#saveuser").hide()
    $("#canceluser").hide()
    $(".usereditfield").prop("disabled", true)
    getUserDetails() 
    console.log("Cancel User Details");
}