package Pages

import Modules.AlertsModule
import Modules.HeaderLoggedModule
import Modules.PermissionsFormModule
import geb.Page

class PermissionsPage extends Page {

    static url = "/permissions"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Manage Permissions"
    }

    static content = {
        headerLogged { module HeaderLoggedModule }
        alerts { module AlertsModule }
        mainPanelTitle { $("h2") }
        permissionsForm { module PermissionsFormModule }
    }

    def search(profile) {
        permissionsForm.search(profile)
    }

    def changePermissions(permissionsValues) {
        permissionsForm.changePermissions(permissionsValues)
    }
}
