package Pages

import Modules.AlertsModule
import Modules.HeaderLoggedModule
import Modules.ProfilesSearchResultsModule
import geb.Page

class ProfilesSearchResultsPage extends Page {

    static url = "/profiles/search"

    static at = {
        title == "Welcome to Vökuró"
        resultsTable.text().contains("Id")
    }

    static content = {
        headerLogged { module HeaderLoggedModule }
        alerts { module AlertsModule }
        resultsTable { $("table") }
        searchResults { resultsTable.$("tbody tr").moduleList(ProfilesSearchResultsModule) }
        pageButtons { resultsTable.$("tfoot").$("a", text: iContains(it)) }
    }
}