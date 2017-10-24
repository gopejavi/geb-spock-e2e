import Pages.HomePage
import geb.spock.GebReportingSpec
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Title

@Title("US5: Web footer")
@Narrative("""
As an Internet surfer
I want a footer
So that I can always see a lovely message and links to Policy and Terms pages
""")
@Issue("https://trello.com/c/shFUUuDd")
class FooterSpec extends GebReportingSpec {

    def "I have a header in Home page"() {
        given: "I am at Home page"
        to HomePage

        when: "I do nothing"
        true

        then: "I see a footer containing message #expectedMessage"
        // We won't check the "red" in CSS as example about what should be manually tested
        // (even if correct maybe it looks ugly, etc)
        assert footer.text().contains(expectedMessage)

        and: "links to Privacy Policy page, Terms page"
        assert footer.privacyLink.text() == "Privacy Policy"
        assert footer.termsLink.text() == "Terms"

        where:
        expectedMessage = "Made with love"
    }
/*
    def "Home link navigates to Home page"() {
        given: "I am at About page"
        to AboutPage

        when: "I click on About link"
        header.homeLink.click()

        then: "I am at About page"
        at HomePage
    }*/
}
