import DataObjects.ProfileSearchData
import Pages.ProfilesSearchResultsPage
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
class ProfileSearchSpec extends CreateProfileSpec { // as we want also to check the new created profiles

    def "Should list all kind of profiles when searching without filters"() {
        given: "I am at Profiles Page"
        true // We are there from CreateProfileSpec

        when: "I search with no filters"
        search(new ProfileSearchData(""))

        then: "I see all profiles"
        at ProfilesSearchResultsPage
        assert searchResults*.name == ["Administrators", "Users", "Read-Only", "All Access", "Some Access"]
    }
}