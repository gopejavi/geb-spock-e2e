import DataObjects.CreateUserData
import DataObjects.UserSearchData
import Pages.CreateUserPage
import Pages.UsersPage
import Pages.UsersSearchResultsPage
import Utils.CommonLoggedSpecFeatures
import Utils.Const
import spock.lang.*

import static Utils.DataObjectsHelper.createDataFrom

@Title("US12: Create Users")
@Narrative("""
As a User administrator
I want to create users
So more people will managing this site
""")
@Issue([
        "https://trello.com/c/LTebUWzi",
        "https://trello.com/c/xURLGV60"
])
@Stepwise
// Note the difference between this form and Sign Up form, being similar this is more efficient,
// because the stepwise and input invalid fields checking using better the "where".
class CreateUserSpec extends CommonLoggedSpecFeatures {

    @Shared
            createUserDoge = new CreateUserData("Doge", "suchEmail@muchValid.wow", "Administrators"),
            createUserGopejavi = new CreateUserData("gopejavi", "nameCan@Already.exist", "Users"),
            createUserUgc = new CreateUserData("UGC", "ugc@absolutely.beer", "Read-Only")

    def "Should navigate to Create User Page from Users Page"() {
        given: "I am at Users page"
        to UsersPage

        when: "I click on a button saying Create Users"
        createUserButton.click()

        then: "I am at Create User Page"
        at CreateUserPage
    }

    def "Should not create a user with name '#invalidCreateUserData.name', email '#invalidCreateUserData.email' and profile '#invalidCreateUserData.profile'"() {
        given: "I am at Create User Page"
        true //stepwise

        when: "I create a user having some field(s) with invalid data"
        create(invalidCreateUserData)

        then: "I see an error message below header"
        assert alerts.error*.text().any { it == errorMessage }

        where:
        invalidCreateUserData                                              | errorMessage
        createDataFrom(createUserDoge, [name: ""])                         | Const.USER_NAME_REQUIRED
        createDataFrom(createUserDoge, [email: ""])                        | Const.EMAIL_REQUIRED
        createDataFrom(createUserDoge, [email: "VeryVeryIncorrect"])       | Const.EMAIL_NOT_VALID
        createDataFrom(createUserDoge, [email: "gopejavi@mailinator.com"]) | Const.EMAIL_ALREADY_REGISTERED
        createDataFrom(createUserDoge, [profile: "..."])                   | Const.PROFILE_REQUIRED
    }

    def "Should create a user with valid name '#createUserData.name', email '#createUserData.email' and profile '#createUserData.profile' and see success message"() {
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

    def "Should find new user #createdUser.name when searching by name '#userSearchData.name', email '#userSearchData.email' or profile '#userSearchData.profile'"() {
        given: "I am at Users Page"
        to UsersPage //stepwise, but looping with "where"

        when: "I search the new user by of its name or email"
        search(userSearchData)

        then: "I see one results containing info form the new user"
        at UsersSearchResultsPage
        assert searchResults.size() == numResults
        assert searchResults.any {
            it.name == createdUser.name && it.email == createdUser.email && it.profile == createdUser.profile
        }

        where:
        createdUser        | userSearchData                                                                        | numResults
        createUserDoge     | new UserSearchData(createUserDoge.name, "", "")                                       | 1
        createUserGopejavi | new UserSearchData(createUserGopejavi.name, "", "")                                   | 2
        createUserUgc      | new UserSearchData("", createUserUgc.email, "")                                       | 1
        createUserDoge     | new UserSearchData(createUserDoge.name, "", createUserDoge.profile)                   | 1
        createUserDoge     | new UserSearchData("", "", createUserDoge.profile)                                    | 5
        createUserUgc      | new UserSearchData("", createUserUgc.email, createUserUgc.profile)                    | 1
        createUserGopejavi | new UserSearchData(createUserGopejavi.name, "", createUserGopejavi.profile)           | 1
        createUserDoge     | new UserSearchData(createUserDoge.name, createUserDoge.email, createUserDoge.profile) | 1
    }
}