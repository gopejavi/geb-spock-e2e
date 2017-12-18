import DataObjects.CardData
import Pages.ShopDemoPage
import Pages.ShopDemoSuccessPage
import Utils.Const
import geb.spock.GebReportingSpec
import spock.lang.*

import static Utils.DataObjectsHelper.createDataFrom

//so general, just an example. Should be taken from Jira/other:
@Title("User Story 1: Payment via Aplazame works")

//Valeria is a user persona. She can always buy whatever and successfully pay with one card.
@Narrative("""
As Veleria
I want to pay with Aplazame
So I can finalize my awesome purchase
""")
//this would link to the Jira/other User Story or task:
@Issue("https://www.example.com")
//examples of realted links, to show in reports.
@See("https://betabeers.com/post/desarrollador-qa-3271/", "https://aplazame.com/docs/api/making-requests/#datos-de-prueba")
class PaySuccessSpec extends GebReportingSpec {

    @Shared
    CardData successCard = new CardData("4111111111111111", "1118", "000")

    def "Should accept the payment from client with NIF #nif and card number #card.number"() {
        given: "The shopping cart checkout page is ready"
        to ShopDemoPage

        when: "I successfully pay with Aplazame"
        payWithAplazame(nif, card)

        then: "I am at success page with success message"
        at ShopDemoSuccessPage
        assert alerts.brand*.text().any { it == Const.PAYMENT_COMPLETE }

        where: //like "examples" in pure Gherkin
        nif          | card
        "99999992 V" | successCard
    }

    def "Should not accept the payment from client with NIF #nif and card with no funds and number #card.number"() {
        given: "The shopping cart checkout page is ready"
        to ShopDemoPage

        when: "I successfully pay with Aplazame"
        payWithAplazame(nif, card)

        then: "I see a warning about the bank denying the purchase"


        and: "I have to options: select another card and go back to the store"
        assert alerts.brand*.text().any { it == Const.PAYMENT_COMPLETE }

        where: //like "examples" in pure Gherkin
        nif          | card
        "99999992 V" | createDataFrom(successCard, [number: "4012888888881881"])
    }
}