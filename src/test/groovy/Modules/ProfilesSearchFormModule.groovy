package Modules

import geb.Module

class ProfilesSearchFormModule extends Module {

    static content = {
        form { $("form") }
        nameInput { form.$("#name") }
        searchButton { form.$(".btn", value: "Search") }
    }

    def search(profileSearchData) {
        nameInput = profileSearchData.name
        searchButton.click()
    }
}
