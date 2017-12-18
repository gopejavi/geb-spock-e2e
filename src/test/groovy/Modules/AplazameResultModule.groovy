package Modules

import geb.Module

class AplazameResultModule extends Module {

    static content = {
        warningLogo { $("img", src: "assets/images/result-warning.svg") }
        warningTitle { $("h3") }
        warningDescription { $(".description") }
        buttonDismiss { $("button", "ng-click": " \$modal.dismiss('ko-downpayment') ") }
        buttonBackToShop { $("button", message: " label.back2shop ") }
    }

    def warningDisplayed() {
        return warningLogo.displayed
    }

    def noFundsText(title, description) {
        return warningTitle.text() == title && warningDescription.text() == description
    }

    def buttonDismissDisplayed() {
        return buttonDismiss.displayed
    }

    def buttonBackToShopDisplayed() {
        return buttonBackToShop.displayed
    }
}
