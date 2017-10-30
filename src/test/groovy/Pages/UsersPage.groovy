package Pages

import Modules.FooterModule
import Modules.HeaderModule
import geb.Page

class UsersPage extends Page {

    static url = "/users"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Search users"
    }

    static content = {
        header { module HeaderModule }
        mainPanelTitle { $("h2") }
        generalErrors { $(".main-container").$(".alert") }

        footer { module FooterModule }
    }
}