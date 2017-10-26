import Classes.SignupData
import Pages.CreateAccountPage
import Pages.HomePage
import geb.spock.GebReportingSpec
import groovy.sql.Sql
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Title

//@Grab('mysql:mysql-connector-java:5.1.25')
//@GrabConfig(systemClassLoader = true)
@Title("US8: Create Account")
@Narrative("""
As an interested user
I want to create an account
So I access to exclusive features""")
@Issue("https://trello.com/c/IlZMPbcA")
class CreateAccountSpec extends GebReportingSpec {

    def "Navigation to Create Account Page"() {
        given: "I am at Home page"
        to HomePage

        when: "I click on a button saying Create an Account"
        createAccountButton.click()

        then: "I am at Create Account Page"
        at CreateAccountPage
    }

    //More complex example with multiple checks with data defined in "Where"
    def "Can Create account with fields: name #validSignupData.name, e-mail #validSignupData.email, password #validSignupData.password"() {
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
        true //Note: not implemented as app don't send emails.

        cleanup:
        println "Restoring Vokuro's DB..."
        def sql = Sql.newInstance("jdbc:mysql://192.168.99.100?allowMultiQueries=true", "root", "", "com.mysql.cj.jdbc.Driver")
        sql.execute("use vokuro;" + new File('src/test/groovy/Data/vokuro.sql').text)

        where:
        validSignupData << [
                new SignupData("gopejavi", "email@mailinator.com", "superSecret", "superSecret"),
                new SignupData("DOGE", "muchemail@mailinator.com", "soPasswordVerySecretWOW", "soPasswordVerySecretWOW"),
                new SignupData("E", "eve@mailinator.com", "eveveveve", "eveveveve"),
                new SignupData(" _some_!·%&/()\"\$WeirdCharsAreAllowedTooAndAlsoLongNamesWOW", "incrediblyLongAndStupidEmailForTheLol@mailinator.com", "superSecret", "superSecret"),
                new SignupData("Neil DeGrasse", "masterUniverse@mailinator.com", "IamTheGreatMasterOfTheUniverseAndYouKnowIt", "IamTheGreatMasterOfTheUniverseAndYouKnowIt")
        ]


    }
}