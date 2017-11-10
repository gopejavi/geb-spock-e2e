package Pages

import Modules.AlertsModule
import Modules.CreateProfileFormModule
import Modules.HeaderLoggedModule
import geb.Page

class CreateProfilePage extends Page {

    static url = "/profiles/create"

    static at = {
        title == "Welcome to Vökuró"
        mainPanelTitle.text() == "Create a Profile"
    }

    static content = {
        headerLogged { module HeaderLoggedModule }
        alerts { module AlertsModule }
        goBackButton { $(".pager .previous") }
        mainPanelTitle { $("h2") }
        createForm { module CreateProfileFormModule }
    }

    def create(createProfileData) {
        createForm.create(createProfileData)
    }
}