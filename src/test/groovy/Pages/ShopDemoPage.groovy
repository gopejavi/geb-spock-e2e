package Pages

import Modules.FooterModule
import Modules.HeaderModule
import geb.Page

class ShopDemoPage extends Page {

    static url = '/'

    static at = {
        title == 'DEMO - Aplazame'
        header.demoLink.text() == 'Demo'
    }

    static content = {
        header { module HeaderModule }
        payWithAplazameButton { $('.pay-with-aplazame') }
        aplazameContainer(page: AplazameEmbeddedPage) { $('#aplazame-checkout-iframe') }
        footer { module FooterModule }
    }

    def payWithAplazame(nif, card) {
        payWithAplazameButton.click()
        waitFor { aplazameContainer.present }
        withFrame(aplazameContainer) {
            doAllPayment(nif, card)
        }
    }
}