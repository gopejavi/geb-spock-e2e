package Pages

import Modules.AlertsModule
import Modules.FooterModule
import Modules.HeaderModule
import Modules.TitleWithTextModule
import geb.Page

class AboutPage extends Page {

    static url = "/about"

    static at = {
        title == "Welcome to Vökuró"
        mainPanel.title.text() == "About this Demo"
    }

    static content = {
        header { module HeaderModule }
        alerts { module AlertsModule }
        mainPanel { module TitleWithTextModule }
        footer { module FooterModule }
    }
}