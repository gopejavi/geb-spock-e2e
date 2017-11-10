package Modules

import geb.Module

class CreateProfileFormModule extends Module {

    static content = {
        form { $("form") }
        nameInput { form.$("#name") }
        createButton { form.$(".btn", value: "Save") }
    }

    def create(createProfileData) {
        nameInput = createProfileData.name
        createButton.click()
    }
}
