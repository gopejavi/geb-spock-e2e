import DataObjects.CreateProfileData
import Pages.CreateProfilePage
import Pages.ProfilesPage
import Utils.CommonLoggedSpecFeatures
import Utils.Const
import spock.lang.*

@Title("US15: EPIC: User access by profile and permissions - US16: Profiles: create & list")
@Narrative("""
As a User Administrator
I want to manage the permissions of users
So each user only has access to specific actions
""")
@Issue("https://trello.com/c/e3BUYACf")
@See("https://trello.com/c/xURLGV60")
@Stepwise
class CreateProfileSpec extends CommonLoggedSpecFeatures {

    @Shared
            createValidProfile = new CreateProfileData("All Access"),
            createValidProfile2 = new CreateProfileData("Some Access")

    def "Should navigate to Create Profiles Page from Profiles Page"() {
        given: "I am at Profiles page"
        to ProfilesPage

        when: "I click on a button saying Create Profiles"
        createProfileButton.click()

        then: "I am at Create Profiles Page"
        at CreateProfilePage
    }

    def "Should not create a profile if name is empty"() {
        given: "I am at Create Profiles Page"
        true //stepwise

        when: "I create a Profile having the name empty"
        create(new CreateProfileData(""))

        then: "I see an error message below header"
        assert alerts.error*.text().any { it == Const.PROFILE_NAME_REQUIRED }
    }

    def "Should create a profile with valid data like #createProfileData.name, and see success message"() {
        given: "I am at Create User Page"
        true //stepwise

        when: " I create a user with valid data"
        create(createProfileData)

        then: " I see a success message"
        assert alerts.success*.text().any { it == Const.PROFILE_CREATED }

        where:
        createProfileData << [createValidProfile, createValidProfile2]
    }

    def "Should have a button to go back to Profiles page"() {
        given: "I am at Create Profiles Page"
        true //stepwise

        when: "I click on a \"go back\" button"
        goBackButton.click()

        then: "I am at Profiles page"
        at ProfilesPage
    }
/*
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
    }*/
}