import DataObjects.LoginData
import DataObjects.UserSearchData
import Pages.LoginPage
import Pages.UsersPage
import Pages.UsersSearchResultsPage
import Utils.CommonSpecFeatures
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Stepwise
import spock.lang.Title

@Title("US11: Users Search")
@Narrative("""
As User administrator
I want to search and list users
So I know who is registered
""")
@Issue("https://trello.com/c/RVupwgNJ")
@Stepwise
class UserSearchSpec extends CommonSpecFeatures {

    //def searchFilters = new UserSearchFilters("")

    def setupSpec() {
        to LoginPage
        login(new LoginData("gopejavi@mailinator.com", "superSecret!!!"))
    }

    def "Should list all kind of users when searching without filters"() {
        given: "I am at Users Page"
        to UsersPage

        when: "I search with no filters"
        search(new UserSearchData("", ""))

        then: "I see search results as users with different names and emails"
        at UsersSearchResultsPage
        assert searchResults*.id == 1..10
        assert searchResults.any { it.name == "gopejavi" }
        assert searchResults.any { it.name == "e" }
        assert searchResults.any { it.email == "gopejavi@mailinator.com" }
        assert searchResults.any { it.email == "bob@phalconphp.com" }
    }
/*
    def "Registered user should log in successfully with e-mail #sharedValidLoginData.email, password #sharedValidLoginData.password"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I log in with valid data"
        login(sharedValidLoginData)

        then: "I am at Users Page"
        at UsersPage
    }

    def "Should not log in without email"() {
        given: "I am at Log In page"
        to LoginPage

        when: "I log in with valid password, empty email"
        login(emptyMailValidPassword)

        then: "I see an error message below the header"
        assert alerts.error*.text().any { it == Const.EMAIL_REQUIRED }

        where:
        emptyMailValidPassword = DataObjectsHelper.createDataFrom(sharedValidLoginData, [email: ""])
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
        assert alerts.error*.text().any { it == Const.PASS_REQUIRED }

        where:
        validLoginDataExceptPassword = DataObjectsHelper.createDataFrom(sharedValidLoginData, [password: ""])
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
    */
}