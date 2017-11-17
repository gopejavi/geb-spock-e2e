import DataObjects.LoginData
import DataObjects.PermissionsData
import Pages.*
import Utils.Const
import Utils.MultiBrowserGebSpec
import Utils.VokuroDatabase
import spock.lang.*

import static Utils.DataObjectsHelper.createDataFrom

@Title("US15: EPIC: User access by profile and permissions - US16: Profiles: create & list")
@Narrative("""
As a User Administrator
I want to manage the permissions of users
So each user only has access to specific actions
""")
@Issue([
        "https://trello.com/c/e3BUYACf",
        "https://trello.com/c/vHIKLAqm"
])
@Stepwise
class PermissionsSpec extends MultiBrowserGebSpec {

    @Shared
            adminSession = newBrowser(), // Here, ademinSession will change permissions of a profile,
            userSession = newBrowser(),  // and userSession will have that profile.
            allPermissions = new PermissionsData(true, true, true, true, true, true, true, true, true, true, true, true),
            somePermissions = new PermissionsData(false, false, true, true, true, true, false, false, true, true, true, false)

    def setupSpec() {
        VokuroDatabase.restoreCommon()
        using adminSession, {
            to LoginPage
            login(new LoginData("gopejavi@mailinator.com", "superSecret!!!"))
        }
        using userSession, {
            to LoginPage
            login(new LoginData("freeman@mailinator.com", "lambdaCore"))
        }
    }

    def "Should be able to select a profile and edit its permissions"() {

        given: "I am at Permissions Page"
        adminSession.to PermissionsPage

        when: "I Search a Profile"
        adminSession.search("Test-Profile")

        then: "I see all possible permissions"
        assert adminSession.permissionsForm.permissions*.description.every {
            it in ["Access users",
                   "Search users",
                   "Edit users",
                   "Create users",
                   "Delete users",
                   "Change password users",
                   "Access profiles",
                   "Search profiles",
                   "Edit profiles",
                   "Create profiles",
                   "Delete profiles",
                   "Access permissions"]
        }
    }

    @Unroll
    def "Should not let a user access to '#pageChangingPermission.url' if his/her profile has disabled permissions for that page"() {

        given: "A user with all permissions can access a particular internal page, then leaves it"
        userSession.to pageChangingPermission
        userSession.to HomePage

        and: "An admin selects the user's profile"
        true //stepwise

        when: "An admin disables the permission for that page"
        adminSession.changePermissions(permissions)

        then: "The admin sees a success message below header"
        assert adminSession.alerts.success*.text().any { it == Const.PERMISSIONS_UPDATED }

        and: "The user can't use the page, seeing info message"
        userSession.go pageChangingPermission.url
        assert userSession.alerts.info*.text().any { it == Const.DONT_HAVE_ACCESS + moduleWithoutAccess }

        where:
        pageChangingPermission    | permissions                                             | moduleWithoutAccess
        UsersPage                 | createDataFrom(allPermissions, [accessUsers: false])    | Const.USERS_INDEX
        UsersSearchResultsPage    | createDataFrom(allPermissions, [searchUsers: false])    | Const.USERS_SEARCH
        ProfilesPage              | createDataFrom(allPermissions, [accessProfiles: false]) | Const.PROFILES_INDEX
        ProfilesSearchResultsPage | createDataFrom(allPermissions, [searchProfiles: false]) | Const.PROFILES_SEARCH
        PermissionsPage           | somePermissions                                         | Const.PERMISSIONS_INDEX
    }

    def "Should let a user access to '#pageChangingPermission.url' if his/her profile has enabled permissions for that page"() {

        given: "A user with without permissions can't access a particular internal page"
        userSession.go pageChangingPermission.url
        assert userSession.alerts.info*.text().any { contains(Const.DONT_HAVE_ACCESS) }

        and: "An admin selects the user's profile"
        true //stepwise

        when: "An admin enables the permission for that page"
        adminSession.changePermissions(permissions)

        then: "The admin sees a success message below header"
        assert adminSession.alerts.success*.text().any { it == Const.PERMISSIONS_UPDATED }

        and: "The user can see the page normally"
        userSession.to pageChangingPermission
        userSession.at pageChangingPermission

        where:
        pageChangingPermission    | permissions
        UsersPage                 | createDataFrom(somePermissions, [accessUsers: true])
        UsersSearchResultsPage    | createDataFrom(somePermissions, [searchUsers: true])
        ProfilesPage              | createDataFrom(somePermissions, [accessProfiles: true])
        ProfilesSearchResultsPage | createDataFrom(somePermissions, [searchProfiles: true])
        PermissionsPage           | createDataFrom(somePermissions, [accessPermissions: true])
    }
}