package Pages

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
        footer { module FooterModule }

        mainTitle { $("h1") }
        mainSubtitle { $("#subtitle") }

        //parametrized module example
        pageSection { indexValue -> module new PageSectionModule(index: indexValue) }
        awesomeSection { pageSection("1") }
        importantStuff { pageSection("2") }

        exampleAddresses { pageSection("3") }
        officeAddress { exampleAddresses.$("address")[0] }
        officeAddressTitle { officeAddress.$("strong") }
        internetAddress { exampleAddresses.$("address")[1] }
        internetAddressTitle { internetAddress.$("strong") }
    }
}