package Pages

import DataObjects.CreditCardData
import Modules.AplazameResultModule
import geb.Page
import org.openqa.selenium.WebElement

class AplazameCheckoutPage extends Page {

    static content = {
        modalCheckout { $("html").$(".modal-checkout") }
        nextCheckoutStepButton { modalCheckout.$("button", "ng-click": "checkout.next()") }
        nifInput { modalCheckout.$("input", name: "document_id") }
        creditCardArea { $('.apz-choice-selector .-choice-wrapper').lastElement() }
        smsCode { modalCheckout.$("#sandbox").$("span", 1) }
        smsCodeInput { modalCheckout.$("#OtpSecureInput") }

        checkoutResult { $("html").$(".modal-result").module(AplazameResultModule) }
    }

    def doAllPayment(nif, card) {
        nextStep()
        fillPersonalData(nif)
        createNewCreditCard(card)
        nextStep()
        fillSmsCodeToAuthorizePayment()
    }

    def nextStep() {
        waitFor { nextCheckoutStepButton.displayed }
        nextCheckoutStepButton.click()
    }

    def fillPersonalData(nif) {
        waitFor { nifInput.displayed }
        nifInput = nif
    }

    def createNewCreditCard(CreditCardData card) {
        waitFor { creditCardArea.displayed }
        creditCardArea.click()

        WebElement active = driver.switchTo().activeElement()
        active.sendKeys(card.number)
        sleep(100)

        active = driver.switchTo().activeElement()
        active.sendKeys(card.expiration)
        sleep(100)

        active = driver.switchTo().activeElement()
        active.sendKeys(card.cvv)
    }

    def fillSmsCodeToAuthorizePayment() {
        waitFor { smsCodeInput.displayed }
        smsCodeInput = smsCode.text()
    }

    def warningDisplayed() {
        waitFor({ checkoutResult.displayed })
        return checkoutResult.warningDisplayed()
    }

    def noFundsText(title, description) {
        waitFor({ checkoutResult.displayed })
        return checkoutResult.noFundsText(title, description)
    }

    def buttonDismissDisplayed() {
        waitFor({ checkoutResult.displayed })
        return checkoutResult.buttonDismissDisplayed()
    }

    def buttonBackToShopDisplayed() {
        waitFor({ checkoutResult.displayed })
        return checkoutResult.buttonBackToShopDisplayed()
    }

}
