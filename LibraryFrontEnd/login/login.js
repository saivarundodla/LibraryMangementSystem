$(document).ready(function () {
    console.log("ready!");
    $("#login").submit(function (event) {
        event.preventDefault();
        console.log(JSON.stringify(event))
        checkCredentials();
    })
});

function checkCredentials() {
    console.log("Submitted");
    console.log(JSON.stringify($("#login")));
    console.log($("#loginid").val())
    console.log($("#password").val())

    data = {
        "loginId": $("#loginid").val(),
        "password": $("#password").val(),
    }

    console.log(data)
    console.log(JSON.stringify(data))

    $.ajax({
        type: "post",
        contentType: "application/json",
        url: "http://localhost:8080/user/login",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (data) {
            $('#invaliderror').css("visibility", "hidden")
            $(".errorborder").css({ "border-style": "solid", "border-width": "2px", "border-color": "green" })
            console.log("Login connection successful");
            console.log(JSON.stringify(data));
            console.log(window.location);
            localStorage.setItem("userid", data.userId);
            if (data.type == "student") {
                console.log(filepath + "/home/studenthome.html");
                window.location.href = filepath + "/home/studenthome.html";
            }
            else {
                console.log(filepath + "/home/libraryhome.html");
                window.location.href = filepath + "/home/libraryhome.html";
            }
        },
        error: function (data) {
            console.log("Invalid Login Credentials");
            // $("#invaliderror").show()
            $('#invaliderror').css("visibility", "visible")
            $(".errorborder").css({ "border-style": "solid", "border-width": "2px", "border-color": "red" })
        }
    })

    return false;
}

