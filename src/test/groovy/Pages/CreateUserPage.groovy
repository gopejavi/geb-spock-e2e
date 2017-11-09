package Pages

import Modules.AlertsModule
import Modules.FooterModule
import Modules.HeaderLoggedModule
import Modules.UsersCreateFormModule
import geb.Page

class CreateUserPage extends Page {

    static url = "/users/create"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Create a User"
    }

    static content = {
        headerLogged { module HeaderLoggedModule }
        alerts { module AlertsModule }
        previousPage { $(".pager .previous") }
        mainPanelTitle { $("h2") }
        createForm { module UsersCreateFormModule }
        footer { module FooterModule }
    }

    def search(userSearchData) {
        searchForm.search(userSearchData)
    }
}