import Pages.HomePage
import Utils.CommonSpecFeatures
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Title

@Title("US1: Basic Home Page")
@Narrative("""
As an Internet surfer
I want a Home page
So that I can start using this web
""")
@Issue("https://trello.com/c/x9lV0DqC")
class BasicHomePageSpec extends CommonSpecFeatures {

    def "Home page should exist"() {
        given: "I go to Home page"
        to HomePage

        when: "I do nothing"
        true

        then: "We are at the Home page"
        at HomePage
    }
}
