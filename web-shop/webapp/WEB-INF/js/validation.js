var passReg = /^[a-zA-Z]\w{3,14}$/;
var nameReg = /^[a-zA-Z0-9_-]{3,16}$/;
var surnameReg = /^[a-zA-Z]{2,100}$/;

function validateSignUpForm(form) {
    var name = form['name'].value;
    var surname = form['surname'].value;
    var pass = form['pass'].value;

    var results = [validateField('signupName', nameReg.test(name)), validateField('signupPass', passReg.test(pass)),
                        validateField('signupSurname', surnameReg.test(surname))]
    var res = true;
    for (i = 0; i < results.length; i++) {
        res = results[i] && res;
    }
    return res;
}

function validateField(fieldId, rule) {
    if (!rule) {
        document.getElementById(fieldId).style.border = "1px solid #ff0000";
        return false;
    } else {
        document.getElementById(fieldId).style.border = '';
        return true;
    }
}