package Pages

import Modules.AlertsModule
import Modules.FooterModule
import Modules.HeaderModule
import Modules.TitleWithTextModule
import geb.Page

class PrivacyPolicyPage extends Page {

    static url = "/privacy"

    static at = {
        title == "Welcome to Vökuró"
        mainPanel.title.text() == "Privacy"
    }

    static content = {
        header { module HeaderModule }
        alerts { module AlertsModule }
        mainPanel { module TitleWithTextModule }
        footer { module FooterModule }
    }
}