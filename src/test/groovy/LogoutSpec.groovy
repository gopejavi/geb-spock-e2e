import DataObjects.LoginData
import Pages.HomePage
import Pages.LoginPage
import Pages.UsersPage
import geb.spock.GebReportingSpec
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

@Title("US10: Log out")
@Narrative("""
As logged user
I want to log out the web
So nobody steal my super-secret info
""")
@Issue("https://trello.com/c/S97G1noM")
class LogoutSpec extends GebReportingSpec {

    @Shared
    LoginData sharedValidLoginData = new LoginData("gopejavi@mailinator.com", "superSecret!!!")

    def setup() {
        println "setup!"
        to LoginPage
        login(sharedValidLoginData)
    }

    def "Registered user should log out when clicking on log out link, not being able to go to private pages"() {

        given: "I am at Users page"
        to UsersPage

        when: "I click on Log out link at header"
        headerLogged.logoutLink.click()

        and: "I try to go to Users url without login in"
        go UsersPage.url

        then: "I stay at Home page, showing error below the header"
        at HomePage
        assert generalErrors*.text().any { it == "You don't have access to this module: private" }

    }
}