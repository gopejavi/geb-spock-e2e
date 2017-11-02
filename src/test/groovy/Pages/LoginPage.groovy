package Pages

import Modules.AlertsModule
import Modules.FooterModule
import Modules.HeaderModule
import Modules.LoginModule
import geb.Page

class LoginPage extends Page {

    static url = "/session/login"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Log In"
    }

    static content = {
        header { module HeaderModule }
        mainPanelTitle { $("h2") }
        alerts { module AlertsModule }
        loginForm { module LoginModule }
        footer { module FooterModule }
    }

    def login(loginData) {
        loginForm.login(loginData)
    }
}