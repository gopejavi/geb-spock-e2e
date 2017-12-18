package Modules

import geb.Module

class AplazameResultModule extends Module {

    static content = {
        modal { $("html").$(".modal-result") }
        warningLogo { modal.$("img", src: "assets/images/result-warning.svg") }
        warningTitle { modal.$("h3") }
        warningDescription { modal.$(".description") }
        buttonDismiss { moda.$("button", "ng-click": " \$modal.dismiss('ko-downpayment') ") }
        buttonBackToShop { moda.$("button", message: " label.back2shop ") }
    }

    def warningDisplayed() {
        assert warningLogo.displayed
    }

    def noFundsText(title, description) {
        assert warningTitle == title
        assert warningDescription == description
    }

    def buttonDismissDisplayed() {
        assert buttonDismiss.displayed
    }

    def buttonBackToShopDisplayed() {
        assert buttonBackToShop.displayed
    }
}
