package Modules

import geb.Module

class ChangePasswordModule extends Module {

    static content = {
        form { $("form") }

        passwordInput { form.$("#password") }
        confirmPasswordInput { form.$("#confirmPassword") }

        changePasswordButton { form.$(".btn", value: "Change Password") }
    }

    def changePassword(def newPasswordData) {
        passwordInput = newPasswordData.password
        confirmPasswordInput = newPasswordData.confirmPassword
        changePasswordButton.click()
    }
}
