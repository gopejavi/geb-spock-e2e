package Pages

import Modules.AlertsModule
import Modules.FooterModule
import Modules.ForgotPasswordModule
import Modules.HeaderModule
import geb.Page

class ForgotPasswordPage extends Page {

    static url = "/session/forgotPassword"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Forgot Password?"
    }

    static content = {
        header { module HeaderModule }
        mainPanelTitle { $("h2") }
        alerts { module AlertsModule }
        forgotPasswordForm { module ForgotPasswordModule }
        footer { module FooterModule }
    }

    def sendEmailToRestorePassword(email) {
        forgotPasswordForm.sendEmailToRestorePassword(email)
    }
}