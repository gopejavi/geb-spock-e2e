package Modules

import geb.Module

class AplazameCheckoutAppModule extends Module {

    static content = {
        embeddedApp { $("html") }
        nifInput { embeddedApp.$("input", name: "document_id") }
        nextCheckoutStepButton { embeddedApp.$("button", type: "submit") }
        smsCode { embeddedApp.$("#sandbox").$("span")[1] }
        smsCodeInput { embeddedApp.$("#OtpSecureInput") }

    }

    def fillPaymentInfo(nif) {
        waitFor { nifInput.present }
        nifInput = nif
    }

    def checkout() {
        waitFor { nifInput.present }
        nextCheckoutStepButton.click()
    }

    def fillSmsCodeAndAuthorizePayment() {
        waitFor { nifInput.present }
        smsCodeInput = smsCode.text()
    }
}
