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
        signupForm { module SignupModule }
        footer { module FooterModule }
    }

    def fillFormWithData(def signupData) {
        signupForm.fillFormWithData signupData
    }
}