package Pages

import Modules.FooterModule
import Modules.HeaderModule
import Modules.TitleWithTextModule
import geb.Page

class CreateAccountPage extends Page {

    static url = "/session/signup"

    static at = {
        title == "Welcome to Vökuró"
        mainPanel.title.text() == "Sign Up"
    }

    static content = {
        header { module HeaderModule }
        mainPanel { module TitleWithTextModule }
        footer { module FooterModule }
    }
}