package Pages

import Modules.AlertsModule
import Modules.FooterModule
import Modules.HeaderModule
import Modules.PageSectionModule
import geb.Page

class HomePage extends Page {

    static url = ""

    static at = {
        title == "Welcome to Vökuró"
        mainTitle.text() == "Welcome!"
    }

    static content = {
        header { module HeaderModule }
        alerts { module AlertsModule }
        mainTitle { $("h1") }
        mainSubtitle { $("#subtitle") }

        createAccountButton { $("#overview .btn", href: "/session/signup") }

        //parametrized module example
        pageSection { indexValue -> module new PageSectionModule(index: indexValue) }
        awesomeSection { pageSection("1") }
        importantStuff { pageSection("2") }

        exampleAddresses { pageSection("3") }
        officeAddress { exampleAddresses.$("address")[0] }
        officeAddressTitle { officeAddress.$("strong") }
        internetAddress { exampleAddresses.$("address")[1] }
        internetAddressTitle { internetAddress.$("strong") }

        footer { module FooterModule }
    }
}