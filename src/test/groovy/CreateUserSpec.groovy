import DataObjects.CreateUserData
import DataObjects.UserSearchData
import Pages.CreateUserPage
import Pages.UsersPage
import Pages.UsersSearchResultsPage
import Utils.CommonLoggedSpecFeatures
import Utils.Const
import spock.lang.*

@Title("US12: Create Users")
@Narrative("""
As a User administrator
I want to create users
So more people will managing this site
""")
@Issue("https://trello.com/c/LTebUWzi")
@Stepwise
// Note the difference between this form and Sign Up form, being similar this is more efficient,
// because the stepwise and input invalid fields checking using better the "where".
class CreateUserSpec extends CommonLoggedSpecFeatures {

    @Shared
            createUserDoge = new CreateUserData("Doge", "suchEmail@muchValid.wow"),
            createUserGopejavi = new CreateUserData("gopejavi", "nameCan@Already.exist"),
            createUserUgc = new CreateUserData("UGC", "ugc@absolutely.beer")

    def "Should navigate to Create User Page from Users Page"() {
        given: "I am at Users page"
        to UsersPage

        when: "I click on a button saying Create Users"
        createUserButton.click()

        then: "I am at Create User Page"
        at CreateUserPage
    }

    def "Should not create a user with #invalidCreateUserData.name and #invalidCreateUserData.email"() {
        given: "I am at Create User Page"
        true //stepwise

        when: "I create a user having some field(s) with invalid data"
        create(invalidCreateUserData)

        then: "I see an error message below header"
        assert alerts.error*.text().any { it == errorMessage }

        where:
        invalidCreateUserData                                    | errorMessage
        new CreateUserData("Doge", "")                           | Const.EMAIL_REQUIRED
        new CreateUserData("", "suchEmail@muchValid.wow")        | Const.NAME_REQUIRED
        new CreateUserData("Doge", "soInvalidVeryVeryIncorrect") | Const.EMAIL_NOT_VALID
        new CreateUserData("Xavier", "gopejavi@mailinator.com")  | Const.EMAIL_ALREADY_REGISTERED
    }

    def "Should create a user when all fields are valid like #createUserData.name and #createUserData.email, and see success message"() {
        given: "I am at Create User Page"
        true //stepwise

        when: " I create a user with valid data"
        create(createUserData)

        then: " I see a success message"
        assert alerts.success*.text().any { it == Const.USER_CREATED }

        where:
        createUserData << [createUserDoge, createUserGopejavi, createUserUgc]
    }

    def "Should have a button to go back to Users page"() {
        given: "I am at Create User Page"
        true //stepwise

        when: "I click on a \"go back\" button"
        goBackButton.click()

        then: "I am at Users page"
        at UsersPage
    }

    def "Should find the new user USERNAME when search by name #userSearchData.name or email #userSearchData.email"() {
        given: "I am at Users Page"
        to UsersPage //stepwise, but looping with "where"

        when: "I search the new user by of its name or email"
        search(userSearchData)

        then: "I see one results containing info form the new user"
        at UsersSearchResultsPage
        assert searchResults.size() == numResults
        assert searchResults*.name.any { it == createdUser.name }
        assert searchResults*.email.any { it == createdUser.email }

        where:
        createdUser        | userSearchData                                  | numResults
        createUserDoge     | new UserSearchData(createUserDoge.name, "")     | 1
        createUserGopejavi | new UserSearchData(createUserGopejavi.name, "") | 2
        createUserUgc      | new UserSearchData("", createUserUgc.email)     | 1
    }
}