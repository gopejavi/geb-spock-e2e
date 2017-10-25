package Modules

import geb.Module

class SignupModule extends Module {

    static content = {
        form { $(".signup") }
        nameInput { form.$("#name") }
        emailInput { form.$("#email") }
        passwordInput { form.$("#password") }
        confirmPasswordInput { form.$("#confirmPassword") }
        termsCheckbox { form.$("#terms") }
        submitButton { form.$("input", type: "submit") }
    }

    def fillFormWithData(def signupData) {
        nameInput = signupData.name
        emailInput = signupData.email
        passwordInput = signupData.password
        confirmPasswordInput = signupData.confirmPassword
    }
}
