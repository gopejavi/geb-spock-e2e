package Pages

import Modules.FooterModule
import Modules.HeaderLoggedModule
import geb.Page

class UsersPage extends Page {

    static url = "/users"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Search users"
    }

    static content = {
        headerLogged { module HeaderLoggedModule }
        generalErrors { $(".main-container").$(".alert") }
        mainPanelTitle { $("h2") }
        footer { module FooterModule }
    }
}