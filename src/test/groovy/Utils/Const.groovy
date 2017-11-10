package Utils

import groovy.sql.Sql

class Const {
    static final String APP_MACHINE_IP = System.getProperty('geb.build.baseUrl').replace("http://", "")
    static final Sql MYSQL_CONNECTION = Sql.newInstance(
            "jdbc:mysql://$APP_MACHINE_IP/vokuro?allowMultiQueries=true",
            "root",
            "",
            "com.mysql.cj.jdbc.Driver"
    )
    // ALERT MESSAGES
    static final String USER_NAME_REQUIRED = "The name is required",
                        EMAIL_REQUIRED = "The e-mail is required",
                        EMAIL_NOT_VALID = "The e-mail is not valid",
                        EMAIL_ALREADY_REGISTERED = "The email is already registered",
                        PASS_REQUIRED = "The password is required",
                        PASS_TOO_SHORT = "Password is too short. Minimum 8 characters",
                        CONFIRM_PASS_REQUIRED = "The confirmation password is required",
                        PASS_DOESNT_MATCH = "Password doesn't match confirmation",
                        PASS_CHANGED = "Your password was successfully changed",
                        ACCEPT_TERMS = "Terms and conditions must be accepted",
            WRONG_EMAIL_PASS = "Wrong email/password combination",
            DONT_HAVE_ACCESS = "You don't have access to this module: private",
            EMAILS_ARE_DISABLED = "Emails are currently disabled. Change config key \"useMail\" to true to enable emails.",
            DIDNT_FIND_USERS = "The search did not find any users",
            USER_CREATED = "User was created successfully",
            PROFILE_NAME_REQUIRED = "name is required",
            PROFILE_CREATED = "Profile was created successfully",
            DIDNT_FIND_PROFILES = "The search did not find any profiles",
            PROFILE_REQUIRED = "profilesId is required"
}
