package Modules

import geb.Module

class LoginModule extends Module {

    static content = {
        form { $("form") }

        emailInput { form.$("#email") }
        passwordInput { form.$("#password") }

        goButton { form.$(".btn", value: "go") }
    }

    def login(def loginData) {
        emailInput = loginData.email
        passwordInput = loginData.password
        goButton.click()
    }
}
