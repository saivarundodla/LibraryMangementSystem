$(document).ready(function () {
    console.log("ready!");
    $("#register").submit(function (event) {
        event.preventDefault();
        console.log(JSON.stringify(event))
        registerUser();
    })
});

function registerUser() {
    console.log("Submitted");
    console.log(JSON.stringify($("#register")));
    console.log($("#loginid").val())
    console.log($("#password").val())

    data = {
        "loginId": $("#loginid").val(),
        "password": $("#password").val(),
        "name": $("#name").val(),
        "email": $("#email").val(),
        "contact": $("#contact").val(),
        "type": "student"
    }

    console.log(data)
    console.log(JSON.stringify(data))

    $.ajax({
        type: "post",
        contentType: ["application/json; charset=utf-8", "text/plain;charset=UTF-8"],
        url: "http://localhost:8080/user/register",
        data: JSON.stringify(data),
        success: function (data) {
            if (data == "User Exists") {
                $('#invaliderror').text("Existing User..");
                $('#invaliderror').css("visibility", "visible");
                $(".errorborder").css({ "border-style": "solid", "border-width": "2px", "border-color": "red" });
            }
            else {                
                $('#invaliderror').css("visibility", "hidden")
                $(".errorborder").css({ "border-style": "solid", "border-width": "2px", "border-color": "green" })                               
                window.location.href = filepath+"/login/login.html";
            }
            console.log("Register connection successful");
            console.log(JSON.stringify(data));
        },
        error: function (text, error) {
            console.log("Invalid Entries");
            console.log(JSON.stringify(text));
            console.log(JSON.stringify(error));
            if (text["responseText"] == "User Exists") {
                $('#invaliderror').text("Existing User..");
            }
            else {
                $('#invaliderror').text("Invalid Entries..");
            }
            $('#invaliderror').css("visibility", "visible");
            $(".errorborder").css({ "border-style": "solid", "border-width": "2px", "border-color": "red" });
        }
    })

    return false;
}

