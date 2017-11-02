package Pages

import Modules.AlertsModule
import Modules.FooterModule
import Modules.HeaderModule
import Modules.TitleWithTextModule
import geb.Page

class TermsPage extends Page {

    static url = "/terms"

    static at = {
        title == "Welcome to Vökuró"
        mainPanel.title.text() == "Terms"
    }

    static content = {
        header { module HeaderModule }
        alerts { module AlertsModule }
        mainPanel { module TitleWithTextModule }
        footer { module FooterModule }
    }
}