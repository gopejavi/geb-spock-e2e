package Pages

import Modules.AlertsModule
import Modules.FooterModule
import Modules.HeaderLoggedModule
import Modules.UsersSearchResultsModule
import geb.Page

class UsersSearchResultsPage extends Page {

    static url = "/users/search"

    static at = {
        title == "Welcome to Vökuró"
        resultsTable.text().contains("Id")
    }

    static content = {
        headerLogged { module HeaderLoggedModule }
        alerts { module AlertsModule }
        resultsTable { $("table") }
        searchResults { resultsTable.$("tbody tr").moduleList(UsersSearchResultsModule) }
        pageButtons { resultsTable.$("tfoot").$("a", text: iContains(it)) }
        footer { module FooterModule }
    }
}