import DataObjects.LoginData
import Pages.ChangePasswordPage
import Pages.LoginPage
import Pages.UsersPage
import geb.spock.GebReportingSpec
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Stepwise
import spock.lang.Title

@Title("US13: Change Password")
@Narrative("""
As registered user
I want to change my password
So my account is safe if password is compromised
""")
@Issue("https://trello.com/c/8L1aVKg9")
@Stepwise
class ChangePasswordSpec extends GebReportingSpec {

    def setupSpec() {
        to LoginPage
        def validLoginData = new LoginData("gopejavi@mailinator.com", "superSecret!!!")
        login(validLoginData)
    }

    def "Should have \"username -> change password\" at header"() {
        given: "Given I am at Users page"
        to UsersPage

        when: " I click on my user name at header"
        headerLogged.userDropdown.click()

        and: "I click on Change Password at dropdown"
        headerLogged.changePasswordLink.click()

        then: "I am at Change Password Page"
        at ChangePasswordPage
    }
/*
    def "Registered user should log in successfully with e-mail #sharedValidLoginData.email, password #sharedValidLoginData.password"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I log in with valid data"
        login(sharedValidLoginData)

        then: "I am at Users Page"
        at UsersPage

        cleanup: //because even if no creating new objects, a login count could be stored, changing initial conditions to other tests
        VokuroDatabase.restoreOriginal()
    }

    def "Should not log in without email"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I log in with valid password, empty email"
        login(emptyMailValidPassword)

        then: "I see an error message below the header"
        assert generalErrors*.text().any { it == "The e-mail is required" }

        where:
        emptyMailValidPassword = DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: ""])
    }

    def "Should not login if email is invalid, like #validLoginDataExceptEmail.email"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I fill the displayed form with valid data except email wich is invalid"
        login(validLoginDataExceptEmail)

        then: "I see an error message under below the header"
        assert generalErrors*.text().any { it == "The e-mail is not valid" }

        where:
        validLoginDataExceptEmail << [
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "thisIsNotValidMail"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "thisIsNotValidNeither.com"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "whoCares@aboutDomains"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "asd!)/(/&)]@what.lol"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "email with spaces@omg.com"])
        ]
    }

    def "I should not be able to log in without password"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I log in valid data except password wich is blank"
        login(validLoginDataExceptPassword)

        then: "I see an error message below the header"
        assert generalErrors*.text().any { it == "The password is required" }

        where:
        validLoginDataExceptPassword = DataObjectsHelper.createDataFrom(sharedValidLoginData, [password: ""])
    }

    def "Should not log in with bad email-password combination, like #badMailPassCombo.email and #badMailPassCombo.password"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I log in with a not existent email-password combination"
        login(badMailPassCombo)

        then: "I see an error message below the header"
        assert generalErrors*.text().any { it == "Wrong email/password combination" }

        where:
        badMailPassCombo << [
                new LoginData("gopejavi@mailinator.com", "12345679"),
                new LoginData("notInDB@mailinator.com", "superSecret!!!"),
                new LoginData("fully@inventedEmail.lol", "alsoVeryInvented!"),
                new LoginData("veronica@phalconphp.com", "superSecret!!!") //both exist in DB (don't match)
        ]
    }*/
}