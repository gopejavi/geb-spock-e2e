import DataObjects.LoginData
import Pages.LoginPage
import Utils.DataObjectsHelper
import geb.spock.GebReportingSpec
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

@Title("US9: Log in")
@Narrative("""
As registered user
I want to log in
So I can access more features
""")
@Issue("https://trello.com/c/plRCzj6C")
class LoginSpec extends GebReportingSpec {

    @Shared
    LoginData sharedValidLoginData = new LoginData("gopejavi@mailinator.com", "superSecret!!!")
/*
    def "Should navigate to Login Page from Home Page"() {
        given: "I am at Home page"
        to HomePage

        when: "I click on Login at header"
        header.loginLink.click()

        then: "I am at Login Page"
        at LoginPage
    }

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
*/

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

/*
    def "Should not log In if email is invalid, like #validLoginDataExceptEmail.email"() {
        given: "I am at Log In page"
        to LogInPage

        when: "I fill the displayed form with valid data except email wich is invalid"
        fillFormWithData validLoginDataExceptEmail

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see an error message under email input"
        assert signupForm.emailInputErrors.text() == "The e-mail is not valid"

        where:
        validLoginDataExceptEmail << [
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "thisIsNotValidMail"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "thisIsNotValidNeither.com"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "whoCares@aboutDomains"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "asd!)/(/&)]@what.lol"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "email with spaces@omg.com"])
        ]
    }

    def "Should not log In if password is invalid -less than 8 chars-, like #validLoginDataExceptPassword"() {
        given: "I am at Log In page"
        to LogInPage

        when: "I fill the displayed form with valid data except password wich is invalid"
        fillFormWithData validLoginDataExceptPassword

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see an error message under password input"
        assert signupForm.passwordInputErrors.text() == "Password is too short. Minimum 8 characters"

        where:
        validLoginDataExceptPassword << [
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [password: "1234567", confirmPassword: "1234567"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [password: "shortP", confirmPassword: "shortP"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [password: ")", confirmPassword: ")"])
        ]
    }

    def "Should not log In if passwords do not match, like #validLoginDataExceptUnmatchingPass.password and #validLoginDataExceptUnmatchingPass.confirmPassword"() {
        given: "I am at Log In page"
        to LogInPage

        when: "I fill the displayed form with valid data except passwords wich do not match"
        fillFormWithData validLoginDataExceptUnmatchingPass

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see an error message under password input"
        assert signupForm.passwordInputErrors.text() == "Password doesn't match confirmation"

        where:
        validLoginDataExceptUnmatchingPass << [
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [password: "12345678", confirmPassword: "12345679"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [password: "SomeLongPass!!", confirmPassword: "SomeLongerPass!!"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [password: "=)(=)(=)(", confirmPassword: "()=()=()="])
        ]
    }

    def "Should not log In when not agreeing terms and conditions"() {
        given: "I am at Log In Page"
        to LogInPage

        when: "I fill the displayed form with valid data"
        fillFormWithData sharedValidLoginData

        and: "I do not agree with terms and conditions" //for information purposes here and in reports
        true

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see an error message under terms agreement checkbox"
        assert signupForm.termsCheckboxErrors.text() == "Terms and conditions must be accepted"
    }

    def "Should not log In if user already exists, like #validLoginDataWithExistingMail.email"() {
        given: "I am at Log In Page"
        to LogInPage

        when: "I fill the displayed form with valid data, the email already registered"
        fillFormWithData validLoginDataWithExistingMail

        and: "I click the check to agree the terms and conditions"
        signupForm.termsCheckbox.click()

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see error message below the header"
        assert generalErrors.text() == "The email is already registered"

        where:
        validLoginDataWithExistingMail << [
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "gopejavi@mailinator.com"]),
                DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: "veronica@phalconphp.com"])
        ]
    }

    */
}