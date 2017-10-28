import Classes.SignupData
import Pages.CreateAccountPage
import Pages.HomePage
import Utils.Classes
import geb.spock.GebReportingSpec
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

@Title("US8: Create Account")
@Narrative("""
As an interested user
I want to create an account
So I access to exclusive features""")
@Issue("https://trello.com/c/IlZMPbcA")
class CreateAccountSpec extends GebReportingSpec {

    @Shared
    SignupData sharedValidSignupData = new SignupData("gopejavi", "email@mailinator.com", "superSecret", "superSecret")

    def "Navigation to Create Account Page"() {
        given: "I am at Home page"
        to HomePage

        when: "I click on a button saying Create an Account"
        createAccountButton.click()

        then: "I am at Create Account Page"
        at CreateAccountPage
    }

    //More complex example with multiple checks with data defined in "Where"
    /* def "Can Create account with fields: name #validSignupData.name, e-mail #validSignupData.email, password #validSignupData.password"() {
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
     }*/

    /*def "Can not create account withouth required data"() {
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

    def "Can not create account if email is invalid, like #validSignupDataExceptEmail"() {

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
                Classes.createDataFrom(sharedValidSignupData, [email: "thisIsNotValidMail"]),
                Classes.createDataFrom(sharedValidSignupData, [email: "thisIsNotValidNeither.com"]),
                Classes.createDataFrom(sharedValidSignupData, [email: "whoCares@aboutDomains"]),
                Classes.createDataFrom(sharedValidSignupData, [email: "asd!)/(/&)]@what.lol"]),
                Classes.createDataFrom(sharedValidSignupData, [email: "email with spaces@omg.com"])
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
                Classes.createDataFrom(sharedValidSignupData, [password: "1234567", confirmPassword: "1234567"]),
                Classes.createDataFrom(sharedValidSignupData, [password: "shortP", confirmPassword: "shortP"]),
                Classes.createDataFrom(sharedValidSignupData, [password: ")", confirmPassword: ")"])
        ]
    }*/

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
                Classes.createDataFrom(sharedValidSignupData, [password: "12345678", confirmPassword: "12345679"]),
                Classes.createDataFrom(sharedValidSignupData, [password: "SomeLongPass!!", confirmPassword: "SomeLongerPass!!"]),
                Classes.createDataFrom(sharedValidSignupData, [password: "=)(=)(=)(", confirmPassword: "()=()=()="])
        ]
    }
}






