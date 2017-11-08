package Modules

import geb.Module

class UsersSearchFormModule extends Module {

    static content = {
        form { $("form") }

        nameInput { form.$("#name") }
        emailInput { form.$("#email") }

        searchButton { form.$(".btn", value: "Search") }
    }

    def search(userSearchData) {
        nameInput = userSearchData.name
        emailInput = userSearchData.email
        searchButton.click()
    }
}
