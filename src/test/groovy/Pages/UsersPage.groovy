package Pages

import Modules.AlertsModule
import Modules.HeaderLoggedModule
import Modules.UsersSearchFormModule
import geb.Page

class UsersPage extends Page {

    static url = "/users"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Search users"
    }

    static content = {
        headerLogged { module HeaderLoggedModule }
        alerts { module AlertsModule }
        createUserButton { $(".btn", href: "/users/create") }
        mainPanelTitle { $("h2") }
        searchForm { module UsersSearchFormModule }
    }

    def search(userSearchData) {
        searchForm.search(userSearchData)
    }
}