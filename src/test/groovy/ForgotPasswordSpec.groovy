import Pages.ForgotPasswordPage
import Pages.LoginPage
import Utils.CommonSpecFeatures
import Utils.Const
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Title

@Title("US14: Forgot Password")
@Narrative("""
As a forgetful registered user
I want to restore my password
So I can log in again
""")
@Issue("https://trello.com/c/WUeeumGk")
class ForgotPasswordSpec extends CommonSpecFeatures {

    def "Should navigate from Log in page to Forgot Password page"() {
        given: "I am at Login Page"
        to LoginPage

        when: "I click on Forgot Password link"
        forgotPasswordLink.click()

        then: "I am at forgot Password page"
        at ForgotPasswordPage
    }

    def "Considering email sending off, it should show a warning about it when trying to restore password"() {
        given: "I am at forgot Password page"
        to ForgotPasswordPage

        when: "I send the email to restore my password"
        sendEmailToRestorePassword(email)

        then: "I see a warning below header"
        assert alerts.warning*.text().any { it == Const.EMAILS_ARE_DISABLED }

        where:
        email << ["gopejavi@mailinator.com", "someOtherMail@mailinator.com", "someInexistant@mailForSure.lol", "evenNotAnEmail"]
    }
}