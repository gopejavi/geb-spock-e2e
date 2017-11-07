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
        assert searchResults.any { it.name == "gopejavi" }
        assert searchResults.any { it.name == "e" }
        assert searchResults.any { it.email == "gopejavi@mailinator.com" }
        assert searchResults.any { it.email == "bob@phalconphp.com" }
    }

    def "Should paginate results: in page #numPage I see #amount users with ids from #firstUserIdAtPage to #lastUserIdAtPage"() {
        given: "I am at Users Page"
        to UsersPage

        when: "I search with no filters"
        search(new UserSearchData("", ""))

        and: "I go to a page"
        to UsersSearchResultsPage, page: numPage

        then: "I see some users with particular ids"
        assert searchResults.size() == amount
        assert searchResults*.id == firstUserIdAtPage..lastUserIdAtPage

        where:
        numPage | amount | firstUserIdAtPage | lastUserIdAtPage
        1       | 10     | 1                 | 10
        2       | 10     | 11                | 20
        3       | 1      | 21                | 21
    }

    def "Should have links to #page page for faster navigation"() {
        given: "I am at Users Page"
        to UsersPage

        when: "I search with no filters"
        search(new UserSearchData("", ""))

        and: "I click on Last or First page after being in one in the middle"
        to UsersSearchResultsPage, page: 2
        pageButtons(page).click()

        then: "I see some users with particular ids"
        assert searchResults.size() == amount
        assert searchResults*.id == firstUserIdAtPage..lastUserIdAtPage

        where:
        page    | amount | firstUserIdAtPage | lastUserIdAtPage
        "first" | 10     | 1                 | 10
        "last"  | 1      | 21                | 21
    }
}