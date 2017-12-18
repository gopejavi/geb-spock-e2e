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
        paymentMethod { modalCheckout.$("input", name: "payment_method") }
        smsCode { modalCheckout.$("#sandbox").$("span", 1) }
        smsCodeInput { modalCheckout.$("#OtpSecureInput") }

        checkoutResult { $("html").$(".modal-result").module(AplazameResultModule) }
    }

    def doAllPayment(nif, card) {
        nextStep()
        fillPersonalData(nif)
        createNewCreditCard(card)
        checkout()
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
        selectNewCreditCard()

        WebElement active = driver.switchTo().activeElement()
        active.sendKeys(card.number)

        active = driver.switchTo().activeElement()
        active.sendKeys(card.expiration)

        active = driver.switchTo().activeElement()
        active.sendKeys(card.cvv)
    }

    def selectNewCreditCard() {
        sleep(750) //Should not use but I could not do it in other way.
        waitFor(2, {
            def newCreditCard = paymentMethod.lastElement()
            if (newCreditCard.displayed) {
                newCreditCard.click()
            } else
                sleep(1500)
            true
        })
    }

    def checkout() {
        sleep(500)
        nextCheckoutStepButton.click()
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
