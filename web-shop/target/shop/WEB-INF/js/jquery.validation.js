var passReg = /^[a-zA-Z]\w{3,14}$/;
var emailReg = /^.+@.+\..+/;


$('#loginForm').submit(function (event) {
    var loginEmail = $(this).find("input[id=loginEmail]");
    var loginPass = $(this).find("input[id=loginPass]");

    var emailValRes = !validateFormField(loginEmail, emailReg.test(loginEmail.val()));
    var passValRes = !validateFormField(loginPass, passReg.test(loginPass.val()));

    if (emailValRes || passValRes) {
        event.preventDefault();
    }
});

function validateFormField(field, rule) {
    if(!rule) {
        field.css("border", "1px solid red");
        return false;
    }
    else {
        field.css("border", "");
        return true;
    }
}


