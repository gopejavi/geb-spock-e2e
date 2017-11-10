package Pages

import Modules.AlertsModule
import Modules.HeaderLoggedModule
import Modules.ProfilesSearchFormModule
import geb.Page

class ProfilesPage extends Page {

    static url = "/profiles"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Search profiles"
    }

    static content = {
        headerLogged { module HeaderLoggedModule }
        alerts { module AlertsModule }
        createProfileButton { $(".btn", href: "/profiles/create") }
        mainPanelTitle { $("h2") }
        searchForm { module ProfilesSearchFormModule }
    }

    def search(profileSearchData) {
        searchForm.search(profileSearchData)
    }
}