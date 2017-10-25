import Pages.CreateAccountPage
import Pages.HomePage
import geb.spock.GebReportingSpec
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Title

@Title("US8: Create Account")
@Narrative("""
As an interested user
I want to create an account
So I access to exclusive features""")
@Issue("https://trello.com/c/IlZMPbcA")
class CreateAccountSpec extends GebReportingSpec {

    def "Navigation to Create Account Page"() {
        given: "I am at Home page"
        to HomePage

        when: "I click on a button saying Create an Account"
        createAccountButton.click()

        then: "I am at Create Account Page"
        at CreateAccountPage
    }
}
