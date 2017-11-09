package Modules

import geb.Module

class UsersCreateFormModule extends Module {

    static content = {
        form { $("form") }

        nameInput { form.$("#name") }
        emailInput { form.$("#email") }
        profileSelect { form.$("profilesId") }

        searchButton { form.$(".btn", value: "Save") }
    }

    def create(userCreateData) {
        nameInput = userCreateData.name
        emailInput = userCreateData.email
        profileSelect = userCreateData.profile
        searchButton.click()
    }
}
