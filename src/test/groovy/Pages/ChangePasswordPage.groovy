package Pages

import Modules.ChangePasswordModule
import Modules.FooterModule
import Modules.HeaderLoggedModule
import geb.Page

class ChangePasswordPage extends Page {

    static url = "/session/changePassword"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Change Password"
    }

    static content = {
        headerLogged { module HeaderLoggedModule }
        mainPanelTitle { $("h2") }

        alertsAtTop { $(".main-container").$(".alert") }
        successAlert { alertsAtTop.filter(".alert-success") }
        errorAlert { alertsAtTop.filter(".alert-danger") }

        changePasswordForm { module ChangePasswordModule }

        footer { module FooterModule }
    }

    //encapsulation: Page does not care about how to sign up. SignupModule does.
    def changePassword(def newPasswordData) {
        changePasswordForm.changePassword(newPasswordData)
    }
}