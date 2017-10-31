package Modules

import geb.Module

class SignupModule extends Module {

    static content = {
        form { $(".signup") }

        nameInput { form.$("#name") }
        nameInputErrors { nameInput.next(".alert-danger") }

        emailInput { form.$("#email") }
        emailInputErrors { emailInput.next(".alert-danger") }

        passwordInput { form.$("#password") }
        passwordInputErrors { passwordInput.next(".alert-danger") }

        confirmPasswordInput { form.$("#confirmPassword") }
        confirmPasswordInputErrors { confirmPasswordInput.next(".alert-danger") }

        termsCheckbox { form.$("#terms") }
        termsCheckboxErrors { termsCheckbox.next(".alert-danger") }

        submitButton { form.$("input", type: "submit") }
    }

    def signup(def signupData) {
        nameInput = signupData.name
        emailInput = signupData.email
        passwordInput = signupData.password
        confirmPasswordInput = signupData.confirmPassword
        if (signupData.agreeTermsConditions) {
            termsCheckbox.click()
        }
        submitButton.click()
    }
}
