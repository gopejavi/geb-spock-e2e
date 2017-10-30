import DataObjects.LoginData
import Pages.HomePage
import Pages.LoginPage
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

    def "Should navigate to Login Page from Home Page"() {
        given: "I am at Home page"
        to HomePage

        when: "I click on Login at header"
        header.loginLink.click()

        then: "I am at Login Page"
        at LoginPage
    }
/*
    //More complex example with multiple checks with data defined in "Where"
    def "Should create account with fields: name #validSignupData.name, e-mail #validSignupData.email, password #validSignupData.password"() {
        given: "I am at Create Account Page"
        to CreateAccountPage

        when: "I fill the displayed form with valid data"
        fillFormWithData validSignupData

        and: "I click the check to agree the terms and conditions"
        signupForm.termsCheckbox.click()

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I am at Home Page"
        at HomePage

        and: "I receive an email confirming the account creation"
        true //Note: not implemented as app doesn't send emails.

        cleanup:
        VokuroDatabase.restoreOriginal()

        where:
        validSignupData << [
                sharedValidSignupData,
                new SignupData("DOGE", "muchemail@mailinator.com", "soPasswordVerySecretWOW", "soPasswordVerySecretWOW"),
                new SignupData("E", "eve@mailinator.com", "eveveveve", "eveveveve"),
                new SignupData(" _some_!·%&/()\"\$WeirdCharsAreAllowedTooAndAlsoLongNamesWOW", "incrediblyLongAndStupidEmailForTheLol@mailinator.com", "superSecret", "superSecret"),
                new SignupData("Neil DeGrasse", "masterUniverse@mailinator.com", "IamTheGreatMasterOfTheUniverseAndYouKnowIt", "IamTheGreatMasterOfTheUniverseAndYouKnowIt")
        ]
    }

    def "Should not create account withouth required data"() {
        given: "I am at Create Account page"
        to CreateAccountPage

        when: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see error messages under each form input"
        assert signupForm.nameInputErrors.text() == nameRequiredError
        assert signupForm.emailInputErrors.text() == emailRequiredError
        assert signupForm.passwordInputErrors.text() == passwordRequiredError
        assert signupForm.confirmPasswordInputErrors.text() == confirmPasswordRequiredError

        where:
        nameRequiredError = "The name is required"
        emailRequiredError = "The e-mail is required"
        passwordRequiredError = "The password is required"
        confirmPasswordRequiredError = "The confirmation password is required"
    }

    def "Should not create account if email is invalid, like #validSignupDataExceptEmail.email"() {
        given: "I am at Create Account page"
        to CreateAccountPage

        when: "I fill the displayed form with valid data except email wich is invalid"
        fillFormWithData validSignupDataExceptEmail

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see an error message under email input"
        assert signupForm.emailInputErrors.text() == "The e-mail is not valid"

        where:
        validSignupDataExceptEmail << [
                Objects.createDataFrom(sharedValidSignupData, [email: "thisIsNotValidMail"]),
                Objects.createDataFrom(sharedValidSignupData, [email: "thisIsNotValidNeither.com"]),
                Objects.createDataFrom(sharedValidSignupData, [email: "whoCares@aboutDomains"]),
                Objects.createDataFrom(sharedValidSignupData, [email: "asd!)/(/&)]@what.lol"]),
                Objects.createDataFrom(sharedValidSignupData, [email: "email with spaces@omg.com"])
        ]
    }

    def "Should not create account if password is invalid -less than 8 chars-, like #validSignupDataExceptPassword"() {
        given: "I am at Create Account page"
        to CreateAccountPage

        when: "I fill the displayed form with valid data except password wich is invalid"
        fillFormWithData validSignupDataExceptPassword

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see an error message under password input"
        assert signupForm.passwordInputErrors.text() == "Password is too short. Minimum 8 characters"

        where:
        validSignupDataExceptPassword << [
                Objects.createDataFrom(sharedValidSignupData, [password: "1234567", confirmPassword: "1234567"]),
                Objects.createDataFrom(sharedValidSignupData, [password: "shortP", confirmPassword: "shortP"]),
                Objects.createDataFrom(sharedValidSignupData, [password: ")", confirmPassword: ")"])
        ]
    }

    def "Should not create account if passwords do not match, like #validSignupDataExceptUnmatchingPass.password and #validSignupDataExceptUnmatchingPass.confirmPassword"() {
        given: "I am at Create Account page"
        to CreateAccountPage

        when: "I fill the displayed form with valid data except passwords wich do not match"
        fillFormWithData validSignupDataExceptUnmatchingPass

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see an error message under password input"
        assert signupForm.passwordInputErrors.text() == "Password doesn't match confirmation"

        where:
        validSignupDataExceptUnmatchingPass << [
                Objects.createDataFrom(sharedValidSignupData, [password: "12345678", confirmPassword: "12345679"]),
                Objects.createDataFrom(sharedValidSignupData, [password: "SomeLongPass!!", confirmPassword: "SomeLongerPass!!"]),
                Objects.createDataFrom(sharedValidSignupData, [password: "=)(=)(=)(", confirmPassword: "()=()=()="])
        ]
    }

    def "Should not create account when not agreeing terms and conditions"() {
        given: "I am at Create Account Page"
        to CreateAccountPage

        when: "I fill the displayed form with valid data"
        fillFormWithData sharedValidSignupData

        and: "I do not agree with terms and conditions" //for information purposes here and in reports
        true

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see an error message under terms agreement checkbox"
        assert signupForm.termsCheckboxErrors.text() == "Terms and conditions must be accepted"
    }

    def "Should not create account if user already exists, like #validSignupDataWithExistingMail.email"() {
        given: "I am at Create Account Page"
        to CreateAccountPage

        when: "I fill the displayed form with valid data, the email already registered"
        fillFormWithData validSignupDataWithExistingMail

        and: "I click the check to agree the terms and conditions"
        signupForm.termsCheckbox.click()

        and: "I click on button saying Sign Up"
        signupForm.submitButton.click()

        then: "I see error message below the header"
        assert generalErrors.text() == "The email is already registered"

        where:
        validSignupDataWithExistingMail << [
                Objects.createDataFrom(sharedValidSignupData, [email: "gopejavi@mailinator.com"]),
                Objects.createDataFrom(sharedValidSignupData, [email: "veronica@phalconphp.com"])
        ]
    }*/
}