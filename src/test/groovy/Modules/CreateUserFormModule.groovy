package Modules

import geb.Module

class CreateUserFormModule extends Module {

    static content = {
        form { $("form") }

        nameInput { form.$("#name") }
        emailInput { form.$("#email") }
        profileSelect { form.$("#profilesId") }

        createButton { form.$(".btn", value: "Save") }
    }

    def create(createUserData) {
        nameInput = createUserData.name
        emailInput = createUserData.email
        profileSelect = "Administrators"//for now. Later: userCreateData.profile
        createButton.click()
    }
}
