package Pages

import Modules.FooterModule
import Modules.HeaderModule
import Modules.SignupModule
import geb.Page

class CreateAccountPage extends Page {

    static url = "/session/signup"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Sign Up"
    }

    static content = {
        header { module HeaderModule }
        mainPanelTitle { $("h2") }
        generalErrors { $(".main-container").$(".alert") }
        signupForm { module SignupModule }
        footer { module FooterModule }
    }

    //encapsulation: Page does not care about how to sign up. SignupModule does.
    def signup(def signupData) {
        signupForm.signup(signupData)
    }
}