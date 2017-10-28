import Pages.*
import geb.spock.GebReportingSpec
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

@Title("US4: web header")
@Narrative("""
As an Internet surfer
I want a header in public pages
So that I can navigate easily to all pages
""")
@Issue("https://trello.com/c/87HqKCDP, https://trello.com/c/IlZMPbcA")
class HeaderSpec extends GebReportingSpec {

    @Shared
            expectedVokuroBrandText = "Vökuró"
    @Shared
            expectedHomeLinkText = "Home"
    @Shared
            expectedAboutLinkText = "About"

    def "Should have a header in Home page"() {
        given: "I am at Home page"
        to HomePage

        when: "I do nothing"
        true

        then: "I see a red header with Vokuro logo"
        // We won't check the "red" in CSS as example about what should be manually tested
        // (even if correct maybe it looks ugly, etc)
        assert header.brand.text() == expectedVokuroBrandText

        and: "links to Home page, About page"
        assert header.homeLink.text() == expectedHomeLinkText
        assert header.aboutLink.text() == expectedAboutLinkText
    }

    def "Should have a header in About page"() {
        given: "I am at About page"
        to AboutPage

        when: "I do nothing"
        true

        then: "I see a red header with Vokuro logo"
        assert header.brand.text() == expectedVokuroBrandText

        and: "links to Home page, About page"
        assert header.homeLink.text() == expectedHomeLinkText
        assert header.aboutLink.text() == expectedAboutLinkText
    }

    def "Should have a header in Privacy Policy page"() {
        given: "I am at Privacy Policy page"
        to PrivacyPolicyPage

        when: "I do nothing"
        true

        then: "I see a red header with Vokuro logo"
        assert header.brand.text() == expectedVokuroBrandText

        and: "links to Home page, About page"
        assert header.homeLink.text() == expectedHomeLinkText
        assert header.aboutLink.text() == expectedAboutLinkText
    }

    def "Should have a header in Terms page"() {
        given: "I am at Terms page"
        to TermsPage

        when: "I do nothing"
        true

        then: "I see a red header with Vokuro logo"
        assert header.brand.text() == expectedVokuroBrandText

        and: "links to Home page, About page"
        assert header.homeLink.text() == expectedHomeLinkText
        assert header.aboutLink.text() == expectedAboutLinkText
    }

    def "Should have a header in Create Account page"() {
        given: "I am at Create Account page"
        to CreateAccountPage

        when: "I do nothing"
        true

        then: "I see a red header with Vokuro logo"
        assert header.brand.text() == expectedVokuroBrandText

        and: "links to Home page, About page"
        assert header.homeLink.text() == expectedHomeLinkText
        assert header.aboutLink.text() == expectedAboutLinkText
    }

    //We decide to perform an extra test to check that links actually work
    def "About link should navigate to About page"() {
        given: "I am at Home page"
        to HomePage

        when: "I click on About link"
        header.aboutLink.click()

        then: "I am at About page"
        at AboutPage
    }

    def "Home link should navigate to Home page"() {
        given: "I am at About page"
        to AboutPage

        when: "I click on Home link"
        header.homeLink.click()

        then: "I am at About page"
        at HomePage
    }
}
