import DataObjects.CreateProfileData
import Pages.CreateProfilePage
import Pages.ProfilesPage
import Utils.CommonLoggedSpecFeatures
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
            createValidProfile = new CreateProfileData("All Access")

    def "Should navigate to Create Profiles Page from Profiles Page"() {
        given: "I am at Profiles page"
        to ProfilesPage

        when: "I click on a button saying Create Profiles"
        createProfileButton.click()

        then: "I am at Create Profiles Page"
        at CreateProfilePage
    }
}