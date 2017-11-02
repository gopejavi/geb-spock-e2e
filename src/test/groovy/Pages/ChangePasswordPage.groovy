package Pages

import Modules.AlertsModule
import Modules.ChangePasswordModule
import Modules.FooterModule
import Modules.HeaderLoggedModule
import geb.Page

class ChangePasswordPage extends Page {

    static url = "/users/changePassword"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Change Password"
    }

    static content = {
        headerLogged { module HeaderLoggedModule }
        mainPanelTitle { $("h2") }
        alerts { module AlertsModule }
        changePasswordForm { module ChangePasswordModule }
        footer { module FooterModule }
    }

    //encapsulation: Page does not care about how to sign up. SignupModule does.
    def changePassword(def newPasswordData) {
        changePasswordForm.changePassword(newPasswordData)
    }
}