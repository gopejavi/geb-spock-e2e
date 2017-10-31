import DataObjects.SignupData
import Pages.CreateAccountPage
import Pages.HomePage
import Utils.DataObjectsHelper
import Utils.VokuroDatabase
import geb.spock.GebReportingSpec
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

@Title("US8: Create Account")
@Narrative("""
As an interested user
I want to create an account
So I access to exclusive features
""")
@Issue("https://trello.com/c/IlZMPbcA")
class CreateAccountSpec extends GebReportingSpec {

    @Shared
    SignupData sharedValidSignupData = new SignupData("gopejavi", "email@mailinator.com", "superSecret", "superSecret", true)

    def "Should navigate to Create Account Page from Home Page"() {
        given: "I am at Home page"
        to HomePage

        when: "I click on a button saying Create an Account"
        createAccountButton.click()

        then: "I am at Create Account Page"
        at CreateAccountPage
    }

    //More complex example with multiple checks with data defined in "Where"
    def "Should create account with fields: name #validSignupData.name, e-mail #validSignupData.email, password #validSignupData.password"() {
        given: "I am at Create Account Page"
        to CreateAccountPage

        when: "I sign up with valid data"
        signup(validSignupData)

        then: "I am at Home Page"
        at HomePage

        and: "I receive an email confirming the account creation"
        true //Note: not implemented as app doesn't send emails.

        cleanup:
        VokuroDatabase.restoreOriginal()

        where:
        validSignupData << [
                sharedValidSignupData,
                new SignupData("DOGE", "muchemail@mailinator.com", "soPasswordVerySecretWOW", "soPasswordVerySecretWOW", true),
                new SignupData("E", "eve@mailinator.com", "eveveveve", "eveveveve", true),
                new SignupData(" _some_!·%&/()\"\$WeirdCharsAreAllowedTooAndAlsoLongNamesWOW", "incrediblyLongAndStupidEmailForTheLol@mailinator.com", "superSecret", "superSecret", true),
                new SignupData("Neil DeGrasse", "masterUniverse@mailinator.com", "IamTheGreatMasterOfTheUniverseAndYouKnowIt", "IamTheGreatMasterOfTheUniverseAndYouKnowIt", true)
        ]
    }

    def "Should not create account without required data"() {
        given: "I am at Create Account page"
        to CreateAccountPage

        when: "I click on button saying Sign Up"
        signupForm.signupButton.click()

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

        when: "I sign up with valid data except email wich is invalid"
        signup(validSignupDataExceptEmail)

        then: "I see an error message under email input"
        assert signupForm.emailInputErrors.text() == "The e-mail is not valid"

        where:
        validSignupDataExceptEmail << [
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [email: "thisIsNotValidMail"]),
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [email: "thisIsNotValidNeither.com"]),
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [email: "whoCares@aboutDomains"]),
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [email: "asd!)/(/&)]@what.lol"]),
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [email: "email with spaces@omg.com"])
        ]
    }

    def "Should not create account if password is invalid -less than 8 chars-, like #validSignupDataExceptPassword"() {
        given: "I am at Create Account page"
        to CreateAccountPage

        when: "I sign up with valid data except password wich is invalid"
        signup(validSignupDataExceptPassword)

        then: "I see an error message under password input"
        assert signupForm.passwordInputErrors.text() == "Password is too short. Minimum 8 characters"

        where:
        validSignupDataExceptPassword << [
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [password: "1234567", confirmPassword: "1234567"]),
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [password: "shortP", confirmPassword: "shortP"]),
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [password: ")", confirmPassword: ")"])
        ]
    }

    def "Should not create account if passwords do not match, like #validSignupDataExceptUnmatchingPass.password and #validSignupDataExceptUnmatchingPass.confirmPassword"() {
        given: "I am at Create Account page"
        to CreateAccountPage

        when: "I sign up with valid data except passwords wich do not match"
        signup(validSignupDataExceptUnmatchingPass)

        then: "I see an error message under password input"
        assert signupForm.passwordInputErrors.text() == "Password doesn't match confirmation"

        where:
        validSignupDataExceptUnmatchingPass << [
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [password: "12345678", confirmPassword: "12345679"]),
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [password: "SomeLongPass!!", confirmPassword: "SomeLongerPass!!"]),
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [password: "=)(=)(=)(", confirmPassword: "()=()=()="])
        ]
    }

    def "Should not create account when not agreeing terms and conditions"() {
        given: "I am at Create Account Page"
        to CreateAccountPage

        when: "I sign up with valid data but not accepting Terms and Conditions"
        signup(validSignupDataButDontAgreeTerms)

        then: "I see an error message under terms agreement checkbox"
        assert signupForm.termsCheckboxErrors.text() == "Terms and conditions must be accepted"

        where:
        validSignupDataButDontAgreeTerms = DataObjectsHelper.createDataFrom(sharedValidSignupData, [agreeTermsConditions: false])
    }

    def "Should not create account if user already exists, like #validSignupDataWithExistingMail.email"() {
        given: "I am at Create Account Page"
        to CreateAccountPage

        when: "I sign up with valid data, the email already registered"
        signup(validSignupDataWithExistingMail)

        then: "I see error message below the header"
        assert generalErrors.text() == "The email is already registered"

        where:
        validSignupDataWithExistingMail << [
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [email: "gopejavi@mailinator.com"]),
                DataObjectsHelper.createDataFrom(sharedValidSignupData, [email: "veronica@phalconphp.com"])
        ]
    }
}