import DataObjects.UserSearchData
import Pages.UsersPage
import Pages.UsersSearchResultsPage
import Utils.CommonLoggedSpecFeatures
import Utils.Const
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
class UserSearchSpec extends CommonLoggedSpecFeatures {

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

    def "Should paginate results: in page #numPage I see #numResults users with ids from #firstUserIdAtPage to #lastUserIdAtPage"() {
        given: "I am at Users Page"
        to UsersPage

        when: "I search with no filters"
        search(new UserSearchData("", ""))

        and: "I go to a page"
        to UsersSearchResultsPage, page: numPage

        then: "I see some users with particular ids"
        assert searchResults.size() == numResults
        assert searchResults*.id == firstUserIdAtPage..lastUserIdAtPage

        where:
        numPage | numResults | firstUserIdAtPage | lastUserIdAtPage
        1       | 10         | 1                 | 10
        2       | 10         | 11                | 20
        3       | 1          | 21                | 21
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
        assert searchResults.size() == numResults
        assert searchResults*.id == firstUserIdAtPage..lastUserIdAtPage

        where:
        page    | numResults | firstUserIdAtPage | lastUserIdAtPage
        "first" | 10         | 1                 | 10
        "last"  | 1          | 21                | 21
    }

    def "Should be able to filter users: for name '#nameFilter' and email #emailFilter we see '#numResults'"() {
        given: "I am at Users Page"
        to UsersPage

        when: "I search filtering by name and/or email"
        search(new UserSearchData(nameFilter, emailFilter))

        then: "I see a expected number of results, all containing that name (case insensitive)"
        at UsersSearchResultsPage
        assert searchResults.size() == numResults
        assert searchResults*.name.every { iContains(nameFilter) }
        assert searchResults*.email.every { iContains(emailFilter) }

        where:
        nameFilter | emailFilter               | numResults
        "gopejavi" | ""                        | 1
        "JAVI"     | ""                        | 1
        "er"       | ""                        | 2
        "a"        | ""                        | 4
        ""         | "phalconphp"              | 4
        ""         | "gopejavi@mailinator.com" | 1
        ""         | "PHP"                     | 4
        ""         | "er"                      | 2
        "gopejavi" | "mailinator"              | 1
        "er"       | "ik"                      | 1
        "g"        | "@"                       | 3
    }

    def "Should not return any user and show alert when any filter matches"() {
        given: "I am at Users Page"
        to UsersPage

        when: "I search filtering by name and/or email that give no results"
        search(new UserSearchData(nameFilter, emailFilter))

        then: "I stay at the users page and see an info message below the header"
        at UsersPage
        assert alerts.info*.text().any { it == Const.DIDNT_FIND_USERS }

        where:
        nameFilter   | emailFilter
        "iDontExist" | ""
        ""           | "iDontExist"
        "gopejavi"   | "iDontExist"
        "iDontExist" | "phalconphp"
        "gopejavi"   | "phalconphp"
    }
}