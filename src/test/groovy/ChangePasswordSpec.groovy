import DataObjects.ChangePasswordData
import DataObjects.LoginData
import Pages.ChangePasswordPage
import Pages.LoginPage
import Pages.UsersPage
import Utils.CommonLoggedSpecFeatures
import Utils.Const
import spock.lang.*

@Title("US13: Change Password")
@Narrative("""
As registered user
I want to change my password
So my account is safe if password is compromised
""")
@Issue("https://trello.com/c/8L1aVKg9")

//So we don't have to log in for every single scenario by reusing the state of previous:
@Stepwise
class ChangePasswordSpec extends CommonLoggedSpecFeatures {

    @Shared
            validLoginDataWithOldPassword = new LoginData(email: "gopejavi@mailinator.com", password: "superSecret!!!"),
            validLoginDataWithNewPassword = new LoginData(email: "gopejavi@mailinator.com", password: "12345678")

    def "Should have \"username -> change password\" at header"() {
        given: "Given I am at Users page"
        to UsersPage

        when: " I click on my user name at header"
        headerLogged.userDropdown.click()

        and: "I click on Change Password at dropdown"
        headerLogged.changePasswordLink.click()

        then: "I am at Change Password Page"
        at ChangePasswordPage
    }

    def "Should not change password if they don't match, like #unmatchingPasswordsData.password and #unmatchingPasswordsData.confirmPassword"() {
        given: "I am at Change Password page"
        true //done before, using Stepwise

        when: "I submit a new password but doesn't match with confirmation"
        changePassword(unmatchingPasswordsData)

        then: "I see an error message below the header"
        assert alerts.error*.text().any { it == Const.PASS_DOESNT_MATCH }

        where:
        unmatchingPasswordsData << [
                new ChangePasswordData(password: "whoeNeedsConfirmation?", confirmPassword: ""),
                new ChangePasswordData(password: "12345678", confirmPassword: " 12345678 "),
                new ChangePasswordData(password: "12345678", confirmPassword: "short"),
                new ChangePasswordData(password: "myNewPassYay!", confirmPassword: "myNewPassYay!!")
        ]
    }

    def "Should not change password if they are not valid, like #notValidPasswordsData.password and #notValidPasswordsData.confirmPassword"() {
        given: "I am at Change Password page"
        true //done before

        when: "I submit a new password but doesn't match with confirmation"
        changePassword(notValidPasswordsData)

        then: "I see an error message below the header"
        assert alerts.error*.text().any { it == Const.PASS_TOO_SHORT }

        where:
        notValidPasswordsData << [
                new ChangePasswordData(password: "1234567", confirmPassword: "1234567"),
                new ChangePasswordData(password: "short", confirmPassword: "thisIsNotShort")
        ]
    }

    def "Should show success message when password is changed with #validNewPasswordsData.password and #validNewPasswordsData.confirmPassword"() {
        given: "I am at Change Password page"
        true //done before

        when: "I submit my new valid password"
        changePassword(validNewPasswordsData)

        then: "I see a success message"
        assert alerts.success*.text().any { it == Const.PASS_CHANGED }

        and: "I am at Change Password page"
        at ChangePasswordPage

        where:
        validNewPasswordsData = new ChangePasswordData(password: "12345678", confirmPassword: "12345678")
    }

    def "Should not be able to log in with old passwords, like #validLoginDataWithOldPassword.email and #validLoginDataWithOldPassword.password"() {
        given: "I successfully changed my password"
        true //done before

        and: "I log out"
        headerLogged.logoutLink.click()

        and: "I am at Log in page"
        to LoginPage

        when: "I log in with valid email and old password"
        login(validLoginDataWithOldPassword)

        then: "I see an error message below the header"
        assert alerts.error*.text().any { it == Const.WRONG_EMAIL_PASS }

        where:
        validLoginDataWithOldPassword = validLoginDataWithOldPassword //so it is shown in reports as scenario variable
    }

    def "Should log in with new password: #validLoginDataWithNewPassword.email and #validLoginDataWithNewPassword.password"() {
        given: "I successfully changed my password"
        true //done before

        and: "I log out"
        true //done before

        and: "I am at Log in page"
        true //done before

        when: "I log in with my new password"
        login(validLoginDataWithNewPassword)

        then: "I am at Users page"
        at UsersPage

        where:
        validLoginDataWithNewPassword = validLoginDataWithNewPassword //so it is shown in reports as scenario variable
    }
}