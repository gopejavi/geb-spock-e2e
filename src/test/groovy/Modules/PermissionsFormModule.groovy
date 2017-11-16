package Modules

import DataObjects.PermissionsData
import geb.Module

class PermissionsFormModule extends Module {

    static content = {
        form { $("form") }
        profileSelect { form.$("#profileId") }
        searchButton { form.$(".btn", value: "Search") }

        permissionsTable { $("table").not(".perms") }
        permissions { permissionsTable.$("tbody tr").moduleList(PermissionModule) }
        submitButton { form.$(".btn", value: "Submit") }
    }

    def search(profile) {
        profileSelect = profile
        searchButton.click()
    }

    def changePermissions(permissionsValues) {
        PermissionsData.declaredFields.findAll { !it.synthetic }*.name.eachWithIndex { it, index ->
            permissions[index].change(permissionsValues[it])
        }
        submitButton.click()
    }
}
