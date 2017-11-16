package Modules

import geb.Module

class ProfilesSearchResultsModule extends Module {

    static content = {
        cell { $("td", it) }
        id { cell(0).text().toInteger() }
        name { cell(1).text() }
    }
}
