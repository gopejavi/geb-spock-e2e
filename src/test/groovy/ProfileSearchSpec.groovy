import DataObjects.ProfileSearchData
import Pages.ProfilesPage
import Pages.ProfilesSearchResultsPage
import Utils.Const
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Stepwise
import spock.lang.Title

@Title("US15: EPIC: User access by profile and permissions - US16: Profiles: create & list")
@Narrative("""
As a User Administrator
I want to manage the permissions of users
So each user only has access to specific actions
""")
@Issue([
        "https://trello.com/c/e3BUYACf",
        "https://trello.com/c/xURLGV60"
])
@Stepwise
class ProfileSearchSpec extends CreateProfileSpec { // as we want also to check the new created profiles

    def "Should list all kind of profiles when searching without filters"() {
        given: "I am at Profiles Page"
        true // We are there from CreateProfileSpec

        when: "I search with no filters"
        search(new ProfileSearchData(""))

        then: "I see all profiles"
        at ProfilesSearchResultsPage
        assert searchResults*.name == ["Administrators", "Users", "Read-Only", "Test-Profile", "All Access", "Some Access"]
    }

    def "Should be able to filter profiles: for name '#nameFilter' I see '#numResults'"() {
        given: "I am at Profiles Page"
        to ProfilesPage

        when: "I search filtering by name"
        search(new ProfileSearchData(nameFilter))

        then: "I see a expected number of results, all containing that name (case insensitive)"
        at ProfilesSearchResultsPage
        assert searchResults.size() == numResults
        assert searchResults*.name.every { iContains(nameFilter) }

        where:
        nameFilter | numResults
        "admin"    | 1
        "-"        | 2
        "ACCESS"   | 2
    }

    def "Should not return any profile and show alert when any filter matches"() {
        given: "I am at Users Page"
        to ProfilesPage

        when: "I search with name that doesn't match any profiles"
        search(new ProfileSearchData(nameFilter))

        then: "I stay at the users page and see an info message below the header"
        at ProfilesPage
        assert alerts.info*.text().any { it == Const.DIDNT_FIND_PROFILES }

        where:
        nameFilter << ["iDontExist", "gopejavi"]
    }
}