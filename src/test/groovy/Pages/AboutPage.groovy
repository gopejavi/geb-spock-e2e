package Pages

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
        generalErrors { $(".main-container").$(".alert") }
        mainPanel { module TitleWithTextModule }
        footer { module FooterModule }
    }
}