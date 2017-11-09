package Utils

import DataObjects.LoginData
import Pages.LoginPage

abstract class CommonLoggedSpecFeatures extends CommonSpecFeatures {

    def setupSpec() {
        to LoginPage
        login(new LoginData("gopejavi@mailinator.com", "superSecret!!!"))
    }
}