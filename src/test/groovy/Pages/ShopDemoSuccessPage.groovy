package Pages

import Modules.AlertsModule
import Modules.FooterModule
import Modules.HeaderModule
import geb.Page

class ShopDemoSuccessPage extends Page {

    static url = "/demo-success.html"

    static at = {
        title == "DEMO - Aplazame"
        header.demoLink.text() == "Demo"
    }

    static content = {
        header { module HeaderModule }
        alerts { module AlertsModule }
        footer { module FooterModule }
    }
}