package Modules

import geb.Module

class ForgotPasswordModule extends Module {

    static content = {
        form { $("form") }
        emailInput { form.$("#email") }
        sendButton { form.$(".btn", value: "Send") }
    }

    def sendEmailToRestorePassword(email) {
        emailInput = email
        sendButton.click()
    }
}
