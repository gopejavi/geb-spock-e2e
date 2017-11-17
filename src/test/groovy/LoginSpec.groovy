import DataObjects.LoginData
import Pages.HomePage
import Pages.LoginPage
import Pages.UsersPage
import Utils.CommonSpecFeatures
import Utils.Const
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

import static Utils.DataObjectsHelper.createDataFrom

@Title("US9: Log in")
@Narrative("""
As registered user
I want to log in
So I can access more features
""")
@Issue("https://trello.com/c/plRCzj6C")
class LoginSpec extends CommonSpecFeatures {

    @Shared
    LoginData sharedValidLoginData = new LoginData("gopejavi@mailinator.com", "superSecret!!!")

    def "Should navigate to Login Page from Home Page"() {
        given: "I am at Home page"
        to HomePage

        when: "I click on Login at header"
        header.loginLink.click()

        then: "I am at Login Page"
        at LoginPage
    }

    def "Registered user should log in successfully with e-mail #validLoginData.email, password #validLoginData.password"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I log in with valid data"
        login(validLoginData)

        then: "I am at Users Page"
        at UsersPage

        where: //just for reports
        validLoginData = sharedValidLoginData
    }

    def "Should not log in with email '#emptyMailValidPassword.email'"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I log in with valid password, empty email"
        login(emptyMailValidPassword)

        then: "I see an error message below the header"
        assert alerts.error*.text().any { it == Const.EMAIL_REQUIRED }

        where:
        emptyMailValidPassword = createDataFrom(sharedValidLoginData, [email: ""])
    }

    def "Should not login if email is invalid, like #validLoginDataExceptEmail.email"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I fill the displayed form with valid data except email wich is invalid"
        login(validLoginDataExceptEmail)

        then: "I see an error message under below the header"
        assert alerts.error*.text().any { it == Const.EMAIL_NOT_VALID }

        where:
        validLoginDataExceptEmail << [
                createDataFrom(sharedValidLoginData, [email: "thisIsNotValidMail"]),
                createDataFrom(sharedValidLoginData, [email: "thisIsNotValidNeither.com"]),
                createDataFrom(sharedValidLoginData, [email: "whoCares@aboutDomains"]),
                createDataFrom(sharedValidLoginData, [email: "asd!)/(/&)]@what.lol"]),
                createDataFrom(sharedValidLoginData, [email: "email with spaces@omg.com"])
        ]
    }

    def "I should not be able to log in with password '#validLoginDataExceptPassword.password'"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I log in valid data except password wich is blank"
        login(validLoginDataExceptPassword)

        then: "I see an error message below the header"
        assert alerts.error*.text().any { it == Const.PASS_REQUIRED }

        where:
        validLoginDataExceptPassword = createDataFrom(sharedValidLoginData, [password: ""])
    }

    def "Should not log in with bad email-password combination, like #badMailPassCombo.email and #badMailPassCombo.password"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I log in with a not existent email-password combination"
        login(badMailPassCombo)

        then: "I see an error message below the header"
        assert alerts.error*.text().any { it == Const.WRONG_EMAIL_PASS }

        where:
        badMailPassCombo << [
                new LoginData("gopejavi@mailinator.com", "12345679"),
                new LoginData("notInDB@mailinator.com", "superSecret!!!"),
                new LoginData("fully@inventedEmail.lol", "alsoVeryInvented!"),
                new LoginData("veronica@phalconphp.com", "superSecret!!!") //both exist in DB (don't match)
        ]
    }
}