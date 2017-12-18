package Pages

import DataObjects.CardData
import geb.Page
import org.openqa.selenium.WebElement

class AplazameEmbeddedPage extends Page {

    static content = {
        embeddedApp { $("html") }
        nextCheckoutStepButton { embeddedApp.$("button", "ng-click": "checkout.next()") }
        nifInput { embeddedApp.$("input", name: "document_id") }
        currentCards { i -> embeddedApp.$(".apz-choice-selector").$(".-choice", i) }
        newCreditCard { embeddedApp.$("input", name: "payment_method").lastElement() }
        //aplazameContainer(page: AplazameEmbeddedPage) { $('#aplazame-checkout-iframe') }
        cardNumberInput { newCreditCard.$("#spreedly-number-iframe-1") }
        cardExpirationInput { newCreditCard.$("._expiration") }
        cardCvvInput { newCreditCard.$("._cvv ") }
        smsCode { embeddedApp.$("#sandbox").$("span", 1) }
        smsCodeInput { embeddedApp.$("#OtpSecureInput") }
    }

    def doAllPayment(nif, card) {
        next()
        fillPersonalData(nif)
        createNewCreditCard(card)
        checkout()
        fillSmsCodeAndAuthorizePayment()
    }

    def next() {
        waitFor { nextCheckoutStepButton.displayed }
        nextCheckoutStepButton.click()
    }

    def fillPersonalData(nif) {
        waitFor { nifInput.displayed }
        nifInput = nif
    }

    def createNewCreditCard(CardData card) {

        waitFor { newCreditCard.displayed }
        newCreditCard.click()
        WebElement active = driver.switchTo().activeElement()
        active.sendKeys(card.number)

        active = driver.switchTo().activeElement()
        active.sendKeys(card.expiration)

        active = driver.switchTo().activeElement()
        active.sendKeys(card.cvv)

        //newCreditCard.sendKeys(card.number)
        //newCreditCard.sendKeys(card.expiration)
        // newCreditCard.sendKeys(card.cvv)

        //cardNumberInput = card.number
        //cardExpirationInput = card.expiration
        //cardCvvInput = card.cvv
    }

    def chooseCreditCard(cardIndex) {
        waitFor { currentCards(cardIndex).displayed }
        currentCards(cardIndex).click()
    }

    def checkout() {
        sleep(500) //button animation makes the click fail
        nextCheckoutStepButton.click()
    }

    def fillSmsCodeAndAuthorizePayment() {
        waitFor { smsCodeInput.displayed }
        smsCodeInput = smsCode.text()
    }
}
