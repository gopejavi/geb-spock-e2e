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
        newCreditCard { modalCheckout.$("input", name: "payment_method").lastElement() }
        paymentMethod { modalCheckout.$(".payment_method") }
        smsCode { modalCheckout.$("#sandbox").$("span", 1) }
        smsCodeInput { modalCheckout.$("#OtpSecureInput") }

        checkoutResult { module AplazameResultModule }
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
        sleep(500) //Should not use but I could not do it in other way.
        waitFor(1.5, {
            if (newCreditCard.displayed)
                newCreditCard.click()
            else
                sleep(1000)
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
        checkoutResult.warningDisplayed()
    }

    def noFundsHasText(title, description) {
        checkoutResult.noFundsText(title, description)
    }

    def buttonDismissIsDisplayed() {
        checkoutResult.buttonDismissDisplayed()
    }

    def buttonBackToShopIsDisplayed() {
        checkoutResult.buttonBackToShopDisplayed()
    }

}
