package Pages

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
        generalErrors { $(".main-container").$(".alert") }
        mainPanel { module TitleWithTextModule }
        footer { module FooterModule }
    }
}