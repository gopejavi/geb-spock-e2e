package Modules

import geb.Module

class UsersSearchResultsModule extends Module {

    static content = {
        cell { $("td", it) }
        id { cell(0).text().toInteger() }
        name { cell(1).text() }
        email { cell(2).text() }
        profile { cell(3).text() }
    }
}
