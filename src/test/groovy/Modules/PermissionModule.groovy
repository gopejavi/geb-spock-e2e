package Modules

import geb.Module

class PermissionModule extends Module {

    static content = {
        cell { $("td", it) }
        checkbox { cell(0).$("input", type: "checkbox") }
        description { cell(1).text() }
    }

    def change(permission) {
        checkbox = permission
    }
}
